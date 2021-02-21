package vass.assessment.server.security.services.write.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import vass.assessment.server.security.models.Usuario;
import vass.assessment.server.security.repository.UsuarioRepository;
import vass.assessment.server.security.services.write.IUsuarioServiceWrite;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class UsuarioServiceWriteImpl implements IUsuarioServiceWrite{
	
	@Autowired
	UsuarioRepository usuarioRepo;
	
	@Override
	public Boolean registrarUsuario(Usuario usuario) {
		try {
			if(usuarioRepo.existsByUsername(usuario.getUsername())) {
				return false;
			}else {
				usuario.setEnabled(true);
				usuarioRepo.save(usuario);
				return true;
			}
		} catch (Exception e) {
			System.out.println("Error en el metodo  registrarUsuario de la clase " + this.getClass().getName() + ": "
					+ e.getMessage() + "\nLinea nro: " + e.getStackTrace()[0].getLineNumber());
			throw e;
		}
	}

}
