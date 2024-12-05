package org.pl.serwis_logownia.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.pl.serwis_logownia.entities.User;
import org.pl.serwis_logownia.entities.jsonUser;
import org.pl.serwis_logownia.services.AuthService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthServiceController {

    AuthService auth = new AuthService();

    @GetMapping("")
    public String showLoginPage() {
        return "In progress";
    }

    @GetMapping("/users")
    public List<User> getUsers() {return auth.getUsers();}

    @GetMapping("/user")
    public User getUser() {return auth.getUser();}

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody jsonUser user, HttpServletResponse response) {
        try {
            ResponseCookie responseCookie = auth.loginUserCookie(user);
            response.addHeader(HttpHeaders.SET_COOKIE, responseCookie.toString());
            return ResponseEntity.ok("Logged in :D");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public void register(@RequestBody jsonUser user) {
        try {
            auth.registerUser(user);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
