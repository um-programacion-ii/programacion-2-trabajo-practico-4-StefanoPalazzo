package com.biblioteca.repository;

import com.biblioteca.model.Prestamo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        Prestamo guardado = prestamoRepository.save(prestamo);

        Optional<Prestamo> encontrado = prestamoRepository.findById(guardado.getId());
        assertTrue(encontrado.isPresent());
        assertEquals(guardado.getId(), encontrado.get().getId());
    }

    @Test
    void testFindAll() {
        prestamoRepository.save(new Prestamo());
        prestamoRepository.save(new Prestamo());

        List<Prestamo> prestamos = prestamoRepository.findAll();
        assertEquals(2, prestamos.size());
    }

    @Test
    void testEliminar() {
        Prestamo prestamo = prestamoRepository.save(new Prestamo());
        prestamoRepository.deleteById(prestamo.getId());

        assertFalse(prestamoRepository.existsById(prestamo.getId()));
    }

    @Test
    void testExistsById() {
        Prestamo prestamo = prestamoRepository.save(new Prestamo());
        assertTrue(prestamoRepository.existsById(prestamo.getId()));
    }
}
