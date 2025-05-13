package com.biblioteca.controller;

import com.biblioteca.model.Usuario;
import com.biblioteca.model.enums.EstadoLibro;
import com.biblioteca.model.enums.EstadoUsuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsuarioControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testCrearYBuscarUsuario() {
        Usuario nuevoUsuario = new Usuario(null, "Juan", "Perez", EstadoUsuario.ACTIVO);
        ResponseEntity<Usuario> postResponse = restTemplate.postForEntity("/api/usuarios", nuevoUsuario, Usuario.class);
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());

        Usuario creado = postResponse.getBody();
        assertNotNull(creado);
        assertNotNull(creado.getId());

        ResponseEntity<Usuario> getResponse = restTemplate.getForEntity("/api/usuarios/" + creado.getId(), Usuario.class);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals("Juan", getResponse.getBody().getNombre());
    }
}
