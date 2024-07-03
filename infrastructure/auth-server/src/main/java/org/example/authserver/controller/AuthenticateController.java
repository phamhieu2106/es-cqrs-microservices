package org.example.authserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.authserver.domain.request.AuthenticateRequest;
import org.example.authserver.service.AuthenticateService;
import org.example.sharedlibrary.response.WrapperResponse;
import org.example.userdomain.command.CreateUserCommand;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/v1")
@RequiredArgsConstructor
public class AuthenticateController {

    private final AuthenticateService authenticateService;


    @PostMapping("/login")
    public WrapperResponse login(@RequestBody AuthenticateRequest authRequest) {
        return authenticateService.authenticate(authRequest);
    }

    @PostMapping("/register")
    public WrapperResponse register(@RequestBody CreateUserCommand command) {
        return authenticateService.register(command);
    }

}
