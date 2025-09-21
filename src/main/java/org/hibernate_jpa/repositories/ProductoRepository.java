package org.hibernate_jpa.repositories;

import jakarta.persistence.EntityManager;
import org.hibernate_jpa.entity.Productos;

import java.util.List;

public class ProductoRepository implements CrudRepository<Productos>{

    private EntityManager em;

    public ProductoRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Productos> listar(){
        return em.createQuery("select p from Productos p", Productos.class).getResultList();
    }
    @Override
    public Productos porId(Long id){
        return em.find(Productos.class, id);
    }

    @Override
    public void guardar(Productos producto) {
        if(producto.getId() != null && producto.getId()<0){
            em.merge(producto);
        }else
            em.persist(producto);
    }

    @Override
    public void eliminar(Long id){
        Productos productos = porId(id);
        em.remove(productos);
    }

}
