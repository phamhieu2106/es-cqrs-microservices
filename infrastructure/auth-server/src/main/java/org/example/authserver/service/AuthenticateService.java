package org.example.authserver.service;

import org.example.authserver.domain.request.AuthenticateRequest;
import org.example.sharedlibrary.response.WrapperResponse;
import org.example.userdomain.command.CreateUserCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticateService {
    WrapperResponse register(CreateUserCommand command);

    WrapperResponse authenticate(AuthenticateRequest authRequest);

    ResponseEntity<?> validateToken(String token);

    String extractUsername(String token);

    boolean isTokenValid(String token, UserDetails userDetails);
}
