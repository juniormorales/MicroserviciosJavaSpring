package vass.assessment.server.clientes.service.read.impl;

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

import vass.assessment.server.clientes.models.Cliente;
import vass.assessment.server.clientes.repository.ClienteRepository;
import vass.assessment.server.clientes.services.read.impl.ClienteServiceReadImpl;

@SpringBootTest
public class ClienteServiceReadTests {


	@InjectMocks
	ClienteServiceReadImpl clienteRead;

	@Mock
	ClienteRepository repoMock;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void listarTodoOk() {
		List<Cliente> lsclientesMock = new ArrayList<>();
		when(repoMock.findAll()).thenReturn(lsclientesMock);
		List<Cliente> lssend = clienteRead.listarTodo();
		Assertions.assertThat(lsclientesMock).isEqualTo(lssend);
	}

	@Test
	void listarTodoError() {
		when(repoMock.findAll()).thenThrow(new RuntimeException("Error de conexion"));
		try {
			clienteRead.listarTodo();
		} catch (Exception e) {
			Assertions.assertThat("Error de conexion").isEqualTo(e.getMessage());
		}
	}
}
