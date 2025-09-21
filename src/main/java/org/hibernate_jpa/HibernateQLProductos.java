package org.hibernate_jpa;

import jakarta.persistence.EntityManager;
import org.hibernate_jpa.dominio.ClienteDto;
import org.hibernate_jpa.dominio.ProductoDto;
import org.hibernate_jpa.util.JpaUtil;

import java.util.List;

public class HibernateQLProductos {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        System.out.println("========== consulta por campos personalizados ===========");
        Object[] objetoProducto = em.createQuery("select p.id, p.nombre,p.precio from Productos p where p.id=:id", Object[].class)
                .setParameter("id", 1L)
                .getSingleResult();
        Long id = (Long) objetoProducto[0];
        String nombre = (String) objetoProducto[1];
        Double precio = (Double) objetoProducto[2];
        System.out.println("id=" + id + ",nombre=" + nombre + ",precio $=" + precio);

        System.out.println("========== consulta que puebla y devuelve objeto otro de una clase personalizada ==========");
        List<ProductoDto> productoDtos = em.createQuery("select new org.hibernate_jpa.dominio.ProductoDto(p.nombre, p.precio) from Productos p", ProductoDto.class)
                .getResultList();
        productoDtos.forEach(System.out::println);

        System.out.println("========== consultas resumen funciones agregaciones count min max avg sum ==========");
        Object[] estadisticas = em.createQuery("select min(c.id), max(c.id), sum(c.id), count(c.id), avg(length(c.nombre)) from Productos c", Object[].class)
                .getSingleResult();
        Long min = (Long) estadisticas[0];
        Long max = (Long) estadisticas[1];
        Long sum = (Long) estadisticas[2];
        Long count = (Long) estadisticas[3];
        Double avg = (Double) estadisticas[4];
        System.out.println("min=" + min + ", max=" + max + ", sum=" + sum + ", count=" + count + ", avg=" + avg);


    }
}
