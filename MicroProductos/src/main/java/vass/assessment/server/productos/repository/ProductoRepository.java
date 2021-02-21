package vass.assessment.server.productos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vass.assessment.server.productos.models.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
	
	
}
