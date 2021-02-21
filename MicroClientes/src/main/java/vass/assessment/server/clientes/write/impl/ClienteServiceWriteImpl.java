package vass.assessment.server.clientes.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import vass.assessment.server.clientes.models.Cliente;
import vass.assessment.server.clientes.repository.ClienteRepository;
import vass.assessment.server.clientes.write.IClienteServiceWrite;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ClienteServiceWriteImpl implements IClienteServiceWrite {
	
	@Autowired
	ClienteRepository clienteRepo;
	
	@Override
	public Boolean registrarCliente(Cliente cliente) {
		try {
			return clienteRepo.save(cliente)!=null?true:false;
		} catch (Exception e) {
			System.out.println("Error en el metodo registrarCliente de la clase " + this.getClass().getName() + ": "
					+ e.getMessage() + "\nLinea nro: " + e.getStackTrace()[0].getLineNumber());
			throw e;
		}
	}

	@Override
	public Boolean actualizarCliente(Cliente cliente) {
		try {
			return clienteRepo.save(cliente)!=null?true:false;
		} catch (Exception e) {
			System.out.println("Error en el metodo actualizarCliente de la clase " + this.getClass().getName() + ": "
					+ e.getMessage() + "\nLinea nro: " + e.getStackTrace()[0].getLineNumber());
			throw e;
		}
	}

	@Override
	public Boolean eliminarClientePorId(Long id) {
		try {
			if(!clienteRepo.existsById(id)) {
				clienteRepo.deleteById(id);
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("Error en el metodo eliminarClientePorId de la clase " + this.getClass().getName() + ": "
					+ e.getMessage() + "\nLinea nro: " + e.getStackTrace()[0].getLineNumber());
			throw e;
		}
	}

}
