package com.biblioteca.interfaces;

import com.biblioteca.model.Prestamo;
import java.util.List;

public interface IPrestamoService {
    Prestamo buscarPorId(Long id);
    List<Prestamo> obtenerTodos();
    Prestamo guardar(Prestamo prestamo);
    void eliminar(Long id);
    Prestamo actualizar(Long id, Prestamo prestamo);
}
