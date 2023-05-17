package com.alura.jdbc.pruebas;

import com.alura.jdbc.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class PruebaPoolConexiones  {
    public static void main(String[] args) throws SQLException {
        ConnectionFactory connectionFactory = new ConnectionFactory();

        for (int i = 1; i <20 ; i++) {
            Connection con = connectionFactory.recuperaConexion();
            System.out.println("Abriendo la connexion " + i);
        }
    }
}
