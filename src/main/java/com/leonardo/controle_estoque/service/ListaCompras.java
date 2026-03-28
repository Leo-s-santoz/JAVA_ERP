package com.leonardo.controle_estoque.service;

import com.leonardo.controle_estoque.dto.ItemCompra;
import com.leonardo.controle_estoque.model.Produto;
import com.leonardo.controle_estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListaCompras {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ItemCompra> gerarListaCompras(){

        List<Produto> produtos = produtoRepository.findAll();
        List<ItemCompra> listaCompras = new ArrayList<>();

        for (Produto produto : produtos) {

            double atual = produto.getQuantidadeAtual();
            double minimo = produto.getQuantidadeMinima();

            double comprar = minimo - atual;

            if (comprar > 0) {
                ItemCompra item = new ItemCompra(
                        produto.getNome(),
                        atual,
                        minimo,
                        comprar
                );
                listaCompras.add(item);
            }
        }
        return listaCompras;
    }
}
