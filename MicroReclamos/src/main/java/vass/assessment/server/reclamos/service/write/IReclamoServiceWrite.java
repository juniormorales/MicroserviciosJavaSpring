package vass.assessment.server.reclamos.service.write;

import vass.assessment.server.reclamos.models.Reclamo;

public interface IReclamoServiceWrite {
	
	public Boolean registrarReclamo(Reclamo reclamo);
	public Boolean actualizarReclamo(Reclamo reclamo);
	public Boolean eliminarReclamoPorId(Long idreclamo);
}
