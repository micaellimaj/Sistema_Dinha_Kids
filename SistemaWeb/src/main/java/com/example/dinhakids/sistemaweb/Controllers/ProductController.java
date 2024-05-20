package com.example.dinhakids.sistemaweb.Controllers;


import com.example.dinhakids.sistemaweb.DTO.CreateOrUpdate.ProductCreateOrUpdateDTO;
import com.example.dinhakids.sistemaweb.Domain.Fornecedor;
import com.example.dinhakids.sistemaweb.Domain.Product;
import com.example.dinhakids.sistemaweb.Services.FornecedorService;
import com.example.dinhakids.sistemaweb.Services.ProductService;
import com.example.dinhakids.sistemaweb.Services.ProductServiceImpl;
import com.example.dinhakids.sistemaweb.util.PaginacaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/produtos") //Fazer referência a table
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private FornecedorService fornecedorService;


    @GetMapping("/cadastrar")
    public String cadastrar(Product product) {
        return "produto/cadastro";
    }

    @GetMapping("/listar")
    public String listar(ModelMap model) {
        model.addAttribute("produtos", productService.buscarTodos());
        return "produto/listar";
    }

    @PostMapping(path = "/salvar")
    public String salvar(@Valid Product product, BindingResult result, RedirectAttributes attr){
        if(result.hasErrors()) {
            return "produto/cadastro";
        }

        productService.salvar(product);
        attr.addFlashAttribute("sucesso", "Produto cadastrado com sucesso!");
        return "redirect:/produtos/cadastrar";
    }


    @GetMapping(path = "/editar/{id}")
    public String preEditar(@PathVariable("id") String id, ModelMap model) {
        model.addAttribute("produto", productService.buscarPorId(id));
        return "produto/cadastro";
    }


    @PostMapping(path = "/editar")
    public String editar(@Valid Product product, BindingResult result, RedirectAttributes attr){

        if(result.hasErrors()) {
            return "produto/cadastro";
        }

        productService.editar(product);
        attr.addFlashAttribute("sucesso", "Produto modificado com sucesso!");
        return "redirect:/produtos/cadastrar";
    }


    @GetMapping(path = "/excluir/{id}")
    public String excluir(@PathVariable("id") String id, RedirectAttributes attr){
        productService.excluir(id);
        attr.addFlashAttribute("sucesso", "Produto removido com sucesso!");
        return "redirect:/produtos/cadastrar";
    }

    @GetMapping("/buscar/nome")
    public String BuscarNome(@RequestParam("nome") String nome, ModelMap model){
        model.addAttribute("produtos", productService.buscarPorNome(nome));
        return "produto/lista";
    }

    @GetMapping("/buscar/fornecedor")
    public String BuscarFornecedor(@RequestParam("id") String id, ModelMap model){
        model.addAttribute("produtos", productService.buscarPorFornecedor(id));
        return "produto/lista";
    }

    @GetMapping("/buscar/id")
    public String BuscarId(@RequestParam("id") String id, ModelMap model){
        model.addAttribute("produtos", productService.buscarPorId(id));
        return "produto/lista";
    }

    @ModelAttribute("fornecedores")
    public List<Fornecedor> getFornecedores() {
        return fornecedorService.buscarTodos();
    }

}
