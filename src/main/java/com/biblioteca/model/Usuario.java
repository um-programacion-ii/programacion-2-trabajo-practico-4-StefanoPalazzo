package com.biblioteca.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import main.java.com.biblioteca.model.enums.EstadoUsuario;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    private Long id;
    private String nombre;
    private String email;
    private EstadoUsuario estado;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
