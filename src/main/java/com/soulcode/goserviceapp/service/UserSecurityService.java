package com.soulcode.goserviceapp.service;

import com.soulcode.goserviceapp.domain.Usuario;
import com.soulcode.goserviceapp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserSecurityService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(username);
        if (usuario.isPresent()) {
            return usuario.get();
        }
        throw new UsernameNotFoundException("Usuario não encontrado.");
    }
}
