package vass.assessment.server.security.service.read.impl;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import vass.assessment.server.security.models.Usuario;
import vass.assessment.server.security.repository.UsuarioRepository;
import vass.assessment.server.security.services.read.impl.UsuarioServiceReadImpl;

@SpringBootTest
public class UsuarioServiceReadTests {

	@InjectMocks
	UsuarioServiceReadImpl usuarioRead;

	@Mock
	UsuarioRepository repoMock;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void listarTodoOk() {
		List<Usuario> listarusuarios = new ArrayList<>();
		when(repoMock.findAll()).thenReturn(listarusuarios);
		List<Usuario> resp = usuarioRead.listarTodo();
		Assertions.assertThat(listarusuarios).isEqualTo(resp);
	}

	@Test
	void listarTodoError() {
		when(repoMock.findAll()).thenThrow(new RuntimeException("Error de conexion"));
		try {
			usuarioRead.listarTodo();
		} catch (Exception e) {
			Assertions.assertThat("Error de conexion").isEqualTo(e.getMessage());
		}
	}
}
