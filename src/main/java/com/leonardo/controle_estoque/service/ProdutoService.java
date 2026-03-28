package com.leonardo.controle_estoque.service;

import com.leonardo.controle_estoque.model.Produto;
import com.leonardo.controle_estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> buscar(
            String nome, String unidade, Boolean estoqueBaixo
    ) {
        Specification<Produto> specification = ((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());

        if (nome != null && !nome.isEmpty()) {
            specification = specification.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("nome")), "%" + nome.toLowerCase() + "%"));
        }

        if (unidade != null && !unidade.isEmpty()) {
            specification = specification.and((root, query, cb) ->
                    cb.equal(root.get("unidade"), unidade));
        }

        if (estoqueBaixo != null && estoqueBaixo) {
            specification = specification.and((root, query, cb) ->
                    cb.lessThanOrEqualTo(
                            root.get("quantidadeAtual"),
                            root.get("quantidadeMinima")
                    ));
        }

        return produtoRepository.findAll(specification);
    }

    public Produto buscarPorId(Long id){
        return produtoRepository.findById(id).orElseThrow(()-> new RuntimeException("Produto não Encontrado"));
    }

    public void salvar(Produto produto){
        calcularLucroEMargem(produto);
        produtoRepository.save(produto);
    }

    public void calcularLucroEMargem(Produto produto) {

        Double precoCusto = produto.getPrecoCusto();
        Double precoVenda = produto.getPrecoVenda();

        if (precoVenda != null && precoCusto != null && precoCusto > 0){

            double lucro = precoVenda - precoCusto;
            double margem = (lucro / precoCusto) * 100;

            produto.setLucro(lucro);
            produto.setMargemLucro(margem);
        }
    }

}
