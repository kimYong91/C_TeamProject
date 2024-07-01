package com.itda.C_TeamProject.user.service;

import com.itda.C_TeamProject.user.data.User;
import com.itda.C_TeamProject.user.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    public String authenticateUser(User user) {
        UserDetails userDetails  = (UserDetails) userService.getUserInfoByUserName(user.getUsername());
        return jwtUtil.generateToken(userDetails );
    }

    public String generateNewToken(String refreshToken, String username) {
        // 리프레시 토큰을 검증하고 새로운 JWT를 발급
        if (refreshToken != null && jwtUtil.validateRefreshToken(refreshToken, username)) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            return jwtUtil.generateToken(userDetails);
        }
        return null;
    }
}
