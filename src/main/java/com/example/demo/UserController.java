package com.example.demo;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Secured("ROLE_USER")
    @GetMapping("/view")
    public String viewRecord() {
        return "Просмотр записи доступен всем пользователям.";
    }
}