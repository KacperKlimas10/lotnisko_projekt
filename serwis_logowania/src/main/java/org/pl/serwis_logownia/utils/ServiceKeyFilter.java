package org.pl.serwis_logownia.utils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class ServiceKeyFilter extends OncePerRequestFilter {

    @Autowired
    private ServiceKeyValidator serviceKeyValidator;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String serviceKey = request.getHeader("X-Service-Key");

        if (serviceKey == null || !serviceKeyValidator.isValid(serviceKey)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Unauthorized service");
            return;
        }

        filterChain.doFilter(request, response);
    }

}

