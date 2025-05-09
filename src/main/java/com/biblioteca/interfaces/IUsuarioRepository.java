package com.biblioteca.interfaces;

import com.biblioteca.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioRepository {
    Usuario save(Usuario usuario);
    Optional<Usuario> findById(Long id);
    Optional<Usuario> findByDni(String dni);
    List<Usuario> findAll();
    void deleteById(Long id);
    boolean existsById(Long id);
}
