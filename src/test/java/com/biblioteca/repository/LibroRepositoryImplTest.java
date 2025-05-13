package com.biblioteca.repository;

import com.biblioteca.model.Libro;
import com.biblioteca.model.enums.EstadoLibro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LibroRepositoryImplTest {

    private LibroRepositoryImpl libroRepository;

    @BeforeEach
    void setUp() {
        libroRepository = new LibroRepositoryImpl();
    }

    @Test
    void testGuardarYBuscarPorId() {
        Libro libro = new Libro(null, "123-456-789", "Titulo", "Autor", EstadoLibro.DISPONIBLE);
        Libro guardado = libroRepository.save(libro);

        Optional<Libro> encontrado = libroRepository.findById(guardado.getId());

        assertTrue(encontrado.isPresent());
        assertEquals(guardado, encontrado.get());
    }

    @Test
    void testBuscarPorIsbn() {
        Libro libro = new Libro(null, "987-654-321", "Otro Titulo", "Autor", EstadoLibro.PRESTADO);
        libroRepository.save(libro);

        Optional<Libro> encontrado = libroRepository.findByIsbn("987-654-321");

        assertTrue(encontrado.isPresent());
        assertEquals("Otro Titulo", encontrado.get().getTitulo());
    }

    @Test
    void testFindAll() {
        libroRepository.save(new Libro(null, "isbn1", "Libro 1", "Autor 1", EstadoLibro.DISPONIBLE));
        libroRepository.save(new Libro(null, "isbn2", "Libro 2", "Autor 2", EstadoLibro.DISPONIBLE));

        List<Libro> todos = libroRepository.findAll();
        assertEquals(2, todos.size());
    }

    @Test
    void testEliminarPorId() {
        Libro libro = libroRepository.save(new Libro(null, "isbn", "Eliminar", "Autor", EstadoLibro.DISPONIBLE));
        libroRepository.deleteById(libro.getId());

        assertFalse(libroRepository.findById(libro.getId()).isPresent());
    }

    @Test
    void testExistsById() {
        Libro libro = libroRepository.save(new Libro(null, "isbn", "Existente", "Autor", EstadoLibro.DISPONIBLE));
        assertTrue(libroRepository.existsById(libro.getId()));
    }
}
