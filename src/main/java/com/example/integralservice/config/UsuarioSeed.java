package com.example.integralservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.integralservice.entity.Usuario;
import com.example.integralservice.enums.TipoUsuario;
import com.example.integralservice.repository.UsuarioRepository;

@Component
public class UsuarioSeed implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.usuarioadmin.email}")
    private String adminEmail;

    @Value("${spring.usuarioadmin.password}")
    private String adminPassword;

    public UsuarioSeed(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run (String... args) {
        if (usuarioRepository.count() == 0) {

            Usuario admin = new Usuario();
            admin.setNome("Administrador");
            admin.setEmail(adminEmail.trim());
            admin.setSenha(passwordEncoder.encode(adminPassword.trim()));
            admin.setTipo(TipoUsuario.ADMIN);

            usuarioRepository.save(admin);

            System.out.println("✅ Usuário ADMIN criado com sucesso");
        }
    }
}
