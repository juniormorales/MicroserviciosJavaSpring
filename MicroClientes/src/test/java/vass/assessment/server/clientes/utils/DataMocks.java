package vass.assessment.server.clientes.utils;

import java.util.Date;
import vass.assessment.server.clientes.models.Cliente;
import vass.assessment.server.clientes.models.TipoDocumento;

public class DataMocks {
	
	public static Cliente retornarCliente() {
		Cliente cliente = new Cliente();
		TipoDocumento tip = new TipoDocumento();
		tip.setIdTipoDocumento((long) 1);
		tip.setDescripcion("DNI");
		cliente.setApellidos("Morales Brenis");
		cliente.setNombres("Junior Angel");
		cliente.setFechaNacimiento(new Date());
		cliente.setNroDocumento("73058001");
		cliente.setTipoDocumento(tip);

		return cliente;
	}
}
