package com.healthcare.healthcare.SecurityFilters;

import com.healthcare.healthcare.Services.CustomUserDetailsService;
import com.healthcare.healthcare.Utility.JwtUtility;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.ott.InvalidOneTimeTokenException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

@AllArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {

    JwtUtility jwtUtility;
    CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            if (authHeader.startsWith("Bearer")) {

                String jwtToken = request.getHeader("Authorization").split(" ")[1];

                String enteredEmail = jwtUtility.getClaims(jwtToken).getSubject();

                UserDetails userDetails = customUserDetailsService.loadUserByUsername(enteredEmail);
                System.out.println(userDetails.getUsername());
                if(userDetails != null && jwtUtility.validateTokenExpiry(jwtToken)){
                    String email = userDetails.getUsername();
                    String password = userDetails.getPassword();
                    Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password, authorities);
                    System.out.println(authToken);
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
                else throw new ExpiredJwtException(jwtUtility.getHeader(jwtToken), jwtUtility.getClaims(jwtToken), "The token is expired or invalid");

            }
//            else throw new InvalidOneTimeTokenException("The provided token is not a jwt token");
        }
        filterChain.doFilter(request, response);
    }
}
