package com.biblioteca.controller;

import com.biblioteca.model.Libro;
import com.biblioteca.model.enums.EstadoLibro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LibroControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testCrearYObtenerLibro() {
        // Crear libro
        Libro nuevoLibro = new Libro(null, "111-222-333", "Integración", "Autor X", EstadoLibro.DISPONIBLE);
        ResponseEntity<Libro> postResponse = restTemplate.postForEntity("/api/libros", nuevoLibro, Libro.class);
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());

        Libro libroCreado = postResponse.getBody();
        assertNotNull(libroCreado);
        assertNotNull(libroCreado.getId());

        // Obtener por ID
        ResponseEntity<Libro> getResponse = restTemplate.getForEntity("/api/libros/" + libroCreado.getId(), Libro.class);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals("Integración", getResponse.getBody().getTitulo());
    }

    @Test
    void testObtenerTodos() {
        ResponseEntity<Libro[]> response = restTemplate.getForEntity("/api/libros", Libro[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
