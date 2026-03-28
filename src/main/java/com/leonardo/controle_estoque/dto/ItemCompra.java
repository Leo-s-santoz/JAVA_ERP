package com.leonardo.controle_estoque.dto;

public class ItemCompra {
    private String nome;
    private Double quantidadeAtual;
    private Double quantidadeMinima;
    private Double quantidadeComprar;

    public ItemCompra(String nome, Double atual, Double minimo, Double quantidadeComprar){
        this.nome = nome;
        this.quantidadeAtual = atual;
        this.quantidadeMinima = minimo;
        this.quantidadeComprar = quantidadeComprar;
    }

    public String getNome() {
        return nome;
    }

    public Double getQuantidadeAtual() {
        return quantidadeAtual;
    }

    public Double getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public Double getQuantidadeComprar() {
        return quantidadeComprar;
    }
}
