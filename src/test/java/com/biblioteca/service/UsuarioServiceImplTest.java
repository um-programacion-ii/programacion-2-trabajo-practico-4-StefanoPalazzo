package com.biblioteca.service;

import com.biblioteca.exception.UsuarioNoEncontradoException;
import com.biblioteca.interfaces.IUsuarioRepository;
import com.biblioteca.model.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceImplTest {

    @Mock
    private IUsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Test
    void cuandoBuscarPorDniExiste_entoncesRetornaUsuario() {
        // Arrange
        Long id = 12345678L;
        Usuario usuarioEsperado = new Usuario();
        usuarioEsperado.setId(id);
        when(usuarioRepository.findByDni(id)).thenReturn(Optional.of(usuarioEsperado));

        // Act
        Usuario resultado = usuarioService.buscarPorDni(id);

        // Assert
        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        verify(usuarioRepository).findByDni(id);
    }

    @Test
    void cuandoBuscarPorDniNoExiste_entoncesLanzaExcepcion() {
        // Arrange
        Long dni = 12345678L;
        when(usuarioRepository.findByDni(dni)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UsuarioNoEncontradoException.class, () ->
                usuarioService.buscarPorDni(dni)
        );
    }
}
