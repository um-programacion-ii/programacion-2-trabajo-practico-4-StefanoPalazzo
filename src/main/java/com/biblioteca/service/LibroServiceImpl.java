package com.biblioteca.service;

import com.biblioteca.exception.LibroNoEncontradoException;
import com.biblioteca.interfaces.ILibroRepository;
import com.biblioteca.interfaces.ILibroService;
import com.biblioteca.model.Libro;
import com.biblioteca.model.Prestamo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroServiceImpl implements ILibroService {
    private final ILibroRepository libroRepository;

    public LibroServiceImpl(ILibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public Libro buscarPorId(Long id) {
        return libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con ID: " + id));
    }

    public Libro buscarPorIsbn(String isbn) {
        return libroRepository.findByIsbn(isbn)
                .orElseThrow(() -> new LibroNoEncontradoException("Libro no encontrado con ID: " + isbn));
    }

    @Override
    public List<Libro> obtenerTodos() {return libroRepository.findAll();}

    @Override
    public Libro guardar(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    public void eliminar(Long id) { libroRepository.deleteById(id); }


    @Override
    public Libro actualizar(Long id, Libro libro) {
        if (!libroRepository.existsById(id)) {
            throw new LibroNoEncontradoException("Libro no encontrado con ID: " + id);
        }
        libro.setId(id);
        return libroRepository.save(libro);
    }

}
