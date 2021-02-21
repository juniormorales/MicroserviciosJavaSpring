package vass.assessment.server.clientes.write;

import vass.assessment.server.clientes.models.Cliente;

public interface IClienteServiceWrite {
	
	public Boolean registrarCliente(Cliente cliente);
	public Boolean actualizarCliente(Cliente cliente);
	public Boolean eliminarClientePorId(Long id);
}
