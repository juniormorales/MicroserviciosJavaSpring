package vass.assessment.server.reclamos.controllers;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import vass.assessment.server.reclamos.models.Reclamo;
import vass.assessment.server.reclamos.service.read.IReclamoServiceRead;
import vass.assessment.server.reclamos.service.write.IReclamoServiceWrite;
import vass.assessment.server.reclamos.utils.Constantes;
import vass.assessment.server.reclamos.utils.DataMocks;


@SpringBootTest
public class ReclamoControllerTests {
	
	@InjectMocks
	ReclamoController controller;
	
	@Mock
	IReclamoServiceRead reclamoReadMock;
	
	@Mock
	IReclamoServiceWrite reclamoWriteMock;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void listarCorrecto() {
		List<Reclamo> lsReclamosMock = new ArrayList<>();
		when(reclamoReadMock.listarTodo()).thenReturn(lsReclamosMock);
		ResponseEntity<Map<String,Object>> resp = controller.listar();
		Assertions.assertThat(resp.getStatusCodeValue()).isEqualByComparingTo(HttpStatus.OK.value());
		Assertions.assertThat(resp.getBody().get("estado")).isEqualTo(Constantes.ESTADO_OK);
	}
	
	@Test
	void listarError() {
		when(reclamoReadMock.listarTodo()).thenThrow(new RuntimeException("Ocurrio un error"));
		ResponseEntity<Map<String,Object>> resp = controller.listar();
		Assertions.assertThat(resp.getStatusCodeValue()).isEqualByComparingTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
		Assertions.assertThat(resp.getBody().get("estado")).isEqualTo(Constantes.ESTADO_ERROR);
		Assertions.assertThat(resp.getBody().get("mensaje")).isEqualTo(Constantes.MSG_LISTAR_ERROR);
	}
	
	
	@Test
	void listarPorClienteCorrecto() {
		List<Reclamo> lsReclamosMock = new ArrayList<>();
		String nrodoc = "73058001";
		when(reclamoReadMock.listarPorCliente(nrodoc)).thenReturn(lsReclamosMock);
		ResponseEntity<Map<String,Object>> resp = controller.listarPorCliente(nrodoc);
		Assertions.assertThat(resp.getStatusCodeValue()).isEqualByComparingTo(HttpStatus.OK.value());
		Assertions.assertThat(resp.getBody().get("estado")).isEqualTo(Constantes.ESTADO_OK);
	}
	
	@Test
	void listarPorClienteError() {
		String nrodoc = "73058001";
		when(reclamoReadMock.listarPorCliente(nrodoc)).thenThrow(new RuntimeException("Ocurrio un error"));
		ResponseEntity<Map<String,Object>> resp = controller.listarPorCliente(nrodoc);
		Assertions.assertThat(resp.getStatusCodeValue()).isEqualByComparingTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
		Assertions.assertThat(resp.getBody().get("estado")).isEqualTo(Constantes.ESTADO_ERROR);
		Assertions.assertThat(resp.getBody().get("mensaje")).isEqualTo(Constantes.MSG_LISTAR_ERROR);
	}
	
	@Test
	void registrarOk() {
		Reclamo Reclamo = DataMocks.retornarReclamo();
		
		when(reclamoWriteMock.registrarReclamo(Reclamo)).thenReturn(true);
		ResponseEntity<Map<String,Object>> resp = controller.registrar(Reclamo);
		Assertions.assertThat(resp.getStatusCodeValue()).isEqualByComparingTo(HttpStatus.CREATED.value());
		Assertions.assertThat(resp.getBody().get("estado")).isEqualTo(Constantes.ESTADO_OK);
		Assertions.assertThat(resp.getBody().get("mensaje")).isEqualTo(Constantes.MSG_REGISTRAR_RECLAMO_OK);
	}
	
	@Test
	void registrarError() {
		Reclamo Reclamo = DataMocks.retornarReclamo();
		
		when(reclamoWriteMock.registrarReclamo(Reclamo)).thenThrow(new RuntimeException("Ocurrio un error"));
		ResponseEntity<Map<String,Object>> resp = controller.registrar(Reclamo);
		Assertions.assertThat(resp.getStatusCodeValue()).isEqualByComparingTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
		Assertions.assertThat(resp.getBody().get("estado")).isEqualTo(Constantes.ESTADO_ERROR);
		Assertions.assertThat(resp.getBody().get("mensaje")).isEqualTo(Constantes.MSG_REGISTRAR_RECLAMO_ERROR);
	}
	
	@Test
	void actualizarOk() {
		Reclamo Reclamo = DataMocks.retornarReclamo();
		Reclamo.setIdReclamo((long) 1);
		
		when(reclamoWriteMock.actualizarReclamo(Reclamo)).thenReturn(true);
		ResponseEntity<Map<String,Object>> resp = controller.actualizar(Reclamo);
		Assertions.assertThat(resp.getStatusCodeValue()).isEqualByComparingTo(HttpStatus.CREATED.value());
		Assertions.assertThat(resp.getBody().get("estado")).isEqualTo(Constantes.ESTADO_OK);
		Assertions.assertThat(resp.getBody().get("mensaje")).isEqualTo(Constantes.MSG_ACTUALIZAR_RECLAMO_OK);
	}
	
	@Test
	void actualizarError() {
		Reclamo Reclamo = DataMocks.retornarReclamo();
		Reclamo.setIdReclamo((long) 1);

		when(reclamoWriteMock.actualizarReclamo(Reclamo)).thenThrow(new RuntimeException("Ocurrio un error"));
		ResponseEntity<Map<String,Object>> resp = controller.actualizar(Reclamo);
		Assertions.assertThat(resp.getStatusCodeValue()).isEqualByComparingTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
		Assertions.assertThat(resp.getBody().get("estado")).isEqualTo(Constantes.ESTADO_ERROR);
		Assertions.assertThat(resp.getBody().get("mensaje")).isEqualTo(Constantes.MSG_ACTUALIZAR_RECLAMO_ERROR);
	}
	
	@Test
	void eliminarOk() {
		Long idReclamo = (long) 1;
		when(reclamoWriteMock.eliminarReclamoPorId(idReclamo)).thenReturn(true);
		ResponseEntity<Map<String,Object>> resp = controller.eliminar(idReclamo);
		Assertions.assertThat(resp.getStatusCodeValue()).isEqualByComparingTo(HttpStatus.ACCEPTED.value());
		Assertions.assertThat(resp.getBody().get("estado")).isEqualTo(Constantes.ESTADO_OK);
		Assertions.assertThat(resp.getBody().get("mensaje")).isEqualTo(Constantes.MSG_ELIMINAR_RECLAMO_OK);
	}
	
	@Test
	void eliminarError() {
		Long idReclamo = (long) 1;	
		when(reclamoWriteMock.eliminarReclamoPorId(idReclamo)).thenThrow(new RuntimeException("Ocurrio un error"));
		ResponseEntity<Map<String,Object>> resp = controller.eliminar(idReclamo);
		Assertions.assertThat(resp.getStatusCodeValue()).isEqualByComparingTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
		Assertions.assertThat(resp.getBody().get("estado")).isEqualTo(Constantes.ESTADO_ERROR);
		Assertions.assertThat(resp.getBody().get("mensaje")).isEqualTo(Constantes.MSG_ELIMINAR_ERROR);
	}
	
	@Test
	void eliminarRegistroNotFound() {
		Long idReclamo = (long) 1;	
		when(reclamoWriteMock.eliminarReclamoPorId(idReclamo)).thenReturn(false);
		ResponseEntity<Map<String,Object>> resp = controller.eliminar(idReclamo);
		Assertions.assertThat(resp.getStatusCodeValue()).isEqualByComparingTo(HttpStatus.ACCEPTED.value());
		Assertions.assertThat(resp.getBody().get("estado")).isEqualTo(Constantes.ESTADO_ERROR);
		Assertions.assertThat(resp.getBody().get("mensaje")).isEqualTo(Constantes.MSG_ELIMINAR_NOEXISTS);
	}
}
