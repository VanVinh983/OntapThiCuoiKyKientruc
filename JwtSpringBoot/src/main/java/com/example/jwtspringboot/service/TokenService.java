package com.example.jwtspringboot.service;

import com.example.jwtspringboot.entity.Token;
import com.example.jwtspringboot.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    public Token createToken(Token token) {
        return tokenRepository.saveAndFlush(token);
    }


    public Token findByToken(String token) {
        return tokenRepository.findByToken(token);
    }
}
