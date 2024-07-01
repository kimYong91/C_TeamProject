package com.itda.C_TeamProject.user.controller;

import com.itda.C_TeamProject.user.jwt.JwtUtil;
import com.itda.C_TeamProject.user.data.User;
import com.itda.C_TeamProject.user.service.AuthService;
import com.itda.C_TeamProject.user.service.CustomUserDetailsService;
import com.itda.C_TeamProject.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/itda")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final UserService userService;
    private final AuthService authService;
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
        String refreshToken = jwtUtil.generateRefreshToken(user.getUsername());

        Map<String, String> response = new HashMap<>();
        response.put("jwt", jwt);
        response.put("refreshToken", refreshToken);

        userService.updateRefreshToken(user.getUsername(), refreshToken);

        return response;
    }
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        if (createdUser != null) {
            return ResponseEntity.ok(createdUser);
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestHeader("Refresh-Token") String refreshToken,
                                          @RequestHeader("Username") String username) {
        String newJwt = authService.generateNewToken(refreshToken, username);
        if (newJwt != null) {
            return ResponseEntity.ok(newJwt);
        } else {
            return ResponseEntity.badRequest().body("Invalid refresh token or username");
        }
    }
}
