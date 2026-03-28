package com.leonardo.controle_estoque.repository;
import com.leonardo.controle_estoque.model.Movimentacao;
import com.leonardo.controle_estoque.utils.TipoMovimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface MovimentacaoRepository extends
        JpaRepository<Movimentacao, Long>,
        JpaSpecificationExecutor<Movimentacao> {

}
/*
    List<Movimentacao> findByProdutoId(Long produtoId);
    List<Movimentacao> findByTipo(TipoMovimentacao tipo);
    List<Movimentacao> findByData(LocalDate data);
    List<Movimentacao> findByProdutoIdAndTipo(Long produtoId, TipoMovimentacao tipo);
    List<Movimentacao> findByProdutoIdAndData(Long produtoId, LocalDate data);
    List<Movimentacao> findByTipoAndData(TipoMovimentacao tipo, LocalDate data);
    List<Movimentacao> findByProdutoIdAndTipoAndData(Long produtoId, TipoMovimentacao tipo, LocalDate data);
    List<Movimentacao> findByDataBetween(LocalDateTime inicio, LocalDateTime fim);
*/
