package org.example.middleware;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// @Component annotation makes this class a Spring Bean, so it will be automatically registered
// in the Spring context and can be used for dependency injection.
@Component
// The class extends OncePerRequestFilter, which ensures that the filter is only
// invoked once per request.
public class MyMiddleware extends OncePerRequestFilter {

    // Middleware is a piece of software that sits between the request and response,
    // often used to handle tasks such as logging, authentication, or
    // request/response modification.
    // It can be thought of as a gatekeeper that intercepts HTTP requests before
    // they reach the target resource
    // and can also modify the response before sending it back to the client.
    // Middleware helps to separate concerns, making code cleaner, more reusable,
    // and easier to maintain.

    @Override // The doFilterInternal method is overridden from OncePerRequestFilter to
              // implement custom filtering logic.
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        // Log the HTTP method (e.g., GET, POST) of the incoming request.
        System.out.println("Request Method: " + request.getMethod());
        // Log the URI of the incoming request (path and query parameters).
        System.out.println("Request URI: " + request.getRequestURI());

        // Continue the filter chain by calling doFilter with the request and response.
        // This allows the request to continue processing through any other filters or
        // reach the target resource.
        filterChain.doFilter(request, response);
    }

}