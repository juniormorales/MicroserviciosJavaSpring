package vass.assessment.server.reclamos.utils;

import vass.assessment.server.reclamos.models.Reclamo;

public class DataMocks {
	
	public static Reclamo retornarReclamo() {
		Reclamo reclamo = new Reclamo();
		reclamo.setCodigo("R-45451");
		reclamo.setCodigoContrato("C-54554");
		reclamo.setDescripcion("El producto esta defectuoso");
		reclamo.setMotivo("Producto con daños");
		reclamo.setNroDocCliente("73058001");
		return reclamo;
	}
}
