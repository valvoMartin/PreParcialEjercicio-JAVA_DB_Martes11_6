package org.example.services;


import org.example.Responses.Response;
import org.example.entities.descuento;
import org.example.entities.producto;
import org.hibernate.Session;
import org.example.connections.HibernateUtil;



public class descuentosServices {

    public Response crearDescuentoParaProducto(int productoId, double porcentaje) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            producto prod = session.get(producto.class, productoId);
            if (prod == null) {
                return new Response("Producto no encontrado", false, null);
            }
            descuento desc = new descuento();
            desc.setProducto(prod);
            desc.setPorcentaje(porcentaje);

            session.save(desc);
            session.getTransaction().commit();

            return new Response("Descuento creado exitosamente", true, null);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new Response("Error en la transacción", false, null);
        }
    }
}
