package net.youtics.repositorio;

import net.youtics.models.Categoria;
import net.youtics.models.Producto;
import net.youtics.util.ConnectionSql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositorio implements Repositorio<Producto>{

    private Connection getConnection() throws SQLException {
        return ConnectionSql.getInstance();
    }
    @Override
    public List<Producto> listar() {
        List<Producto> listaProductos = new ArrayList<>();

        try(Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("Select p.*, c.nombre_categoria " +
                    "From productos as p inner join categorias as c ON (p.idCategoria = c.idcategoria) ");)
        {
            while (resultSet.next())
            {
                Producto p = getProducto(resultSet);
                listaProductos.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaProductos;
    }

    @Override
    public Producto buscarPorId(Long id) {

        Producto p = null;
        try (PreparedStatement prepareStatement = getConnection().
                prepareStatement("Select p.*, c.nombre_categoria From productos as p " +
                        "inner join categorias as c On (p.idCategoria = c.idcategoria) Where p.id =?"))
        {
            prepareStatement.setLong(1,id);
            //anidando el resulset dentro del try no es necesario el metodo close ya que lo cierra de forma automatica
            try (ResultSet resultSet = prepareStatement.executeQuery()) {
                if (resultSet.next())
                    p = getProducto(resultSet);
                //resultSet.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return p;
    }



    @Override
    public void guardar(Producto producto) {
        String sql;
        //si el producto tiene id lo actualizamos y si no lo tiene lo creamos

        if(producto.getId() != null && producto.getId() > 0)
        {
            sql = "UPDATE productos SET (nombre=?, precio=?, idCategoria=? WHERE id=?";
        }else {
            sql = "INSERT INTO productos (nombre, precio, idCategoria, fecha_registro) VALUES (?, ?, ?, ?)";

        }
        try (PreparedStatement prepareStatement = getConnection().
                prepareStatement(sql))
        {
            prepareStatement.setString(1,producto.getNombre());
            prepareStatement.setInt(2,producto.getPrecio());
            prepareStatement.setLong(3, producto.getCategoria().getId());

            if(producto.getId() != null && producto.getId() > 0)
            {
                prepareStatement.setLong(4,producto.getId());
            }else {
                prepareStatement.setDate(4,new Date(producto.getFecha_registro().getTime()));
            }
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminar(Long id) {
        Producto p = null;
        try (PreparedStatement prepareStatement = getConnection().
                prepareStatement("Delete From java_curso.productos Where id =?"))
        {
            prepareStatement.setLong(1,id);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Producto getProducto(ResultSet resultSet) throws SQLException {
        Producto p = new Producto(
                resultSet.getString("nombre"),
                resultSet.getInt("id"),
                resultSet.getInt("precio"),
                resultSet.getDate("fecha_registro"));

        Categoria categoria = new Categoria();
        categoria.setId(resultSet.getInt("idCategoria"));
        categoria.setNombreCategoria(resultSet.getString("nombre_categoria"));
        p.setCategoria(categoria);

        return p;
    }
}
