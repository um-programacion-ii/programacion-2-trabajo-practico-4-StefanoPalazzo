package com.biblioteca.controller;

import com.biblioteca.model.Libro;
import com.biblioteca.interfaces.ILibroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    private final ILibroService libroService;

    public LibroController(ILibroService libroService) {
        this.libroService = libroService;
    }

    // -- Métodos CRUD para la entidad Libro --
    // Metodo para obtener todos los libros
    // @return Lista de libros
    @GetMapping
    public List<Libro> obtenerTodos() {
        return libroService.obtenerTodos();
    }

    // Metodo para obtener un libro por su ID
    // @param id ID del libro
    // @return Libro encontrado
    @GetMapping("/{id}")
    public Libro obtenerPorId(@PathVariable Long id) {
        return libroService.buscarPorId(id);
    }

    // Metodo para crear un nuevo libro
    // @param libro Libro a crear
    // @return Libro creado
    @PostMapping
    public Libro crear(@RequestBody Libro libro) {
        return libroService.guardar(libro);
    }

    // Metodo para actualizar un libro existente
    // @param id ID del libro a actualizar
    // @param libro Libro con los nuevos datos
    // @return Libro actualizado
    @PutMapping("/{id}")
    public Libro actualizar(@PathVariable Long id, @RequestBody Libro libro) {
        return libroService.actualizar(id, libro);
    }

    // Metodo para eliminar un libro por su ID
    // @param id ID del libro a eliminar
    // @return void
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        libroService.eliminar(id);
    }
}
