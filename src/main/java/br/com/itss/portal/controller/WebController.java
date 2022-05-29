package br.com.itss.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
