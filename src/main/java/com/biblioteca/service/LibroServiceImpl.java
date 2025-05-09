package com.biblioteca.service;

import com.biblioteca.interfaces.ILibroRepository;
import com.biblioteca.interfaces.ILibroService;
import main.java.com.biblioteca.model.Libro;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroServiceImpl implements ILibroService {
    private final ILibroRepository libroRepository;

    public LibroServiceImpl(ILibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public Libro buscarPorIsbn(String isbn) {
        return null;
    }

    @Override
    public List<Libro> obtenerTodos() {
        return List.of();
    }

    @Override
    public Libro guardar(Libro libro) {
        return null;
    }

    @Override
    public void eliminar(Long id) {

    }

    @Override
    public Libro actualizar(Long id, Libro libro) {
        return null;
    }

    // métodos ya implementados
}
