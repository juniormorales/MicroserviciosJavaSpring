package vass.assessment.server.security.utils;

import vass.assessment.server.security.models.Perfil;
import vass.assessment.server.security.models.Usuario;

public class DataMocks {
	
	public static Usuario retornarUsuario() {
		Usuario usuario = new Usuario();
		Perfil perfil = new Perfil();
		perfil.setIdPerfil((long) 1);
		perfil.setPerfil("ROLE_ADMIN");
		usuario.setEnabled(true);
		usuario.setPassword("pass");
		usuario.setPerfil(perfil);
		usuario.setUsername("junior");
		return usuario;
	}
}
