package com.ms.historico.infraestrutura.config.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

class SecurityUtilTest {

    private final SecurityUtil securityUtil = new SecurityUtil();

    @AfterEach
    void cleanSecurityContext() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void shouldReturnNullValuesWhenAuthenticationMissing() {
        SecurityContextHolder.clearContext();

        assertNull(securityUtil.getCurrentUsername());
        assertNull(securityUtil.getJwtToken());
        assertNull(securityUtil.getUserId());
        assertEquals(Role.PACIENTE, securityUtil.getRole());
    }

    @Test
    void shouldReturnAuthenticationDetailsWhenPresent() {
        var authorities = List.of(new SimpleGrantedAuthority(Role.ADMIN.toAuthority()));
        MyUserDetails principal = MyUserDetails.builder()
                .userId(7L)
                .username("admin-user")
                .authorities(authorities)
                .build();

        var authentication = new TestingAuthenticationToken(principal, "jwt-token", authorities);
        authentication.setAuthenticated(true);
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        assertEquals("admin-user", securityUtil.getCurrentUsername());
        assertEquals("jwt-token", securityUtil.getJwtToken());
        assertTrue(securityUtil.isAdmin());
        assertEquals(Role.ADMIN, securityUtil.getRole());
        assertEquals(7L, securityUtil.getUserId());
    }

    @Test
    void shouldDelegateToSecurityContextHolderForGetAuthentication() {
        Authentication authentication = mock(Authentication.class);
        SecurityContext context = mock(SecurityContext.class);
        when(context.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(context);

        assertEquals(authentication, securityUtil.getAuthentication());
        verify(context).getAuthentication();
    }

    @Test
    void shouldReturnPacienteRoleWhenAuthoritiesMissing() {
        Authentication authentication = Mockito.mock(Authentication.class);
        when(authentication.getAuthorities()).thenReturn(null);
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);

        assertEquals(Role.PACIENTE, securityUtil.getRole());
    }
}

