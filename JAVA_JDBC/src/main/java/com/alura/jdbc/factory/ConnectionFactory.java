package com.alura.jdbc.factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionFactory {

    final private DataSource dataSource;
    public ConnectionFactory(){
        var pooleDataSource = new ComboPooledDataSource();
        pooleDataSource.setJdbcUrl("jdbc:mysql://localhost/control_de_stock?useTimezone=true&serverTimeZone=UTC");
        pooleDataSource.setUser("root");
        pooleDataSource.setPassword("root");
        pooleDataSource.setMaxPoolSize(10);

        this.dataSource = pooleDataSource;
    }
    public Connection recuperaConexion() {
        try{
            return this.dataSource.getConnection();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }


    }
}
