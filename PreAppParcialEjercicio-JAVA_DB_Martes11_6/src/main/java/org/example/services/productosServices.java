package org.example.services;


import org.example.Responses.Response;
import org.example.dtos.productoDto;
import org.example.entities.producto;

import org.hibernate.Session;
import org.example.connections.HibernateUtil;
import org.hibernate.query.Query;

import java.util.List;
import java.util.stream.Collectors;


public class productosServices {

    public Response getProductosConYSinDescuento() {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            String hql = "SELECT p FROM producto p LEFT JOIN p.descuentoList d GROUP BY p.id ORDER BY (p.precio - COALESCE(SUM(d.porcentaje), 0)) DESC";
            Query<producto> query = session.createQuery(hql, producto.class);
            List<producto> productos = query.getResultList();
            session.getTransaction().commit();

            return new Response("Transaccion Exitosa", true, productos.stream()
                    .map(p -> new productoDto(p.getNombre(), p.getPrecio()))
                    .collect(Collectors.toList()));
        } catch (Exception ex) {
            return new Response("Error en la transacción", false, null);
        }
    }

    public Response getProductosSinDescuento() {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            String hql = "SELECT p FROM producto p WHERE p.descuentoList IS EMPTY";
            Query<producto> query = session.createQuery(hql, producto.class);
            List<producto> productos = query.getResultList();
            session.getTransaction().commit();

            return new Response("Transaccion Exitosa", true, productos.stream()
                    .map(p -> new productoDto(p.getNombre(), p.getPrecio()))
                    .collect(Collectors.toList()));
        } catch (Exception ex) {
            return new Response("Error en la transacción", false, null);
        }
    }


    public Response getProductosConMasDeUnDescuento() {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            String hql = "SELECT p FROM producto p JOIN p.descuentoList d GROUP BY p.id HAVING COUNT(d.id) > 1";
            Query<producto> query = session.createQuery(hql, producto.class);
            List<producto> productos = query.getResultList();
            session.getTransaction().commit();

            return new Response("Transaccion Exitosa", true, productos.stream()
                    .map(p -> new productoDto(p.getNombre(), p.getPrecio()))
                    .collect(Collectors.toList()));
        } catch (Exception ex) {
            return new Response("Error en la transacción", false, null);
        }
    }
}
