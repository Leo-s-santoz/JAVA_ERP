package com.leonardo.controle_estoque.service;

import com.leonardo.controle_estoque.model.Produto;
import com.leonardo.controle_estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

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
