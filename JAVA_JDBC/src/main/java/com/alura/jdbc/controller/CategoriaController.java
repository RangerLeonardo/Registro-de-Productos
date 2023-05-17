package com.alura.jdbc.controller;

import com.alura.jdbc.dao.CategoriaDAO;
import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Categoria;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class CategoriaController {

    private CategoriaDAO categoriaDAO;
    public CategoriaController(){
        Connection connection = new ConnectionFactory().recuperaConexion();
        this.categoriaDAO = new CategoriaDAO(connection);
    }
	public List<Categoria> listar() {
		return categoriaDAO.listar();
	}

    public List<Categoria> cargaReporte() {
        return this.categoriaDAO.listarConProductos();
    }

}
