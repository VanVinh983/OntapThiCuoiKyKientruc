package com.example.jwtspringboot.controller;

import com.example.jwtspringboot.authen.UserPrincipal;
import com.example.jwtspringboot.entity.Token;
import com.example.jwtspringboot.entity.User;
import com.example.jwtspringboot.service.TokenService;
import com.example.jwtspringboot.service.UserService;
import com.example.jwtspringboot.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        if (userService.createUser(user) != null) {
            UserPrincipal userPrincipal = userService.findByUsername(user.getUsername());
            Token token = new Token();
            token.setToken(jwtUtil.generateToken(userPrincipal));
            token.setTokenExpDate(jwtUtil.generateExpirationDate());
            token.setCreatedBy(userPrincipal.getUserId());
            tokenService.createToken(token);

            return ResponseEntity.ok(token.getToken());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Account or password is not valid!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {

        UserPrincipal userPrincipal =
                userService.findByUsername(user.getUsername());

        if (null == user || !new BCryptPasswordEncoder()
                .matches(user.getPassword(), userPrincipal.getPassword())) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Account or password is not valid!");
        }

        Token token = new Token();
        token.setToken(jwtUtil.generateToken(userPrincipal));

        token.setTokenExpDate(jwtUtil.generateExpirationDate());
        token.setCreatedBy(userPrincipal.getUserId());
        tokenService.createToken(token);

        return ResponseEntity.ok(token.getToken());
    }


    @GetMapping("/hello")
    @PreAuthorize("hasAnyAuthority('USER_READ')")
    public ResponseEntity hello(){
        return ResponseEntity.ok("hello");
    }

    @PostMapping("/generate")
    public ResponseEntity<?> generate(@RequestBody User user) {
        if(login(user).getStatusCode() == HttpStatus.OK)
            return login(user);
        else
            return register(user);
    }

}
