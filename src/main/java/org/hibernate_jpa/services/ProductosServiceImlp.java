package org.hibernate_jpa.services;

import jakarta.persistence.EntityManager;
import org.hibernate_jpa.entity.Cliente;
import org.hibernate_jpa.entity.Productos;
import org.hibernate_jpa.repositories.CrudRepository;
import org.hibernate_jpa.repositories.ProductoRepository;

import java.util.List;
import java.util.Optional;

public class ProductosServiceImlp implements ProductService {

    private EntityManager em;
    private CrudRepository<Productos> repo;

    public ProductosServiceImlp(EntityManager em) {
        this.em = em;
        this.repo = new ProductoRepository(em);
    }

    @Override
    public List<Productos> listar(){
        return repo.listar();
    }
    @Override
    public Optional<Productos> porId(Long id) {
        return Optional.ofNullable(repo.porId(id));
    }

    @Override
    public void guardar(Productos producto) {
        try{
            em.getTransaction().begin();
            repo.guardar(producto);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Long id){
        try{
            em.getTransaction().begin();
            repo.eliminar(id);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }


}
