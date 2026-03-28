package com.leonardo.controle_estoque.service;

import com.leonardo.controle_estoque.model.Movimentacao;
import com.leonardo.controle_estoque.model.Produto;
import com.leonardo.controle_estoque.repository.MovimentacaoRepository;
import com.leonardo.controle_estoque.repository.ProdutoRepository;
import com.leonardo.controle_estoque.utils.TipoMovimentacao;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
public class MovimentacaoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;
    @Autowired
    private ProdutoService produtoService;

    public void registrar(
            Long produtoId,
            TipoMovimentacao tipo,
            Integer quantidade,
            String observacao){

        Produto produto = produtoService.buscarPorId(produtoId);

        if (quantidade == null || quantidade <= 0) {
            throw new RuntimeException("Quantidade Inválida");
        }

        if (tipo == TipoMovimentacao.ENTRADA){
            produto.setQuantidadeAtual(produto.getQuantidadeAtual() + quantidade);
        }

        if (tipo == TipoMovimentacao.SAIDA){
            if (produto.getQuantidadeAtual() < quantidade){
                throw new RuntimeException("Estoque Insuficiente para retirada");
            }
            produto.setQuantidadeAtual(produto.getQuantidadeAtual() - quantidade);
        }
        produtoRepository.save(produto);
        salvarMovimentacao(produto, tipo, quantidade, observacao);
    }

    private void salvarMovimentacao(
            Produto produto,
            TipoMovimentacao tipo,
            Integer quantidade,
            String observacao){

        Movimentacao movimentacao = new Movimentacao();

        movimentacao.setProduto(produto);
        movimentacao.setTipo(tipo);
        movimentacao.setQuantidade(quantidade);
        movimentacao.setObservacao(observacao);

        movimentacaoRepository.save(movimentacao);
    }

    public List<Movimentacao>listarPorData(){
        return movimentacaoRepository.findAll(Sort.by("data").descending());

    }

    public List<Movimentacao> buscar(Long produtoId, TipoMovimentacao tipo, LocalDate data) {

        Specification<Movimentacao> specification = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();

        if (produtoId != null){
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("produto").get("id"), produtoId));
        }


        if(tipo != null){
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("tipo"), tipo));
        }

        if(data != null){
            LocalDateTime inicio = data.atStartOfDay();
            LocalDateTime fim = data.atTime(23,59,59);

            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.between(root.get("data"), inicio, fim));
        }

        return movimentacaoRepository.findAll(specification, Sort.by("data").descending());
    }
}
