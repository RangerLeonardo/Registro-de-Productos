package com.alura.jdbc.pruebas;

import com.alura.jdbc.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PruebaDelete {
    public static void main(String[] args) throws SQLException {
        Connection conection = new ConnectionFactory().recuperaConexion();

        Statement statement = conection.createStatement(); //Creamos un objeto de tipo statement que es el que nos permite acceder a los atributos del producto

        statement.execute("DELETE FROM PRODUCTO WHERE ID = 99" );
        System.out.println(statement.getUpdateCount());
    }
}
