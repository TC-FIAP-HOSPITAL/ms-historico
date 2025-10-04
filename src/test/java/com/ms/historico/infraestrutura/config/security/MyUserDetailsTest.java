package com.ms.historico.infraestrutura.config.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

class MyUserDetailsTest {

    @Test
    void shouldExposeValuesProvidedToBuilder() {
        Long userId = 123L;
        String username = "user@test";
        String password = "secret";
        var authorities = List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));

        MyUserDetails details = MyUserDetails.builder()
                .userId(userId)
                .username(username)
                .password(password)
                .authorities(authorities)
                .build();

        assertEquals(username, details.getUsername());
        assertEquals(password, details.getPassword());
        assertSame(authorities, details.getAuthorities());
        assertEquals(userId, details.getUserId());
        // default contract methods must return true
        assertEquals(true, details.isAccountNonExpired());
        assertEquals(true, details.isAccountNonLocked());
        assertEquals(true, details.isCredentialsNonExpired());
        assertEquals(true, details.isEnabled());
    }
}

