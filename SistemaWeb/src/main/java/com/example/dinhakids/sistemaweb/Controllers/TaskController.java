package com.example.dinhakids.sistemaweb.Controllers;

import com.example.dinhakids.sistemaweb.Models.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;
import java.util.List;
import java.util.ArrayList; // Importe a classe ArrayList
import com.example.dinhakids.sistemaweb.Models.Task; // Importação da classe Task

@Controller
public class TaskController {

    List<Task> tasks = new ArrayList<>();

    @GetMapping("/cadastrar")
    public String home() {
        return "dashboard";
    }


    @PostMapping("/cadastrar")
    public String create(Task task){
        Long id = (long) tasks.size() + 1L;;
        System.out.println("O nome da tarefa é");
        return "redirect:/dashboard";
}

    @GetMapping("/dashboard")
    public ModelAndView dashboard() {
        ModelAndView mv = new ModelAndView("dashboard");
        mv.addObject("tasks", tasks);
        return mv;
    }

}
