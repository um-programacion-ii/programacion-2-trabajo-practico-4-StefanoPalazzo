package com.biblioteca.controller;

import com.biblioteca.model.Libro;
import com.biblioteca.model.enums.EstadoLibro;
import com.biblioteca.service.LibroServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LibroController.class)
class LibroControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibroServiceImpl libroService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("POST /api/libros - Crear libro exitosamente")
    void testCrearYObtenerLibro() throws Exception {
        // Crear libro
        Libro nuevoLibro = new Libro(null, "111-222-333", "Integración", "Autor X", EstadoLibro.DISPONIBLE);
        Libro libroCreado = new Libro(1L, "111-222-333", "Integración", "Autor X", EstadoLibro.DISPONIBLE);

        when(libroService.guardar(nuevoLibro)).thenReturn(libroCreado);

        // Realizar la solicitud POST
        mockMvc.perform(post("/api/libros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nuevoLibro)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(libroCreado.getId()))
                .andExpect(jsonPath("$.titulo").value("Integración"))
                .andExpect(jsonPath("$.autor").value("Autor X"))
                .andExpect(jsonPath("$.estado").value("DISPONIBLE"));

        // Verificar que el servicio fue invocado
        verify(libroService).guardar(nuevoLibro);
    }

    @Test
    @DisplayName("GET /api/libros/{id} - Obtener libro por ID")
    void testObtenerLibroPorId() throws Exception {
        // Preparar el libro que queremos obtener
        Libro libro = new Libro(1L, "111-222-333", "Integración", "Autor X", EstadoLibro.DISPONIBLE);

        when(libroService.buscarPorId(1L)).thenReturn(libro);

        // Realizar la solicitud GET
        mockMvc.perform(get("/api/libros/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Integración"))
                .andExpect(jsonPath("$.autor").value("Autor X"))
                .andExpect(jsonPath("$.estado").value("DISPONIBLE"));

        // Verificar que el servicio fue invocado
        verify(libroService).buscarPorId(1L);
    }

    @Test
    @DisplayName("GET /api/libros - Obtener todos los libros")
    void testObtenerTodos() throws Exception {
        // Crear algunos libros
        Libro libro1 = new Libro(1L, "111-222-333", "Integración", "Autor X", EstadoLibro.DISPONIBLE);
        Libro libro2 = new Libro(2L, "444-555-666", "Java Avanzado", "Autor Y", EstadoLibro.PRESTADO);

        when(libroService.obtenerTodos()).thenReturn(List.of(libro1, libro2));

        // Realizar la solicitud GET
        mockMvc.perform(get("/api/libros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Integración"))
                .andExpect(jsonPath("$[1].titulo").value("Java Avanzado"));

        // Verificar que el servicio fue invocado
        verify(libroService).obtenerTodos();
    }
}
