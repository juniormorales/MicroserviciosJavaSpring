package vass.assessment.server.reclamos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import vass.assessment.server.reclamos.models.Reclamo;

public interface ReclamoRepository extends JpaRepository<Reclamo, Long>{
	
	List<Reclamo> findByNroDocCliente(String codigo);
}
