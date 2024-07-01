package com.itda.C_TeamProject.user.jwt;

import com.itda.C_TeamProject.user.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");

        String jwt = null;
        String username = null;
        String refreshToken = null;

        if (authorization != null && authorization.startsWith("Bearer ")) {
            jwt = authorization.substring(7);
            refreshToken = request.getHeader("Refresh-Token");
            username = jwtUtil.extractUsername(jwt);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            if (jwtUtil.validateToken(jwt, userDetails)) {

                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                authToken.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);

                // 리프레시 토큰이 존재하고 유효한지 확인합니다.
                if (refreshToken != null && jwtUtil.validateRefreshToken(refreshToken, username)) {
                    // 새로운 JWT를 생성합니다.
                    String newJwt = jwtUtil.generateToken(userDetails);

                    // 새로운 JWT를 응답 헤더에 추가합니다.
                    response.setHeader("New-Token", newJwt);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
