package com.example.dinhakids.sistemaweb.Controllers;


import com.example.dinhakids.sistemaweb.Domain.Fornecedor;
import com.example.dinhakids.sistemaweb.Services.FornecedorService;
import com.example.dinhakids.sistemaweb.util.PaginacaoUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping("/cadastrar")
    public String cadastrar(Fornecedor fornecedor){
        return "fornecedores/cadastrar";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model,
                         @RequestParam("page") Optional<Integer> page,
                         @RequestParam("dir") Optional<String> dir) {

        int paginaAtual = page.orElse(1);
        String ordem = dir.orElse("asc");

        PaginacaoUtil<Fornecedor> pageCargo = fornecedorService.buscarPorPagina(paginaAtual, ordem);

        model.addAttribute("pageCargo", pageCargo);
        return "fornecedores/lista";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Fornecedor fornecedor, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "fornecedores/cadastro";
        }

        fornecedorService.salvar(fornecedor);
        attr.addFlashAttribute("success", "Fornecedor inserido com sucesso.");
        return "redirect:/fornecedores/cadastrar";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") String  id, ModelMap model) {
        model.addAttribute("fornecedores", fornecedorService.buscarPorId(id));
        return "fornecedores/cadastro";
    }

    @PostMapping("/editar")
    public String editar(@Valid Fornecedor fornecedor, BindingResult result, RedirectAttributes attr) {

        if (result.hasErrors()) {
            return "fornecedores/cadastro";
        }

        fornecedorService.editar(fornecedor);
        attr.addFlashAttribute("success", "Registro atualizado com sucesso.");
        return "redirect:/fornecedores/cadastrar";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") String id, RedirectAttributes attr) {
        if (fornecedorService.FornecedorTemProdutos(id)) {
            attr.addFlashAttribute("fail", "Fornecedor não excluido. Tem Produto(s) vinculado(s).");
        } else {
            fornecedorService.excluir(id);
            attr.addFlashAttribute("success", "Fornecedor excluido com sucesso.");
        }
        return "redirect:/fornecedores/listar";
    }
}
