package vass.assessment.server.reclamos.service.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import vass.assessment.server.reclamos.models.Reclamo;
import vass.assessment.server.reclamos.repository.ReclamoRepository;
import vass.assessment.server.reclamos.service.write.IReclamoServiceWrite;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ReclamoServiceWriteImpl implements IReclamoServiceWrite {
	
	@Autowired
	ReclamoRepository reclamoRepo;
	
	@Override
	public Boolean registrarReclamo(Reclamo reclamo) {
		try {
			return reclamoRepo.save(reclamo)!=null?true:false;
		} catch (Exception e) {
			System.out.println("Error en el metodo  registrarReclamo de la clase " + this.getClass().getName() + ": "
					+ e.getMessage() + "\nLinea nro: " + e.getStackTrace()[0].getLineNumber());
			throw e;
		}
	}

	@Override
	public Boolean actualizarReclamo(Reclamo reclamo) {
		try {
			return reclamoRepo.save(reclamo)!=null?true:false;
		} catch (Exception e) {
			System.out.println("Error en el metodo  actualizarReclamo de la clase " + this.getClass().getName() + ": "
					+ e.getMessage() + "\nLinea nro: " + e.getStackTrace()[0].getLineNumber());
			throw e;
		}
	}

	@Override
	public Boolean eliminarReclamoPorId(Long idreclamo) {
		try {
			if(!reclamoRepo.existsById(idreclamo)) {
				reclamoRepo.deleteById(idreclamo);
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("Error en el metodo  eliminarReclamoPorId de la clase " + this.getClass().getName() + ": "
					+ e.getMessage() + "\nLinea nro: " + e.getStackTrace()[0].getLineNumber());
			throw e;
		}
	}

}
