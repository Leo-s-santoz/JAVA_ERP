package com.leonardo.controle_estoque.repository;

import com.leonardo.controle_estoque.model.Produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProdutoRepository extends
        JpaRepository<Produto, Long>,
        JpaSpecificationExecutor<Produto> {
}
