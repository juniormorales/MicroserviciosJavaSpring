package vass.assessment.server.security.service.write.impl;

import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import vass.assessment.server.security.models.Usuario;
import vass.assessment.server.security.repository.UsuarioRepository;
import vass.assessment.server.security.services.write.impl.UsuarioServiceWriteImpl;
import vass.assessment.server.security.utils.DataMocks;



@SpringBootTest
public class UsuarioServiceWriteTests {
	
	@InjectMocks
	UsuarioServiceWriteImpl usuarioWrite;

	@Mock
	UsuarioRepository repoMock;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void registrarUsuarioOk() {
		Usuario usuario = DataMocks.retornarUsuario();
		when(repoMock.existsByUsername(usuario.getUsername())).thenReturn(false);
		when(repoMock.save(usuario)).thenReturn(usuario);
		Boolean resp = usuarioWrite.registrarUsuario(usuario);
		Assertions.assertThat(true).isEqualTo(resp);
	}
	
	@Test
	void registrarUsuarioFail() {
		Usuario usuario = DataMocks.retornarUsuario();
		when(repoMock.existsByUsername(usuario.getUsername())).thenReturn(true);
		when(repoMock.save(usuario)).thenReturn(usuario);
		Boolean resp = usuarioWrite.registrarUsuario(usuario);
		Assertions.assertThat(false).isEqualTo(resp);
	}

	@Test
	void registrarUsuarioError() {
		Usuario usuario = DataMocks.retornarUsuario();
		when(repoMock.save(usuario)).thenThrow(new RuntimeException("Error de conexion"));
		try {
			usuarioWrite.registrarUsuario(usuario);
		} catch (Exception e) {
			Assertions.assertThat("Error de conexion").isEqualTo(e.getMessage());
		}
	}
}
