package com.alura.jdbc.dao;

import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
//    Esto es un DAO un Data Access Object, una clase que accede a los datos del producto
     final private Connection connection;
    public ProductoDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Producto> listar() {
        List<Producto> resultado = new ArrayList<>();
        try{
            final PreparedStatement statement = connection.prepareStatement("SELECT ID, NOMBRE, DESCRIPCION, CANTIDAD FROM PRODUCTO");
            try (statement) {
                statement.execute();
                final ResultSet resultSet = statement.getResultSet();

                try (resultSet) {
                    while (resultSet.next()) {
                        resultado.add(new Producto(
                                resultSet.getInt("ID"),
                                resultSet.getString("NOMBRE"),
                                resultSet.getString("DESCRIPCION"),
                                resultSet.getInt("CANTIDAD")));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }
    public void guardar(Producto producto){
        try {
//            connection.setAutoCommit(false);
            final PreparedStatement statement = connection.prepareStatement(("INSERT INTO PRODUCTO (NOMBRE, DESCRIPCION, CANTIDAD, CATEGORIA_ID)  VALUES (?,?,?,?)"),
                    Statement.RETURN_GENERATED_KEYS);
            try (statement) {
                ejecutaRegistro(producto, statement);

            }
//            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
//            connection.rollback();
        }
    }
    private static void ejecutaRegistro(Producto producto, PreparedStatement statement) throws SQLException {
        statement.setString(1, producto.getNombre());
        statement.setString(2, producto.getDescripcion());
        statement.setInt(3, producto.getCantidad());
        statement.setInt(4, producto.getCategoriaId());
        statement.execute();
        final ResultSet resultSet = statement.getGeneratedKeys(); //Así es el try-with-resources se pone el final y en el try el nombre de la variable
        try(resultSet) {
            while (resultSet.next()) {
                producto.setId(resultSet.getInt(1));
                System.out.println("Fue insertado el producto " + producto);
            }
        }
    }
    public int eliminar(Integer id) {
        try{
            final PreparedStatement statement = connection.prepareStatement("DELETE FROM PRODUCTO WHERE ID = ?");

            try (statement) {
                statement.setInt(1, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int modificar(String nombre, String descripcion, int cantidad, Integer id) {
        try {
            final PreparedStatement statement = connection.prepareStatement(
                    "UPDATE PRODUCTO SET "
                            + " NOMBRE = ?, "
                            + " DESCRIPCION = ?,"
                            + " CANTIDAD = ?"
                            + " WHERE ID = ?");

            try (statement) {
                statement.setString(1, nombre);
                statement.setString(2, descripcion);
                statement.setInt(3, cantidad);
                statement.setInt(4, id);
                statement.execute();

                int updateCount = statement.getUpdateCount();

                return updateCount;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public List<Producto> listar(Integer categoriaId) {
        List<Producto> resultado = new ArrayList<>();
        try{
            final PreparedStatement statement = connection.prepareStatement("SELECT ID, NOMBRE, DESCRIPCION, CANTIDAD FROM PRODUCTO WHERE CATEGORIA_ID = ?");
            try (statement) {
                statement.setInt(1, categoriaId);
                statement.execute();
                final ResultSet resultSet = statement.getResultSet();

                try (resultSet) {
                    while (resultSet.next()) {
                        resultado.add(new Producto(
                                resultSet.getInt("ID"),
                                resultSet.getString("NOMBRE"),
                                resultSet.getString("DESCRIPCION"),
                                resultSet.getInt("CANTIDAD")));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultado;
    }
}
