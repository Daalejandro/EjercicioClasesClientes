package org.hibernate_jpa;

import jakarta.persistence.EntityManager;
import org.hibernate_jpa.entity.Productos;
import org.hibernate_jpa.services.ProductService;
import org.hibernate_jpa.services.ProductosServiceImlp;
import org.hibernate_jpa.util.JpaUtil;

import javax.swing.*;
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
        serviceprod.listar().forEach(System.out::println);


        System.out.println("Editando un producto");
        System.out.println("Escriba el nombre del producto a editar :");
        String editProd = sc.next();
        Optional<Productos> editProducto = serviceprod.porNombre(editProd);
        editProducto.ifPresent(pro ->{
            System.out.print("Nuevo nombre: ");
            String nuevoNombre = sc.next();
            pro.setNombre(nuevoNombre);
            pro.setCantidad(22.0);
            serviceprod.guardar(pro);
        });



        System.out.println("Eliminando Producto");
        System.out.println("Que registro de producto desea eliminar :");
        long idd = sc.nextLong();
        serviceprod.eliminar(idd);
        System.out.println("El producto se ha eliminado correctamente ");
        serviceprod.listar().forEach(System.out::println);






    }

}
