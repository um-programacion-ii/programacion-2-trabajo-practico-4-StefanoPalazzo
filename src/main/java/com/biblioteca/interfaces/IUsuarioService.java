package com.biblioteca.interfaces;

import com.biblioteca.model.Usuario;
import java.util.List;

public interface IUsuarioService {
    Usuario buscarPorDni(String dni);
    List<Usuario> obtenerTodos();
    Usuario guardar(Usuario usuario);
    void eliminar(Long id);
    Usuario actualizar(Long id, Usuario usuario);
}
