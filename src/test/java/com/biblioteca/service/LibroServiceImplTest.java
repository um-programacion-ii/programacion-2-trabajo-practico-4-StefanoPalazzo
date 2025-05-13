package com.biblioteca.service;

import com.biblioteca.exception.LibroNoEncontradoException;
import com.biblioteca.interfaces.ILibroRepository;
import com.biblioteca.model.Libro;
import com.biblioteca.model.enums.EstadoLibro;
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
class LibroServiceImplTest {
    @Mock
    private ILibroRepository libroRepository;

    @InjectMocks
    private LibroServiceImpl libroService;

    @Test
    void cuandoBuscarPorIsbnExiste_entoncesRetornaLibro() {
        // Arrange
        String isbn = "123-456-789";
        Libro libroEsperado = new Libro(1L, isbn, "Test Book", "Test Author", EstadoLibro.DISPONIBLE);
        when(libroRepository.findByIsbn(isbn)).thenReturn(Optional.of(libroEsperado));

        // Act
        Libro resultado = libroService.buscarPorIsbn(isbn);

        // Assert
        assertNotNull(resultado);
        assertEquals(isbn, resultado.getIsbn());
        verify(libroRepository).findByIsbn(isbn);
    }

    @Test
    void cuandoBuscarPorIsbnNoExiste_entoncesLanzaExcepcion() {
        // Arrange
        String isbn = "123-456-789";
        when(libroRepository.findByIsbn(isbn)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(LibroNoEncontradoException.class, () ->
                libroService.buscarPorIsbn(isbn)
        );
    }
}