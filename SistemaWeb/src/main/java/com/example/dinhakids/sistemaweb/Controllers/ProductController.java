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


    //retorna todos os produtos
    @GetMapping("/produtos")
    public ResponseEntity<List<Product>> getProduct(){
        List<Product> products = productService.getProducts();

        return "produtos/cadastro";
    }

    
    //retorna os produtos pelo id
    @GetMapping(path = "/produtos/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id){
        Product product = productService.getProductById(id);
    }

    //atualiza os produtos
    @PutMapping(path = "/produtos/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, String nome,int quantidade,double preco, @RequestBody @Valid ProductCreateOrUpdateDTO dto){
        Product product = dto.getProduct();
    }

    //deleta os produtos pelo id
    @GetMapping(path = "/produtos/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable String id){
        productService.deleteProduct(id);
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
