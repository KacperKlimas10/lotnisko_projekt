package org.pl.serwis_logownia;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthService {

    @GetMapping("/login/")
    private void login(String username, String password) {}

    private String hashPassword(String password){
        return "";
    }
}
