package com.leonardo.controle_estoque.controller;

import com.leonardo.controle_estoque.model.Produto;
import com.leonardo.controle_estoque.repository.ProdutoRepository;
import com.leonardo.controle_estoque.service.ListaCompras;
import com.leonardo.controle_estoque.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.Optional;

@RestController
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private ListaCompras listaCompras;

    @GetMapping("/cadastroProduto")
    public ModelAndView cadastrar(Produto produto){
        ModelAndView modelAndView = new ModelAndView("estoque/produto/cadastro");
        modelAndView.addObject("produto", produto);
        return modelAndView;
    }

    @PostMapping("/salvarProduto")
    public ModelAndView salvar(Produto produto, BindingResult result){
        if (result.hasErrors()) {
            System.out.println("erro nas informações");
            result.getAllErrors().forEach(error -> System.out.println(" - " + error.toString()));
            return cadastrar(produto);
        }
        produtoService.salvar(produto);
        return new ModelAndView("redirect:/cadastroProduto");
    }

    @GetMapping("/excluirProduto/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id){
        Optional<Produto> produto = produtoRepository.findById(id);
        produtoRepository.delete(produto.get());
        return new ModelAndView("redirect:/estoqueProduto");
    }

    @GetMapping("editarProduto/{id}")
    public ModelAndView editar(@PathVariable("id") Long id){
        Optional<Produto> produto = produtoRepository.findById(id);
        return cadastrar(produto.get());
    }

    @GetMapping("/estoqueProduto")
    public ModelAndView listar(){
        ModelAndView modelAndView = new ModelAndView("estoque/produto/lista");
        modelAndView.addObject("listaProduto", produtoRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/listaCompras")
    public ModelAndView listaCompras(){
        ModelAndView modelAndView = new ModelAndView("estoque/produto/compras");
        modelAndView.addObject("itensCompra", listaCompras.gerarListaCompras());
        return modelAndView;
    }
}
