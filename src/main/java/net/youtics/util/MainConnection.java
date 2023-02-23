package net.youtics.util;

import net.youtics.models.Categoria;
import net.youtics.models.Producto;
import net.youtics.repositorio.ProductoRepositorio;
import net.youtics.repositorio.Repositorio;

import java.sql.Connection;
import java.util.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class MainConnection {
    public static void main(String[] args) {

        Long id;
        try (Connection connection = ConnectionSql.getInstance();) {
            Repositorio<Producto> repositorio = new ProductoRepositorio();
            System.out.println("===================Listado de Productos ========================");
            repositorio.listar().forEach(p-> System.out.println(p.toString()));

            System.out.println("===================Inserta un nuevo Productos ========================");

            System.out.println("Ingrese 1 si quiere ingresar un producto");
            System.out.println("Ingrese 2 si quiere eliminar");
            System.out.println("Ingrese 3 si quiere buscar");
            Scanner scanner = new Scanner(System.in);
            int op = scanner.nextInt();

            if(op==1){

                Producto p = new Producto();

                System.out.println("Ingrese el precio del Producto");
                p.setPrecio(scanner.nextInt());
                //limpiamos le buffer con:
                scanner.nextLine();

                System.out.println("Ingrese el nombre del Producto");
                p.setNombre(scanner.nextLine());

                Categoria categoria = new Categoria();
                categoria.setId(3);
                categoria.setNombreCategoria("Juegos");
                p.setCategoria(categoria);

                p.setFecha_registro(new Date());
                repositorio.guardar(p);
            }else if(op==2){

                System.out.println("==================Eliminar Producto");
                System.out.println("Ingrese el id del producto a eliminar");
                id = scanner.nextLong();
                repositorio.eliminar(id);
                repositorio.listar().forEach(producto-> System.out.println(producto.toString()));
            }else if (op==3)
            {
                System.out.println("===================Busqueda de Productos ========================");
                System.out.println("Ingrese un id de Producto");
                id = scanner.nextLong();
                System.out.println("id = " + id);
                System.out.println(repositorio.buscarPorId(id));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //INSERT INTO `java_curso`.`productos` (`nombre`, `precio`, `fecha_registro`) VALUES ('Auto', '500', '2023-01-23');
    }
}
