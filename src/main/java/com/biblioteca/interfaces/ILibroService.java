package com.biblioteca.interfaces;
import com.biblioteca.model.Libro;

import java.util.List;

public interface ILibroService {

    Libro buscarPorId(Long isbn);
    List<Libro> obtenerTodos();
    Libro guardar(Libro libro);
    void eliminar(Long id);
    Libro actualizar(Long id, Libro libro);
}




