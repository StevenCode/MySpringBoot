package com.github.steven.account.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
}
