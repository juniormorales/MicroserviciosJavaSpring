package vass.assessment.server.productos.services.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import vass.assessment.server.productos.models.Producto;
import vass.assessment.server.productos.repository.ProductoRepository;
import vass.assessment.server.productos.services.read.IProductoServiceRead;

@Service
@Primary
public class ProductoServiceReadImpl implements IProductoServiceRead {

	@Autowired
	ProductoRepository productoRepo;

	@Override
	public List<Producto> listarTodo() {
		try {
			return productoRepo.findAll();
		} catch (Exception e) {
			System.out.println("Error en el metodo listarTodo de la clase " + this.getClass().getName() +": "+ e.getMessage()
					+ "\nLinea nro: " + e.getStackTrace()[0].getLineNumber());
			throw e;
		}
	}

}
