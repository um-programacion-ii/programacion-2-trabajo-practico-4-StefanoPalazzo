package com.biblioteca.repository;

import com.biblioteca.interfaces.ILibroRepository;
import com.biblioteca.model.Libro;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class LibroRepositoryImpl implements ILibroRepository {
    private final Map<Long, Libro> libros = new HashMap<>();
    private Long nextId = 1L;

    @Override
    public Libro save(Libro libro) {
        return null;
    }

    @Override
    public Optional<Libro> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Libro> findByIsbn(String isbn) {
        return Optional.empty();
    }

    @Override
    public List<Libro> findAll() {
        return List.of();
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }

}

