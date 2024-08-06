package org.example;

import org.example.Responses.Response;
import org.example.dtos.productoDto;
import org.example.services.descuentosServices;
import org.example.services.productosServices;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static productosServices productosService = new productosServices();
    private static descuentosServices descuentosService = new descuentosServices();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Obtener productos CON y SIN descuento, ordenando diferencia entre ambos valores de mayor a menor.");
            System.out.println("2. Obtener productos que NO tengan ningún descuento.");
            System.out.println("3. Crear descuentos para un producto determinado pasado como parámetro.");
            System.out.println("4. Retornar los productos que tienen más de un descuento cargado.");
            System.out.println("5. Salir.");


            System.out.println("Elija su opcion:");
            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    Response response1 = productosService.getProductosConYSinDescuento();
                    if(response1.success){
                        System.out.println(response1.message);
                        List<productoDto> productos = (List<productoDto>) response1.results;
                        for (productoDto producto : productos) {
                            System.out.println(producto.getNombre() + " " + producto.getPrecio());
                        }
                    } else {
                        System.out.println("Error al obtener los productos.");
                    }

                    break;
                case 2:
                    Response response2 = productosService.getProductosSinDescuento();
                    if(response2.success){
                        List<productoDto> productos = (List<productoDto>) response2.results;
                        System.out.println(response2.message);
                        for (productoDto producto : productos) {
                            System.out.println(producto.getNombre() + " " + producto.getPrecio());
                        }
                    } else {
                        System.out.println("Error al obtener los productos.");
                    }


                    break;
                case 3:
                    System.out.println("Ingrese el ID del producto:");
                    int productoId = scanner.nextInt();
                    System.out.println("Ingrese el porcentaje de descuento:");
                    double porcentaje = scanner.nextDouble();
                    Response response3 = descuentosService.crearDescuentoParaProducto(productoId, porcentaje);
                    System.out.println(response3.message);
                    break;
                case 4:
                    Response response4 = productosService.getProductosConMasDeUnDescuento();
                    System.out.println(response4.message);
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}

