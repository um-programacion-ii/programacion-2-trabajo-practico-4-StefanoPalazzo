package com.biblioteca.controller;

import com.biblioteca.model.Prestamo;
import com.biblioteca.interfaces.IPrestamoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final IPrestamoService prestamoService;

    public PrestamoController(IPrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    // -- Métodos CRUD para la entidad Prestamo --
    // Metodo para obtener todos los prestamos
    // @return Lista de prestamos
    @GetMapping
    public List<Prestamo> obtenerTodos() {
        return prestamoService.obtenerTodos();
    }

    // Metodo para obtener un prestamo por su ID
    // @param id ID del prestamo
    // @return Prestamo encontrado
    @GetMapping("/{id}")
    public Prestamo obtenerPorId(@PathVariable Long id) {
        return prestamoService.buscarPorId(id);
    }

    // Metodo para crear un nuevo prestamo
    // @param prestamo Prestamo a crear
    // @return Prestamo creado
    @PostMapping
    public Prestamo crear(@RequestBody Prestamo prestamo) {
        return prestamoService.guardar(prestamo);
    }

    // Metodo para actualizar un prestamo existente
    // @param id ID del prestamo a actualizar
    // @param prestamo Prestamo con los nuevos datos
    @PutMapping("/{id}")
    public Prestamo actualizar(@PathVariable Long id, @RequestBody Prestamo prestamo) {
        return prestamoService.actualizar(id, prestamo);
    }

    // Metodo para eliminar un prestamo por su ID
    // @param id ID del prestamo a eliminar
    // @return void
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        prestamoService.eliminar(id);
    }
}
