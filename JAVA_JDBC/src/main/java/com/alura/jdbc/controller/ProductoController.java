package com.alura.jdbc.controller;

import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Categoria;
import com.alura.jdbc.modelo.Producto;
import com.alura.jdbc.dao.ProductoDAO;
import java.util.List;

public class ProductoController {
	final private ProductoDAO productoDAO;
	public ProductoController(){
		this.productoDAO = new ProductoDAO(new ConnectionFactory().recuperaConexion());
	}
//No es buena practica delegar Exceptions en otra clase, lo correcto es tratarlas en la misma clase
	public void modificar(String nombre, String descripcion, int cantidad, Integer id){
		productoDAO.modificar(nombre, descripcion, cantidad, id);
	}

	public int eliminar(Integer id){
		return productoDAO.eliminar(id);
	}

	public List<Producto> listar(){
 			return productoDAO.listar();
	}

	public List<Producto> listar(Categoria categoria){
		return productoDAO.listar(categoria.getId());
	}
	public void guardar(Producto producto, Integer categoriaId) {
		// al hacer esto estamos quitandole la responsabilidad al JDBC de gestionar los guardados
//		para no tener un error en caso de que a media ejecución falle y se borren los registros, pero ahora tendremos que trabajar
//		para sustituir este error
			/*Acabamos de usar un O pasamos todo o no pasamos nada, que basicamente se basa en que si tenemos un error de transacción
			La aplicación va a parar y puede que gracias al con.commit lo pase todo o que con el con.rollback lo regrese */
//			statement.close(); obsoleto iguanas ranas
//		con.close(); obsoleto por el try-with-resources
//		Try-with-Resource nos permite declarar recursos que van a ser usados en un bloque de try-catch con la certeza que cerán cerrados o finalizados.
		producto.setCategoriaId(categoriaId);
		productoDAO.guardar(producto);
	}

}
