package com.hmsapp.config;

import com.hmsapp.entity.User;
import com.hmsapp.repository.UserRepository;
import com.hmsapp.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private JWTService jwtService;
    private UserRepository userRepository;

    public JwtFilter(JWTService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Implement JWT token validation and authorization logic here
        String token = request.getHeader("Authorization");
        System.out.println(token);
        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken=token.substring(7, token.length());
            System.out.println(jwtToken);
            String username = jwtService.getUsername(jwtToken);
            System.out.println(username);
            Optional<User> opUser = userRepository.findByUsername(username);
            if (opUser.isPresent()) {
                User user = opUser.get();
                UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(user, null, Collections.singleton(new SimpleGrantedAuthority(user.getRole())));
                userToken.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(userToken);
            }

        }
        filterChain.doFilter(request,response);

            //String jwtToken = token.substring(7);
            //System.out.println(jwtToken);
//            String username = jwtService.getUsername(jwtToken);
//            System.out.println(username);
//            Optional<User> opUser = userRepository.findByUsername(username);
//            if (opUser.isPresent()) {
//                User user = opUser.get();
//                UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(user, null, null);
//                userToken.setDetails(new WebAuthenticationDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(userToken);
//            }
//        //}
//        filterChain.doFilter(request, response); //incoming request is coming that incoming request will be internsent to the internal filter mechanism of your spring security
    }

}
