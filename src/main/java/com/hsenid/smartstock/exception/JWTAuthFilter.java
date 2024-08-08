//package com.hsenid.smartstock.exception;
//
//
//import com.hsenid.smartstock.service.UserDetailService;
//import com.hsenid.smartstock.utils.JWTUtils;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//public class JWTAuthFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private JWTUtils jwtUtils;
//
//    @Autowired
//    private UserDetailService userDetailService;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain filterChain)
//            throws ServletException, IOException {
//
//        // Retrieve the Authorization header from the request
//        final String authorizationHeader = request.getHeader("Authorization");
//        String jwtToken = null;
//        String userEmail = null;
//
//        try {
//            // Check if the Authorization header is present and well-formed
//            if (authorizationHeader != null && !authorizationHeader.isBlank() && authorizationHeader.startsWith("Bearer ")) {
//                // Extract the JWT token from the header
//                jwtToken = authorizationHeader.substring(7);
//                // Extract the username (email) from the JWT token
//                userEmail = jwtUtils.extractUsername(jwtToken);
//            }
//
//            // Check if the username is extracted and no authentication is set in the security context
//            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                // Load user details using the username
//                UserDetails userDetails = userDetailService.loadUserByUsername(userEmail);
//
//                // Validate the token
//                if (jwtUtils.isTokenValid(jwtToken, userDetails)) {
//                    // Create an authentication token
//                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//                            userDetails,
//                            null,
//                            userDetails.getAuthorities()
//                    );
//                    // Set authentication details in the token
//                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                    // Set the authentication token in the security context
//                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//                }
//            }
//        } catch (Exception e) {
//            // Log the exception (can be replaced with a logger)
//            e.printStackTrace();
//        }
//
//        // Proceed with the next filter in the chain
//        filterChain.doFilter(request, response);
//    }
//}



package com.hsenid.smartstock.exception;

import com.hsenid.smartstock.service.UserDetailService;
import com.hsenid.smartstock.utils.JWTUtils;
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
public class JWTAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private UserDetailService userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Retrieve the Authorization header from the request
        final String authorizationHeader = request.getHeader("Authorization");
        String jwtToken = null;
        String userEmail = null;

        try {
            // Check if the Authorization header is present and well-formed
            if (authorizationHeader != null && !authorizationHeader.isBlank() && authorizationHeader.startsWith("Bearer ")) {
                // Extract the JWT token from the header
                jwtToken = authorizationHeader.substring(7);
                // Extract the username (email) from the JWT token
                userEmail = jwtUtils.extractUsername(jwtToken);
            }

            // Check if the username is extracted and no authentication is set in the security context
            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // Load user details using the username
                UserDetails userDetails = userDetailService.loadUserByUsername(userEmail);

                // Validate the token
                if (jwtUtils.isTokenValid(jwtToken, userDetails)) {
                    // Create an authentication token
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    // Set authentication details in the token
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // Set the authentication token in the security context
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        } catch (Exception e) {
            // Log the exception (can be replaced with a logger)
            e.printStackTrace();
        }

        // Proceed with the next filter in the chain
        filterChain.doFilter(request, response);
    }
}
