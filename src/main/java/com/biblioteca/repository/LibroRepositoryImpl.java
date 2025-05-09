package com.biblioteca.repository;

import com.biblioteca.model.Libro;

public interface LibroRepository {
    save(Libro libro);
    Optional<Libro> findById(Long id);
    Optional<Libro> findByIsbn(String isbn);
    List<Libro> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}
