package com.biblioteca.service;

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
    public Libro buscarPorIsbn(Long isbn) {
        return libroRepository.findById(isbn)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado con ISBN: " + isbn));
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
            throw new RuntimeException("Libro no encontrado con ID: " + id);
        }
        libro.setId(id);
        return libroRepository.save(libro);
    }

}
