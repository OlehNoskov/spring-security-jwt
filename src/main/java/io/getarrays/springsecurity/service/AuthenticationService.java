package io.getarrays.springsecurity.service;

import io.getarrays.springsecurity.dao.request.SignUpRequest;
import io.getarrays.springsecurity.dao.request.SigninRequest;
import io.getarrays.springsecurity.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
