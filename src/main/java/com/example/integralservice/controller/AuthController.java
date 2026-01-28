package com.example.integralservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.integralservice.config.UsuarioDetails;
import com.example.integralservice.dto.LoginRequestDTO;
import com.example.integralservice.dto.LoginResponseDTO;
import com.example.integralservice.dto.UsuarioLogadoResponseDTO;
import com.example.integralservice.entity.Usuario;
import com.example.integralservice.service.JwtService;
import com.example.integralservice.service.UsuarioService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsuarioService usuarioService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, UsuarioService usuarioService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO dto) {

        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.email(), dto.senha()));

        UsuarioDetails usuarioDetails = (UsuarioDetails) auth.getPrincipal();
        Usuario usuario = usuarioDetails.getUsuario();

        String token = jwtService.gerarToken(usuario);

        return new LoginResponseDTO(token);
    }

    @GetMapping("/me")
    public ResponseEntity<UsuarioLogadoResponseDTO> usuarioLogado() {

        Usuario usuario = usuarioService.buscarUsuarioLogado();

        return ResponseEntity.ok(
            new UsuarioLogadoResponseDTO(
                usuario.getId(),
                usuario.getNome(), 
                usuario.getEmail(), 
                usuario.getTipo()
            )
        );
    }
}
