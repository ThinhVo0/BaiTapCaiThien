package com.xuanthinh.service.impl;

import com.xuanthinh.entity.Users;
import com.xuanthinh.model.LoginUserModel;
import com.xuanthinh.model.RegisterUserModel;
import com.xuanthinh.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public Users signup(RegisterUserModel input){
        Users user = new Users();
        user.setName(input.getFullname());
        user.setEmail(input.getUsernameOrEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        return userRepository.save(user);
    }
    public Users authenticate(LoginUserModel input){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(input.getUsernameOrEmail(), input.getPassword()));
        return userRepository.findByEmail(input.getUsernameOrEmail()).orElseThrow();
    }

}
