package com.ms.historico.infraestrutura.config.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

class JwtUtilTest {

    private static final String SECRET = "z0kRWl7lN6qQcv1H5mV2eA9sT4yUi8oPq3rXbDfZhLgFjSkA";

    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() throws Exception {
        jwtUtil = new JwtUtil();
        Field field = JwtUtil.class.getDeclaredField("jwtSecret");
        field.setAccessible(true);
        field.set(jwtUtil, SECRET);
    }

    @Test
    void shouldGenerateAndValidateTokenWithUserId() {
        String token = jwtUtil.generateToken("ROLE_ADMIN", "42");

        assertNotNull(token);
        assertTrue(jwtUtil.validateToken(token));

        Claims claims = jwtUtil.extractClaims(token);
        assertEquals("ROLE_ADMIN", jwtUtil.extractUsername(claims));
        assertEquals("42", jwtUtil.extractUserId(claims));
    }

    @Test
    void shouldExtractRoleFromClaim() {
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("role", "MEDICO");
        Claims claims = Jwts.claims(claimsMap);
        claims.setSubject("user");

        assertEquals(Role.MEDICO, jwtUtil.extractRole(claims));
    }

    @Test
    void shouldExtractRoleFromSubjectWhenClaimMissing() {
        String token = jwtUtil.generateToken("ROLE_MEDICO", "9");
        Claims claims = jwtUtil.extractClaims(token);

        assertEquals(Role.MEDICO, jwtUtil.extractRole(claims));
    }

    @Test
    void shouldReturnFalseForInvalidToken() {
        assertFalse(jwtUtil.validateToken("invalid-token"));
    }
}
