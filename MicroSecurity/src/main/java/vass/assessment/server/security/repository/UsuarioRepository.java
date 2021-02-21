package vass.assessment.server.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vass.assessment.server.security.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Usuario findByUsername(String username);
	Boolean existsByUsername(String username);
}
