package com.example.demo;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Secured("ROLE_ADMIN")
    @GetMapping("/edit")
    public String editRecord() {
        return "Редактирование записи доступно только администраторам.";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/delete")
    public String deleteRecord() {
        return "Удаление записи доступно только администраторам.";
    }
}


