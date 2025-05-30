package com.biblioteca.repository;

import com.biblioteca.interfaces.ILibroRepository;
import com.biblioteca.model.Libro;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class LibroRepositoryImpl implements ILibroRepository {

    private final Map<Long, Libro> libros = new HashMap<>();
    private Long nextId = 1L;

    @Override
    public Libro save(Libro libro) {
        if (libro.getId() == null) {
            libro.setId(nextId++);
        }
        if (libro.getTitulo() == null || libro.getTitulo().isEmpty()) {
            throw new IllegalArgumentException("El título no puede ser nulo o vacío");
        }

        if (libro.getAutor() == null || libro.getAutor().isEmpty()) {
            throw new IllegalArgumentException("El autor no puede ser nulo o vacío");
        }

        libros.put(libro.getId(), libro);
        return libro;
    }

    @Override
    public Optional<Libro> findById(Long id) {
        return Optional.ofNullable(libros.get(id));
    }

    @Override
    public Optional<Libro> findByIsbn(String isbn) {
        return libros.values().stream()
                .filter(libro -> isbn.equals(libro.getIsbn()))
                .findFirst();

    }

    @Override
    public List<Libro> findAll() {
        return new ArrayList<>(libros.values());
    }

    @Override
    public void deleteById(Long id) {
        libros.remove(id);
    }

    @Override
    public boolean existsById(Long id) {
        return libros.containsKey(id);
    }
}
