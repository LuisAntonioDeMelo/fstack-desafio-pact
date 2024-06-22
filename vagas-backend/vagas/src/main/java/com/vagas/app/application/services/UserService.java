package com.vagas.app.application.services;

import com.vagas.app.application.resources.dto.AuthenticationRequest;
import com.vagas.app.application.resources.dto.Register;
import com.vagas.app.application.resources.dto.UserRequest;
import com.vagas.app.application.services.erros.RegisterException;
import com.vagas.app.config.Token;
import com.vagas.app.config.TokenService;
import com.vagas.app.domain.User;
import com.vagas.app.infra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    public User criarUser(Register register) {
        if (this.userRepository.findByLogin(register.username()).isPresent()) {
            throw new RegisterException("Não e possivel registrar com esse login");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(register.password());
        User newUser = User.getUser(register, encryptedPassword);
        return this.userRepository.save(newUser);
    }

    public Token obterToken(AuthenticationRequest req) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(req.username(), req.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            return tokenService.generateToken((User) auth.getPrincipal());
        }catch (Exception e) {
            throw new RuntimeException("Erro ao tentar logar: " + e.getMessage());
        }
    }

    public User obterDadosUser(UserRequest userRequest){
        return userRepository.findByLogin(userRequest.login()).orElseThrow();
    }

    public List<User> obterTodos(){
        return userRepository.findAll();
    }

}
