package com.biblioteca.service;

import com.biblioteca.exception.PrestamoNoEncontradoException;
import com.biblioteca.interfaces.IPrestamoRepository;
import com.biblioteca.model.Prestamo;
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
class PrestamoServiceImplTest {

    @Mock
    private IPrestamoRepository prestamoRepository;

    @InjectMocks
    private PrestamoServiceImpl prestamoService;

    @Test
    void cuandoBuscarPorIdExiste_entoncesRetornaPrestamo() {
        // Arrange
        Long id = 1L;
        Prestamo prestamoEsperado = new Prestamo();
        prestamoEsperado.setId(id);
        when(prestamoRepository.findById(id)).thenReturn(Optional.of(prestamoEsperado));

        // Act
        Prestamo resultado = prestamoService.buscarPorId(id);

        // Assert
        assertNotNull(resultado);
        assertEquals(id, resultado.getId());
        verify(prestamoRepository).findById(id);
    }

    @Test
    void cuandoBuscarPorIdNoExiste_entoncesLanzaExcepcion() {
        // Arrange
        Long id = 1L;
        when(prestamoRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(PrestamoNoEncontradoException.class, () -> prestamoService.buscarPorId(id));
    }
}
