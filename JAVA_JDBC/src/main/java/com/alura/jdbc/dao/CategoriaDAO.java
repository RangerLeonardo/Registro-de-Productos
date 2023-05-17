package com.alura.jdbc.dao;

import com.alura.jdbc.modelo.Categoria;
import com.alura.jdbc.modelo.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    private Connection connection;
    public CategoriaDAO (Connection connection){

        this.connection = connection;
    }

    public List<Categoria> listar(){
        List<Categoria> list = new ArrayList<>();
        try {
            final PreparedStatement statement = connection.prepareStatement("SELECT ID, NOMBRE FROM CATEGORIA");
            try (statement){
                final ResultSet resultSet = statement.executeQuery();
                try(resultSet){
                    while(resultSet.next()){
                        Categoria categoria = new Categoria(resultSet.getInt("id"),resultSet.getString("nombre"));
                        list.add(categoria);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<Categoria> listarConProductos() {
        List<Categoria> list = new ArrayList<>();
        try {
            final PreparedStatement statement = connection.prepareStatement("SELECT C.ID, C.NOMBRE, P.ID, P.NOMBRE,P.CANTIDAD FROM CATEGORIA C"+
                    " INNER JOIN PRODUCTO P ON C.ID = P.CATEGORIA_ID");
            try (statement){
                final ResultSet resultSet = statement.executeQuery();

                try(resultSet){
                    while(resultSet.next()){
                        Integer categoriaId = resultSet.getInt("C.ID");
                        String categoriaNombre = resultSet.getString("C.NOMBRE");

                        var categoria = list.stream().filter(cat -> cat.getId().equals(categoriaId)).findAny()
                                .orElseGet(()->{
                                    Categoria cat = new Categoria(categoriaId, categoriaNombre);

                                    list.add(cat);
                                    return cat;
                                });
                        Producto producto = new Producto(resultSet.getInt("P.ID"),
                                resultSet.getString("P.NOMBRE"),
                                resultSet.getInt("P.CANTIDAD"));
                        categoria.agregar(producto);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}
