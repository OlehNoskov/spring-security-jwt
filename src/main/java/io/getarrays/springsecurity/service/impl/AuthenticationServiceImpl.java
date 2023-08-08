package io.getarrays.springsecurity.service.impl;

import io.getarrays.springsecurity.dao.request.SignUpRequest;
import io.getarrays.springsecurity.dao.request.SigninRequest;
import io.getarrays.springsecurity.dao.response.JwtAuthenticationResponse;
import io.getarrays.springsecurity.entity.Role;
import io.getarrays.springsecurity.entity.User;
import io.getarrays.springsecurity.repository.UserRepository;
import io.getarrays.springsecurity.service.AuthenticationService;
import io.getarrays.springsecurity.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        var user = User.builder().firstname(request.getFirstname()).lastname(request.getLastname())
            .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
            .role(Role.USER).build();
        userRepository.save(user);
        String jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        String jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
