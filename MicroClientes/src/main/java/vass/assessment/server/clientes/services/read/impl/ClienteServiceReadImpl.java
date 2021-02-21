package vass.assessment.server.clientes.services.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vass.assessment.server.clientes.models.Cliente;
import vass.assessment.server.clientes.repository.ClienteRepository;
import vass.assessment.server.clientes.services.read.IClienteServiceRead;

@Service
public class ClienteServiceReadImpl implements IClienteServiceRead {

	@Autowired
	ClienteRepository clienteRepo;

	@Override
	public List<Cliente> listarTodo() {
		try {
			return clienteRepo.findAll();
		} catch (Exception e) {
			System.out.println("Error en el metodo listarTodo de la clase " + this.getClass().getName() + ": "
					+ e.getMessage() + "\nLinea nro: " + e.getStackTrace()[0].getLineNumber());
			throw e;
		}
	}

}
