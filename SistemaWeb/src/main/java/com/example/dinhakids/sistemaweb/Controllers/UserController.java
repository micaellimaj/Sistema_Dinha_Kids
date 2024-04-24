package com.example.dinhakids.sistemaweb.Controllers;

import com.example.dinhakids.sistemaweb.Models.UserModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("/")
    public void create(@RequestBody UserModel userModel){
        System.out.println(userModel.getNome());
    }
}
