package com.example.tienda.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.tienda.dto.ResgisterResponse;
import com.example.tienda.dto.ResgistroRequest;
import com.example.tienda.exceotion.AutchException;
import com.example.tienda.model.Usuario;
import com.example.tienda.repository.UsuarioRepository;

@Service
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private ResgisterResponse convertirResponse(Usuario usuario) {

        return new ResgisterResponse("usuario creado exitosamente", usuario.getUsername());
    }

    public ResgisterResponse register(ResgistroRequest request) {
        if (this.usuarioRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new AutchException("usuario existente");
        }
        String password = this.passwordEncoder.encode(request.getPassword());
        Usuario usuario = new Usuario(request.getUsername(), password, "user");
        return this.convertirResponse(this.usuarioRepository.save(usuario));
    }
}
