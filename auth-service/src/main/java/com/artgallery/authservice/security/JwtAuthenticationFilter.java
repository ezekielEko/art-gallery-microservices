package com.artgallery.authservice.security;

import com.artgallery.authservice.entity.User;
import com.artgallery.authservice.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String authheader = request.getHeader("Authorization");

        if(authheader == null || !authheader.startsWith("Bearer")){
            filterChain.doFilter(request, response);
            return;
        }

        String token = authheader.substring(7);
        String email = jwtService.extractEmail(token);


        if (email != null && SecurityContextHolder.getContext().getAuthentication()== null){
            User user = userRepository.findByEmail(email)
                    .orElse(null);

            if (user != null && jwtService.isTokenValid(token)){
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                user.getEmail(),
                                null,
                                List.of(new SimpleGrantedAuthority("ROLE_"+user.getRole()))
                        );
                SecurityContextHolder.getContext().setAuthentication(authToken);

            }
        }

        filterChain.doFilter(request, response);

    }
}
