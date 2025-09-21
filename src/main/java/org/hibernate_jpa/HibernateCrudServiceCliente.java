package org.hibernate_jpa;

import jakarta.persistence.EntityManager;
import org.hibernate_jpa.entity.Cliente;
import org.hibernate_jpa.services.ClienteService;
import org.hibernate_jpa.services.ClienteServicelmpl;
import org.hibernate_jpa.util.JpaUtil;

import java.util.List;
import java.util.Optional;

public class HibernateCrudServiceCliente {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        ClienteService service = new ClienteServicelmpl(em);

        System.out.println("=== listar ===");
        List<Cliente> clientes = service.listar();
        clientes.forEach(System.out::println);

        System.out.println("=== Obtener por id ===");
        Optional<Cliente> optionalCliente = service.porId(1L);
        optionalCliente.ifPresent(System.out::println);

        System.out.println("=== Insertar nuevo cliente ===");
        Cliente cliente = new Cliente();
        cliente.setNombre("Jose");
        cliente.setApellido("Lopez");
        cliente.setFormaPago("paypal");

        service.guardar(cliente);
        System.out.println("Cliente guardado exitosamente");
        service.listar().forEach(System.out::println);

        System.out.println("=== Editar Cliente ===");
        Long id = cliente.getId();
        optionalCliente = service.porId(id);
        optionalCliente.ifPresent(c ->{
            c.setApellido("mercado pago");
            service.guardar(c);
            System.out.println("Cliente editado exitosamente");
            service.listar().forEach(System.out::println);
        });

        System.out.println("=== Eliminar Cliente ===");
        id =cliente.getId();
        optionalCliente = service.porId(id);
        optionalCliente.ifPresent(c ->{
            service.eliminar(c.getId());
            System.out.println("Cliente eliminado exitosamente");
            service.listar().forEach(System.out::println);
        });

        em.close();
    }
}