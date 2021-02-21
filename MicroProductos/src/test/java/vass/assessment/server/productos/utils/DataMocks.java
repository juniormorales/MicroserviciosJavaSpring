package vass.assessment.server.productos.utils;

import vass.assessment.server.productos.models.Producto;

public class DataMocks {
	
	public static Producto retornarProducto() {
		Producto producto = new Producto();
		producto.setCodigo("P-0494");
		producto.setNombre("Laptop");
		producto.setTecnologia("Computador");
		return producto;
	}
}
