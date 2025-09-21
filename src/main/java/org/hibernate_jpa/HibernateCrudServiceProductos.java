package org.hibernate_jpa;

import jakarta.persistence.EntityManager;
import org.hibernate_jpa.entity.Productos;
import org.hibernate_jpa.services.ProductService;
import org.hibernate_jpa.services.ProductosServiceImlp;
import org.hibernate_jpa.util.JpaUtil;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class HibernateCrudServiceProductos {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        EntityManager em = JpaUtil.getEntityManager();

        ProductService serviceprod = new ProductosServiceImlp(em);

        System.out.println("===Listando Productos====");
        List<Productos> listaproductos = serviceprod.listar();
        listaproductos.forEach(System.out::println);

        System.out.println("Obtener producto por id");
        System.out.print("Que registro de producto desea obtener :");
        Long id = sc.nextLong();
        Optional<Productos> searchprodcuct = serviceprod.porId(id);
        searchprodcuct.ifPresent(System.out::println);

        System.out.println("===Guardando un nuevo producto====");

        Productos newproductos = new Productos();
        newproductos.setNombre("Cafe");
        newproductos.setPrecio(15.0);
        newproductos.setDescripcion("Cafe en bolsa");
        newproductos.setCantidad(2.0);

        serviceprod.guardar(newproductos);
        System.out.println("El producto se ha guardado correctamente " +newproductos);
        serviceprod.listar();




    }

}
