package com.alura.jdbc.pruebas;

import java.sql.Connection;
import java.sql.SQLException;

public class PruebaConnection {

    public static void main(String[] args) throws SQLException {
        Connection conection = new com.alura.jdbc.factory.ConnectionFactory().recuperaConexion();


        System.out.println("Cerrando la conexión");

        conection.close();
    }

}
