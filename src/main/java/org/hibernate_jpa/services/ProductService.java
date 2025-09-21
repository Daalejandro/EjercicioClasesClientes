package org.hibernate_jpa.services;

import org.hibernate_jpa.entity.Productos;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Productos> listar();
    Optional<Productos> porId(Long id);
    void guardar(Productos producto);
    void eliminar(Long id);
    Optional<Productos> porNombre(String nombre);

}
