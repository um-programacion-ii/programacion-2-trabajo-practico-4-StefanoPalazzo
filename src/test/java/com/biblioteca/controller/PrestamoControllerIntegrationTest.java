package com.biblioteca.controller;

import com.biblioteca.model.Libro;
import com.biblioteca.model.Prestamo;
import com.biblioteca.model.Usuario;
import com.biblioteca.model.enums.EstadoLibro;
import com.biblioteca.model.enums.EstadoUsuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PrestamoControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testCrearYObtenerPrestamo() {
        // Crear libro
        Libro libro = new Libro(null, "999-999-999", "Libro Prestado", "Autor", EstadoLibro.DISPONIBLE);
        libro = restTemplate.postForEntity("/api/libros", libro, Libro.class).getBody();

        // Crear usuario
        Usuario usuario = new Usuario(null, "Ana", "Lopez", EstadoUsuario.ACTIVO);
        usuario = restTemplate.postForEntity("/api/usuarios", usuario, Usuario.class).getBody();

        // Crear préstamo
        Prestamo prestamo = new Prestamo(null, libro, usuario, LocalDate.now(), null);
        ResponseEntity<Prestamo> postResponse = restTemplate.postForEntity("/api/prestamos", prestamo, Prestamo.class);
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());

        Prestamo creado = postResponse.getBody();
        assertNotNull(creado);
        assertEquals(usuario.getNombre(), creado.getUsuario().getNombre());
    }
}
