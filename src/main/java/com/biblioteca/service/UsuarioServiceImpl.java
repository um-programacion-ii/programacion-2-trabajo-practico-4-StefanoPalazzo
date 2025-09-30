package com.biblioteca.service;

import com.biblioteca.exception.UsuarioNoEncontradoException;
import com.biblioteca.model.Usuario;
import com.biblioteca.interfaces.IUsuarioRepository;
import com.biblioteca.interfaces.IUsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
    private final IUsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario buscarPorDni(Long dni) {
        return usuarioRepository.findByDni(dni)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado con DNI: " + dni));
    }

    @Override
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario actualizar(Long id, Usuario usuario) {
        if (!usuarioRepository.existsById(id)) {
            throw new UsuarioNoEncontradoException("Usuario no encontrado con ID: " + id);
        }
        usuario.setId(id);

        return usuarioRepository.save(usuario);
    }
}
