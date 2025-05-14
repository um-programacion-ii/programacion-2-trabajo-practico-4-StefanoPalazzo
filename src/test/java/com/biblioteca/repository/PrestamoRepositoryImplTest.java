package com.biblioteca.repository;

import com.biblioteca.model.Libro;
import com.biblioteca.model.Prestamo;
import com.biblioteca.model.Usuario;
import com.biblioteca.model.enums.EstadoLibro;
import com.biblioteca.model.enums.EstadoUsuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PrestamoRepositoryImplTest {

    private PrestamoRepositoryImpl prestamoRepository;

    @BeforeEach
    void setUp() {
        prestamoRepository = new PrestamoRepositoryImpl();
    }

    @Test
    void testGuardarYBuscarPorId() {
        Prestamo prestamo = new Prestamo();
        prestamo.setId(1L);
        prestamo.setUsuario(new Usuario(1L, "Juan", "juan@example.com", EstadoUsuario.ACTIVO));
        prestamo.setLibro(new Libro(1L, "1234-5687", "1984", "George Orwell", EstadoLibro.DISPONIBLE));
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo.setFechaDevolucion(LocalDate.now().plusDays(7));
        Prestamo guardado = prestamoRepository.save(prestamo);

        Optional<Prestamo> encontrado = prestamoRepository.findById(guardado.getId());
        assertTrue(encontrado.isPresent());
        assertEquals(guardado.getId(), encontrado.get().getId());
    }

    @Test
    void testFindAll() {
        Prestamo prestamo1 = new Prestamo();
        prestamo1.setId(1L);
        prestamo1.setUsuario(new Usuario(1L, "Juan", "juan@example.com", EstadoUsuario.ACTIVO));
        prestamo1.setLibro(new Libro(1L, "1234-5687", "1984", "George Orwell", EstadoLibro.DISPONIBLE));
        prestamo1.setFechaPrestamo(LocalDate.now());
        prestamo1.setFechaDevolucion(LocalDate.now().plusDays(7));
        prestamoRepository.save(prestamo1);
        Prestamo prestamo2 = new Prestamo();
        prestamo2.setId(2L);
        prestamo2.setUsuario(new Usuario(1L, "Pedro", "Pedro@example.com", EstadoUsuario.ACTIVO));
        prestamo2.setLibro(new Libro(2L, "8234-5687", "Harry Potter", "J.K. Rowling", EstadoLibro.DISPONIBLE));
        prestamo2.setFechaPrestamo(LocalDate.now());
        prestamo2.setFechaDevolucion(LocalDate.now().plusDays(7));
        prestamoRepository.save(prestamo2);

        List<Prestamo> prestamos = prestamoRepository.findAll();
        assertEquals(2, prestamos.size());
    }

    @Test
    void testEliminar() {
        Prestamo prestamo = new Prestamo();
        prestamo.setId(1L);
        prestamo.setUsuario(new Usuario(1L, "Juan", "juan@example.com", EstadoUsuario.ACTIVO));
        prestamo.setLibro(new Libro(1L, "1234-5687", "1984", "George Orwell", EstadoLibro.DISPONIBLE));
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo.setFechaDevolucion(LocalDate.now().plusDays(7));
        prestamoRepository.save(prestamo);
        prestamoRepository.deleteById(prestamo.getId());

        assertFalse(prestamoRepository.existsById(prestamo.getId()));
    }

    @Test
    void testExistsById() {
        Prestamo prestamo = new Prestamo();
        prestamo.setId(1L);
        prestamo.setUsuario(new Usuario(1L, "Juan", "juan@example.com", EstadoUsuario.ACTIVO));
        prestamo.setLibro(new Libro(1L, "1234-5687", "1984", "George Orwell", EstadoLibro.DISPONIBLE));
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo.setFechaDevolucion(LocalDate.now().plusDays(7));
        prestamoRepository.save(prestamo);
        assertTrue(prestamoRepository.existsById(prestamo.getId()));
    }

    @Test
    void testInvalidParameters() {
        // Test cuando el ID es nulo
        Prestamo prestamo = new Prestamo();
        prestamo.setId(null);
        prestamo.setUsuario(new Usuario(1L, "Juan", "Perez@gmail.com", EstadoUsuario.ACTIVO)); // Usuario no nulo
        assertThrows(IllegalArgumentException.class, () -> prestamoRepository.save(prestamo));

        // Test cuando el usuario es nulo
        prestamo.setId(1L);
        prestamo.setUsuario(null); // Usuario nulo
        assertThrows(IllegalArgumentException.class, () -> prestamoRepository.save(prestamo));

        // Test cuando el libro es nulo
        prestamo.setUsuario(new Usuario(1L, "Juan", "Perez@gmail.com", EstadoUsuario.ACTIVO)); // Usuario no nulo
        assertThrows(IllegalArgumentException.class, () -> prestamoRepository.save(prestamo));
    }
}
