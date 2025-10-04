package com.ms.historico.infraestrutura.config.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

class SecurityExceptionHandlerConfigTest {

    private final SecurityExceptionHandlerConfig config = new SecurityExceptionHandlerConfig();
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void authenticationEntryPointShouldReturnUnauthorizedResponse() throws IOException, ServletException {
        AuthenticationEntryPoint entryPoint = config.authenticationEntryPoint(mapper);
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/protected");
        MockHttpServletResponse response = new MockHttpServletResponse();

        entryPoint.commence(request, response, null);

        assertEquals(HttpServletResponse.SC_UNAUTHORIZED, response.getStatus());
        assertEquals("application/json", response.getContentType());
        String body = response.getContentAsString(StandardCharsets.UTF_8);
        assertTrue(body.contains("\"status\":401"));
        assertTrue(body.contains("\"error\":\"Unauthorized\""));
        assertTrue(body.contains("\"path\":\"/protected\""));
    }

    @Test
    void accessDeniedHandlerShouldReturnForbiddenResponse() throws IOException, ServletException {
        AccessDeniedHandler handler = config.accessDeniedHandler(mapper);
        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/restricted");
        MockHttpServletResponse response = new MockHttpServletResponse();

        handler.handle(request, response, null);

        assertEquals(HttpServletResponse.SC_FORBIDDEN, response.getStatus());
        assertEquals("application/json", response.getContentType());
        String body = response.getContentAsString(StandardCharsets.UTF_8);
        assertTrue(body.contains("\"status\":403"));
        assertTrue(body.contains("\"error\":\"Forbidden\""));
        assertTrue(body.contains("\"path\":\"/restricted\""));
    }
}

