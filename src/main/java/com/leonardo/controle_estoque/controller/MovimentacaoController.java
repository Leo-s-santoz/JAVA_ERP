package com.leonardo.controle_estoque.controller;

import com.leonardo.controle_estoque.model.Movimentacao;
import com.leonardo.controle_estoque.repository.MovimentacaoRepository;
import com.leonardo.controle_estoque.repository.ProdutoRepository;
import com.leonardo.controle_estoque.service.MovimentacaoService;
import com.leonardo.controle_estoque.utils.TipoMovimentacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@RestController
public class MovimentacaoController {

    @Autowired
    private MovimentacaoService movimentacaoService;
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping("/registrar")
    public ModelAndView registrarMovimentacao(
            @RequestParam Long produtoId,
            @RequestParam TipoMovimentacao tipo,
            @RequestParam Integer quantidade,
            @RequestParam(required = false) String observacao,
            RedirectAttributes redirectAttributes
            ){
        try {
            movimentacaoService.registrar(produtoId, tipo, quantidade, observacao);
            redirectAttributes.addFlashAttribute("sucesso", "Movmentação registrada");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
        }
        return new ModelAndView("redirect:/estoqueProduto");
    }

    @GetMapping("/movimentacao")
    public ModelAndView filtrarMovimentacoes(
            @RequestParam(required = false) Long produtoId,
            @RequestParam(required = false) TipoMovimentacao tipo,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate data
    ){
        ModelAndView modelAndView = new ModelAndView("estoque/movimentacoes/lista");

        List<Movimentacao> lista;

        if (produtoId != null || tipo != null || data != null) {
            lista = movimentacaoService.buscar(produtoId, tipo, data);
        }else {
            lista = movimentacaoService.listarPorData();
        }

        modelAndView.addObject("produtoId", produtoId);
        modelAndView.addObject("tipo", tipo);
        modelAndView.addObject("data", data);
        modelAndView.addObject("listaMovimentacoes", lista);
        modelAndView.addObject("produtos", produtoRepository.findAll());

        return modelAndView;
    }

}
