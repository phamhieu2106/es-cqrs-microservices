package org.example.userquery.controller;

import lombok.RequiredArgsConstructor;
import org.example.sharedlibrary.response.WrapperResponse;
import org.example.userquery.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserQueryController {

    private final UserService service;

    @GetMapping
    public WrapperResponse findAll() {
        return service.findAll();
    }

    @GetMapping("/detail/{userId}")
    public WrapperResponse findOne(@PathVariable String userId) {
        return service.findOne(userId);
    }
}
