package br.com.itss.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/usuarios")
    public String usuarios(){
        return "usuarios";
    }

}
