package vass.assessment.server.reclamos.service.read;

import java.util.List;

import vass.assessment.server.reclamos.models.Reclamo;

public interface IReclamoServiceRead {
	
	public List<Reclamo> listarTodo();
	public List<Reclamo> listarPorCliente(String nrodocumento);
}
