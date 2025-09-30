package com.biblioteca.interfaces;

import com.biblioteca.model.Usuario;
import java.util.List;

public interface IUsuarioService {
    Usuario buscarPorDni(Long dni);
    List<Usuario> obtenerTodos();
    Usuario guardar(Usuario usuario);
    void eliminar(Long id);
    Usuario actualizar(Long id, Usuario usuario);
}
