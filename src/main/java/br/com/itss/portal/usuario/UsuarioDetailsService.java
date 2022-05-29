package br.com.itss.portal.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
       Optional<Usuario> usuarioRetornado = usuarioRepository.findByUserName(userName);

        usuarioRetornado.orElseThrow(()-> new UsernameNotFoundException("Usuário não encontrado: "+userName));

        return usuarioRetornado.map(UsuarioDetails::new).get();
    }
}
