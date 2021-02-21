package vass.assessment.server.reclamos.service.write.impl;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import vass.assessment.server.reclamos.models.Reclamo;
import vass.assessment.server.reclamos.repository.ReclamoRepository;
import vass.assessment.server.reclamos.utils.DataMocks;


@SpringBootTest
public class ReclamoServiceWriteTests {
	
	@InjectMocks
	ReclamoServiceWriteImpl reclamoWrite;

	@Mock
	ReclamoRepository repoMock;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void registrarReclamoOk() {
		Reclamo Reclamo = DataMocks.retornarReclamo();
		
		when(repoMock.save(Reclamo)).thenReturn(Reclamo);
		Boolean resp = reclamoWrite.registrarReclamo(Reclamo);
		Assertions.assertThat(true).isEqualTo(resp);
	}
	
	@Test
	void registrarReclamoFail() {
		Reclamo Reclamo = DataMocks.retornarReclamo();
		when(repoMock.save(Reclamo)).thenReturn(null);
		Boolean resp = reclamoWrite.registrarReclamo(Reclamo);
		Assertions.assertThat(false).isEqualTo(resp);
	}

	@Test
	void registrarReclamoError() {
		Reclamo Reclamo = DataMocks.retornarReclamo();
		when(repoMock.save(Reclamo)).thenThrow(new RuntimeException("Error de conexion"));
		try {
			reclamoWrite.registrarReclamo(Reclamo);
		} catch (Exception e) {
			Assertions.assertThat("Error de conexion").isEqualTo(e.getMessage());
		}
	}
	
	@Test
	void actualizarReclamoOk() {
		Reclamo Reclamo = DataMocks.retornarReclamo();
		Reclamo.setIdReclamo((long) 1);
		when(repoMock.save(Reclamo)).thenReturn(Reclamo);
		Boolean resp = reclamoWrite.actualizarReclamo(Reclamo);
		Assertions.assertThat(true).isEqualTo(resp);
	}
	
	@Test
	void actualizarReclamoFail() {
		Reclamo Reclamo = DataMocks.retornarReclamo();
		Reclamo.setIdReclamo((long) 1);
		when(repoMock.save(Reclamo)).thenReturn(null);
		Boolean resp = reclamoWrite.actualizarReclamo(Reclamo);
		Assertions.assertThat(false).isEqualTo(resp);
	}

	@Test
	void actualizarReclamoError() {
		Reclamo Reclamo = DataMocks.retornarReclamo();
		Reclamo.setIdReclamo((long) 1);
		when(repoMock.save(Reclamo)).thenThrow(new RuntimeException("Error de conexion"));
		try {
			reclamoWrite.actualizarReclamo(Reclamo);
		} catch (Exception e) {
			Assertions.assertThat("Error de conexion").isEqualTo(e.getMessage());
		}
	}
	
	@Test
	void eliminarReclamoOk() {
		Long idReclamo = (long) 1;
		when(repoMock.existsById(idReclamo)).thenReturn(false);
		doNothing().when(repoMock).deleteById(idReclamo);
		Boolean resp = reclamoWrite.eliminarReclamoPorId(idReclamo);
		Assertions.assertThat(true).isEqualTo(resp);
	}
	
	@Test
	void eliminarReclamoNoDelete() {
		Long idReclamo = (long) 1;
		when(repoMock.existsById(idReclamo)).thenReturn(true);
		doNothing().when(repoMock).deleteById(idReclamo);
		Boolean resp = reclamoWrite.eliminarReclamoPorId(idReclamo);
		Assertions.assertThat(false).isEqualTo(resp);
	}
	
	@Test
	void eliminarReclamoError() {
		Long idReclamo = (long) 1;
		when(repoMock.existsById(idReclamo)).thenThrow(new RuntimeException("Error de conexion"));
		try {
			reclamoWrite.eliminarReclamoPorId(idReclamo);
		} catch (Exception e) {
			Assertions.assertThat("Error de conexion").isEqualTo(e.getMessage());
		}
	}
}
