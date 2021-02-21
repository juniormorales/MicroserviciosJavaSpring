package vass.assessment.server.reclamos.service.read.impl;

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

import vass.assessment.server.reclamos.models.Reclamo;
import vass.assessment.server.reclamos.repository.ReclamoRepository;


@SpringBootTest
public class ReclamoServiceReadTests {
	
	@InjectMocks
	ReclamoServiceReadImpl ReclamoRead;

	@Mock
	ReclamoRepository repoMock;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void listarTodoOk() {
		List<Reclamo> lsReclamosMock = new ArrayList<>();
		when(repoMock.findAll()).thenReturn(lsReclamosMock);
		List<Reclamo> lssend = ReclamoRead.listarTodo();
		Assertions.assertThat(lsReclamosMock).isEqualTo(lssend);
	}

	@Test
	void listarTodoError() {
		when(repoMock.findAll()).thenThrow(new RuntimeException("Error de conexion"));
		try {
			ReclamoRead.listarTodo();
		} catch (Exception e) {
			Assertions.assertThat("Error de conexion").isEqualTo(e.getMessage());
		}
	}
	
	@Test
	void listarPorClienteOk() {
		List<Reclamo> lsReclamosMock = new ArrayList<>();
		String nrodoc = "73058001";
		when(repoMock.findByNroDocCliente(nrodoc)).thenReturn(lsReclamosMock);
		List<Reclamo> lssend = ReclamoRead.listarPorCliente(nrodoc);
		Assertions.assertThat(lsReclamosMock).isEqualTo(lssend);
	}

	@Test
	void listarPorClienteError() {
		String nrodoc = "73058001";
		when(repoMock.findByNroDocCliente(nrodoc)).thenThrow(new RuntimeException("Error de conexion"));
		try {
			ReclamoRead.listarPorCliente(nrodoc);
		} catch (Exception e) {
			Assertions.assertThat("Error de conexion").isEqualTo(e.getMessage());
		}
	}
}
