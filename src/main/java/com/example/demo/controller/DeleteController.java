package com.example.demo.controller;


import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class DeleteController {

    private final UserService userService;

    @PostMapping("delete/user")

    public String deleteUser(Principal principal){
        String email= principal.getName();
        userService.deleteUser(email);
        return "user has been deleted";
    }
}
