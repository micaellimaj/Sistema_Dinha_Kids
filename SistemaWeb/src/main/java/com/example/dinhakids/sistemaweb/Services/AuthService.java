package com.example.dinhakids.sistemaweb.Services;

import com.example.dinhakids.sistemaweb.DTO.AcessDTO;
import com.example.dinhakids.sistemaweb.DTO.AuthenticationDTO;
import com.example.dinhakids.sistemaweb.Models.User;
import com.example.dinhakids.sistemaweb.Repository.UserRepository;
import com.example.dinhakids.sistemaweb.Security.JWT.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoderService passwordEncoderService;
    @Autowired
    private UserRepository userRepository;


    public AcessDTO login(AuthenticationDTO dto) {


        try {

            User user = userRepository.findByUserName(dto.getUserName()).orElseThrow(() -> new BadCredentialsException("Username ou senha invalidos"));

            if (user!= null) {
                // Verifica se a senha fornecida pelo usuário coincide com a senha encriptada armazenada no banco de dados
                if (passwordEncoderService.matches(dto.getPassword(), user.getPassword())) {
                    // Cria mecanismo de credencial para o spring
                    UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(dto.getUserName(), user.getPassword());

                    // Prepara mecanismo para autenticação
                    Authentication authentication = authenticationManager.authenticate(userAuth);

                    // Busca usuario logado
                    UserDetailsImpl userAuthenticate = (UserDetailsImpl) authentication.getPrincipal();

                    String token = jwtUtils.generateTokenFromUserDetailsImpl(userAuthenticate);

                    AcessDTO acessDTO = new AcessDTO(token);

                return acessDTO;
                }else {
                    throw new BadCredentialsException("Invalid username or password");
                }
            } else {
                throw new BadCredentialsException("Invalid username or password");
            }

        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

}
