package com.biblioteca.repository;

import com.biblioteca.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioRepositoryImplTest {

    private UsuarioRepositoryImpl usuarioRepository;

    @BeforeEach
    void setUp() {
        usuarioRepository = new UsuarioRepositoryImpl();
    }

    @Test
    void testGuardarYBuscarPorDni() {
        Usuario usuario = new Usuario();
        usuario.setId(12345678L);
        Usuario guardado = usuarioRepository.save(usuario);

        Optional<Usuario> encontrado = usuarioRepository.findByDni(12345678L);
        assertTrue(encontrado.isPresent());
        assertEquals(guardado.getId(), encontrado.get().getId());
    }

    @Test
    void testFindAll() {
        Usuario u1 = new Usuario();
        u1.setId(111L);
        Usuario u2 = new Usuario();
        u2.setId(222L);
        usuarioRepository.save(u1);
        usuarioRepository.save(u2);

        List<Usuario> usuarios = usuarioRepository.findAll();
        assertEquals(2, usuarios.size());
    }

    @Test
    void testEliminar() {
        Usuario usuario = new Usuario();
        usuario.setId(333L);
        Usuario guardado = usuarioRepository.save(usuario);

        usuarioRepository.deleteById(guardado.getId());

        assertFalse(usuarioRepository.existsById(guardado.getId()));
    }

    @Test
    void testExistsById() {
        Usuario usuario = new Usuario();
        usuario.setId(444L);
        Usuario guardado = usuarioRepository.save(usuario);

        assertTrue(usuarioRepository.existsById(guardado.getId()));
    }
}
