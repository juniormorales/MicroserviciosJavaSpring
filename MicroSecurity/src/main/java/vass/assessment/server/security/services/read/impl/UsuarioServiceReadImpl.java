package vass.assessment.server.security.services.read.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vass.assessment.server.security.models.Usuario;
import vass.assessment.server.security.repository.UsuarioRepository;
import vass.assessment.server.security.services.read.IUsuarioServiceRead;

@Service
public class UsuarioServiceReadImpl implements IUsuarioServiceRead{
	
	@Autowired
	UsuarioRepository usuarioRepo;
	
	@Override
	public List<Usuario> listarTodo() {
		try {
			return usuarioRepo.findAll();
		} catch (Exception e) {
			System.out.println("Error en el metodo  listarTodo de la clase " + this.getClass().getName() + ": "
					+ e.getMessage() + "\nLinea nro: " + e.getStackTrace()[0].getLineNumber());
			throw e;
		}
	}

}
