package com.winwin.travel.dataapi.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public final class InternalTokenFilter extends OncePerRequestFilter {

    private static final String INTERNAL_TOKEN = "X-Internal-Token";

    private final String internalToken;

    public InternalTokenFilter(final @Value("${internal.token}") String internalToken) {
        this.internalToken = internalToken;
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader(INTERNAL_TOKEN);

        if (header == null) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden");
            return;
        }

        if (!header.equals(this.internalToken)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden");
        }

        filterChain.doFilter(request, response);
    }
}
