package com.biblioteca.controller;

import com.biblioteca.model.Usuario;
import com.biblioteca.interfaces.IUsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final IUsuarioService usuarioService;

    public UsuarioController(IUsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // -- Métodos CRUD para la entidad Usuario --
    // Metodo para obtener todos los usuarios
    // @return Lista de usuarios
    @GetMapping
    public List<Usuario> obtenerTodos() {
        return usuarioService.obtenerTodos();
    }

    // Metodo para obtener un usuario por su ID
    // @param id ID del usuario
    // @return Usuario encontrado
    @GetMapping("/{id}")
    public Usuario obtenerPorId(@PathVariable Long id) {
        return usuarioService.buscarPorDni(id);
    }

    // Metodo para crear un nuevo usuario
    // @param usuario Usuario a crear
    // @return Usuario creado
    @PostMapping
    public Usuario crear(@RequestBody Usuario usuario) {
        return usuarioService.guardar(usuario);
    }

    // Metodo para actualizar un usuario existente
    // @param id ID del usuario a actualizar
    // @param usuario Usuario con los nuevos datos
    @PutMapping("/{id}")
    public Usuario actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        return usuarioService.actualizar(id, usuario);
    }

    // Metodo para eliminar un usuario por su ID
    // @param id ID del usuario a eliminar
    // @return void
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
    }
}
