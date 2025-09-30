package com.biblioteca.interfaces;

import com.biblioteca.model.Prestamo;

import java.util.List;
import java.util.Optional;

public interface IPrestamoRepository {
    Prestamo save(Prestamo prestamo);
    Optional<Prestamo> findById(Long id);
    List<Prestamo> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}
