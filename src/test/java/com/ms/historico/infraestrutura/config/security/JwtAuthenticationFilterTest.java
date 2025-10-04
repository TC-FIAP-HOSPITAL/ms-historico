package com.ms.historico.infraestrutura.config.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

class JwtAuthenticationFilterTest {

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private Claims claims;

    @InjectMocks
    private JwtAuthenticationFilter filter;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        SecurityContextHolder.clearContext();
    }

    @AfterEach
    void tearDown() throws Exception {
        SecurityContextHolder.clearContext();
        closeable.close();
    }

    @Test
    void shouldSkipWhenAuthorizationHeaderMissing() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain chain = Mockito.mock(FilterChain.class);

        filter.doFilterInternal(request, response, chain);

        verify(chain).doFilter(request, response);
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void shouldSkipWhenTokenInvalid() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Bearer invalid");
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain chain = Mockito.mock(FilterChain.class);
        when(jwtUtil.validateToken("invalid")).thenReturn(false);

        filter.doFilterInternal(request, response, chain);

        verify(chain).doFilter(request, response);
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void shouldAuthenticateUserWhenTokenValid() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Bearer valid-token");
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain chain = Mockito.mock(FilterChain.class);

        when(jwtUtil.validateToken("valid-token")).thenReturn(true);
        when(jwtUtil.extractClaims("valid-token")).thenReturn(claims);
        when(jwtUtil.extractUsername(claims)).thenReturn("john");
        when(jwtUtil.extractRole(claims)).thenReturn(Role.MEDICO);
        when(jwtUtil.extractUserId(claims)).thenReturn("15");

        filter.doFilterInternal(request, response, chain);

        verify(chain).doFilter(request, response);
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        assertEquals("john", authentication.getName());
        assertEquals("valid-token", authentication.getCredentials());
        assertEquals(List.of(Role.MEDICO.toAuthority()), authentication.getAuthorities().stream()
                .map(a -> a.getAuthority()).toList());
        Object principal = authentication.getPrincipal();
        MyUserDetails userDetails = (MyUserDetails) principal;
        assertEquals(15L, userDetails.getUserId());
    }

    @Test
    void shouldHandleNonNumericUserIdGracefully() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Bearer token");
        FilterChain chain = Mockito.mock(FilterChain.class);
        when(jwtUtil.validateToken("token")).thenReturn(true);
        when(jwtUtil.extractClaims("token")).thenReturn(claims);
        when(jwtUtil.extractUsername(claims)).thenReturn("user");
        when(jwtUtil.extractRole(claims)).thenReturn(null);
        when(jwtUtil.extractUserId(claims)).thenReturn("abc");

        filter.doFilterInternal(request, new MockHttpServletResponse(), chain);

        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        assertNull(((MyUserDetails) principal).getUserId());
        assertEquals(List.of(Role.PACIENTE.toAuthority()), principal.getAuthorities().stream()
                .map(a -> a.getAuthority()).toList());
    }

    @Test
    void shouldNotOverrideExistingAuthentication() throws ServletException, IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Bearer valid");
        when(jwtUtil.validateToken("valid")).thenReturn(true);
        when(jwtUtil.extractClaims("valid")).thenReturn(claims);
        when(jwtUtil.extractUsername(claims)).thenReturn("user");

        SecurityContextHolder.getContext().setAuthentication(Mockito.mock(org.springframework.security.core.Authentication.class));
        FilterChain chain = Mockito.mock(FilterChain.class);

        filter.doFilterInternal(request, new MockHttpServletResponse(), chain);

        verify(chain).doFilter(any(HttpServletRequest.class), any(HttpServletResponse.class));
    }
}

