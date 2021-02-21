package vass.assessment.server.reclamos.service.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vass.assessment.server.reclamos.models.Reclamo;
import vass.assessment.server.reclamos.repository.ReclamoRepository;
import vass.assessment.server.reclamos.service.read.IReclamoServiceRead;

@Service
public class ReclamoServiceReadImpl implements IReclamoServiceRead {
	
	@Autowired
	ReclamoRepository reclamoRepo;

	@Override
	public List<Reclamo> listarTodo() {
		try {
			return reclamoRepo.findAll();
		} catch (Exception e) {
			System.out.println("Error en el metodo listarTodo de la clase " + this.getClass().getName() + ": "
					+ e.getMessage() + "\nLinea nro: " + e.getStackTrace()[0].getLineNumber());
			throw e;
		}
	}

	@Override
	public List<Reclamo> listarPorCliente(String nrodocumento) {
		try {
			return reclamoRepo.findByNroDocCliente(nrodocumento);
		} catch (Exception e) {
			System.out.println("Error en el metodo listarPorCliente de la clase " + this.getClass().getName() + ": "
					+ e.getMessage() + "\nLinea nro: " + e.getStackTrace()[0].getLineNumber());
			throw e;
		}
	}

}
