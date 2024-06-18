package com.itda.C_TeamProject.controller;

import com.itda.C_TeamProject.jwt.JwtUtil;
import com.itda.C_TeamProject.user.CustomUserDetailsService;
import com.itda.C_TeamProject.user.User;
import com.itda.C_TeamProject.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/itda")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/auth")
    public Map<String, String> authToken(@RequestBody User user) throws Exception {
        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("wrong username or password", e);
        }


        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

        String jwt = jwtUtil.generateToken(userDetails);

        Map<String, String> response = new HashMap<>();
        response.put("jwt", jwt);

        return response;
    }
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.createUser(user);
    }
}
