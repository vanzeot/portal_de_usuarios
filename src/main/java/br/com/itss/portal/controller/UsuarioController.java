package br.com.itss.portal.controller;

import br.com.itss.portal.usuario.Usuario;
import br.com.itss.portal.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/lista_de_usuarios")
    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

}
