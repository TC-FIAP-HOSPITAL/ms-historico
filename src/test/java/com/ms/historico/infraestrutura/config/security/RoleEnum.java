package com.ms.historico.infraestrutura.config.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Test
    void testToAuthority_Admin() {
        assertEquals("ROLE_ADMIN", Role.ADMIN.toAuthority());
    }

    @Test
    void testToAuthority_Medico() {
        assertEquals("ROLE_MEDICO", Role.MEDICO.toAuthority());
    }

    @Test
    void testToAuthority_Enfermeiro() {
        assertEquals("ROLE_ENFERMEIRO", Role.ENFERMEIRO.toAuthority());
    }

    @Test
    void testToAuthority_Paciente() {
        assertEquals("ROLE_PACIENTE", Role.PACIENTE.toAuthority());
    }

    @Test
    void testFromAuthority_Admin() {
        assertEquals(Role.ADMIN, Role.fromAuthority("ROLE_ADMIN"));
    }

    @Test
    void testFromAuthority_Medico() {
        assertEquals(Role.MEDICO, Role.fromAuthority("ROLE_MEDICO"));
    }

    @Test
    void testFromAuthority_Enfermeiro() {
        assertEquals(Role.ENFERMEIRO, Role.fromAuthority("ROLE_ENFERMEIRO"));
    }

    @Test
    void testFromAuthority_Paciente() {
        assertEquals(Role.PACIENTE, Role.fromAuthority("ROLE_PACIENTE"));
    }

    @Test
    void testFromAuthority_WithoutRolePrefix() {
        assertEquals(Role.ADMIN, Role.fromAuthority("ADMIN"));
        assertEquals(Role.MEDICO, Role.fromAuthority("MEDICO"));
    }

    @Test
    void testFromAuthority_InvalidAuthority_ShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> Role.fromAuthority("INVALID_ROLE"));
    }
}