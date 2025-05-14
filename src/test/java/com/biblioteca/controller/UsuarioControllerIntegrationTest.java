package com.biblioteca.controller;

import com.biblioteca.exception.UsuarioNoEncontradoException;
import com.biblioteca.model.Usuario;
import com.biblioteca.model.enums.EstadoUsuario;
import com.biblioteca.service.UsuarioServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
class UsuarioControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("POST /api/usuarios - crear usuario exitosamente")
    void crearUsuario_ok() throws Exception {
        Usuario nuevo = new Usuario(null, "Juan", "Perez@gmail.com", EstadoUsuario.ACTIVO);
        Usuario creado = new Usuario(1L, "Juan", "Perez@gmail.com", EstadoUsuario.ACTIVO);

        when(usuarioService.guardar(any(Usuario.class))).thenReturn(creado);

        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nuevo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(creado.getId()))
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.email").value("Perez@gmail.com"))
                .andExpect(jsonPath("$.estado").value("ACTIVO"));

        verify(usuarioService).guardar(any(Usuario.class));
    }

    @Test
    @DisplayName("GET /api/usuarios/{id} - usuario no encontrado")
    void buscarUsuario_noExiste() throws Exception {
        Long id = 99L;
        when(usuarioService.buscarPorDni(id)).thenThrow(new UsuarioNoEncontradoException("Usuario no encontrado"));

        mockMvc.perform(get("/api/usuarios/{id}", id))
                .andExpect(status().isNotFound());

        verify(usuarioService).buscarPorDni(id);
    }

}
