package vass.assessment.server.productos.services.write;

import vass.assessment.server.productos.models.Producto;

public interface IProductoServiceWrite {
	
	public Boolean registrarProducto(Producto producto);
	public Boolean actualizarProducto(Producto producto);
	public Boolean eliminarProductoPorId(Long idproducto);
}
