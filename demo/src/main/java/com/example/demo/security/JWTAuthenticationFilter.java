package com.example.demo.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class JWTAuthenticationFilter extends OncePerRequestFilter {

   @Autowired
   JWTGenerator jwtGenerator;

   @Autowired
   CustomUserDetailService customUserDetailService;

    // Paths that don't require a token
    private static final String[] NON_TOKEN_REQUIRED_PATHS = {
            "/api/v1/auth/login",
            "/api/v1/auth/register",
            "/api/v1/auth/logout"
    };

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String requestPath = request.getRequestURI();
        boolean tokenRequired = Arrays.stream(NON_TOKEN_REQUIRED_PATHS)
                .noneMatch(path -> requestPath.startsWith(path));

        if(tokenRequired){
            String token = getJWTFromRequest(request);


            if(jwtGenerator.validateToken(token)){

                String username = jwtGenerator.getUsernameFromJWT(token);

                if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                    UserDetails userDetails = customUserDetailService.loadUserByUsername(username);

                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities()
                    );

                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    private String getJWTFromRequest(HttpServletRequest request){
        String bearerToken = request.getHeader(Constants.TOKEN_HEADER_AUTHORIZATION);
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring("Bearer ".length(), bearerToken.length());
        }
        return null;
    }
}
