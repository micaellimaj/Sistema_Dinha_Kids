package com.example.dinhakids.sistemaweb.Services;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

//transforma a senha digitada num hash
@Service
public class PasswordEncoderService {

    public String encode(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    public boolean matches(String password, String encodedPassword) {
        return BCrypt.verifyer().verify(password.toCharArray(), encodedPassword).verified;
    }

}
