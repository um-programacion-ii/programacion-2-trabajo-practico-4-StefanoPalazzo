package com.biblioteca.controller;

import com.biblioteca.model.Libro;
import com.biblioteca.model.Prestamo;
import com.biblioteca.model.Usuario;
import com.biblioteca.model.enums.EstadoLibro;
import com.biblioteca.model.enums.EstadoUsuario;
import com.biblioteca.service.LibroServiceImpl;
import com.biblioteca.service.PrestamoServiceImpl;
import com.biblioteca.service.UsuarioServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PrestamoController.class)
class PrestamoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PrestamoServiceImpl prestamoService;

    @MockBean
    private LibroServiceImpl libroService;

    @MockBean
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("POST /api/prestamos - Crear préstamo exitosamente")
    void testCrearYObtenerPrestamo() throws Exception {
        // Preparación de los objetos mockeados
        Libro libro = new Libro(1L, "999-999-999", "Libro Prestado", "Autor", EstadoLibro.DISPONIBLE);
        Usuario usuario = new Usuario(1L, "Ana", "Lopez", EstadoUsuario.ACTIVO);
        Prestamo prestamo = new Prestamo(null, libro, usuario, LocalDate.now(), null);
        Prestamo prestamoCreado = new Prestamo(1L, libro, usuario, LocalDate.now(), null);

        when(libroService.buscarPorId(1L)).thenReturn(libro);
        when(usuarioService.buscarPorDni(1L)).thenReturn(usuario);
        when(prestamoService.guardar(prestamo)).thenReturn(prestamoCreado);

        // Realizar la solicitud POST
        mockMvc.perform(post("/api/prestamos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(prestamo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(prestamoCreado.getId()))
                .andExpect(jsonPath("$.usuario.nombre").value(usuario.getNombre()))
                .andExpect(jsonPath("$.libro.titulo").value(libro.getTitulo()));

        // Verificar que el servicio fue invocado
        verify(prestamoService).guardar(prestamo);
    }
}
