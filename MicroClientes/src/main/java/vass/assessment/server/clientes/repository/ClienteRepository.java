package vass.assessment.server.clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vass.assessment.server.clientes.models.Cliente;

public interface ClienteRepository  extends JpaRepository<Cliente, Long>{

}
