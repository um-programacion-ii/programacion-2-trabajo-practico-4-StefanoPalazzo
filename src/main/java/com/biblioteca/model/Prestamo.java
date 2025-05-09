package com.biblioteca.model;

import com.biblioteca.model.Libro;
import com.biblioteca.model.Usuario;


import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prestamo {
    private Long id;
    private Libro libro;
    private Usuario usuario;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    // Lombok no me generaba estos getters y setters
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
