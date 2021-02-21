package vass.assessment.server.productos.services.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import vass.assessment.server.productos.models.Producto;
import vass.assessment.server.productos.repository.ProductoRepository;
import vass.assessment.server.productos.services.write.IProductoServiceWrite;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProductoServiceWriteImpl implements IProductoServiceWrite {

	@Autowired
	ProductoRepository productoRepo;

	@Override
	public Boolean registrarProducto(Producto producto) {
		try {
			return productoRepo.save(producto)!=null?true:false;
		} catch (Exception e) {
			System.out.println("Error en el metodo  registrarProducto de la clase " + this.getClass().getName() + ": "
					+ e.getMessage() + "\nLinea nro: " + e.getStackTrace()[0].getLineNumber());
			throw e;
		}
	}

	@Override
	public Boolean actualizarProducto(Producto producto) {
		try {
			return productoRepo.save(producto)!=null?true:false;
		} catch (Exception e) {
			System.out.println("Error en el metodo actualizarProducto de la clase " + this.getClass().getName() + ": "
					+ e.getMessage() + "\nLinea nro: " + e.getStackTrace()[0].getLineNumber());
			throw e;
		}
	}

	@Override
	public Boolean eliminarProductoPorId(Long idproducto) {
		try {
			if(!productoRepo.existsById(idproducto)) {
				productoRepo.deleteById(idproducto);
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("Error en el metodo eliminarProductoPorId de la clase " + this.getClass().getName() + ": "
					+ e.getMessage() + "\nLinea nro: " + e.getStackTrace()[0].getLineNumber());
			throw e;
		}
	}

}
