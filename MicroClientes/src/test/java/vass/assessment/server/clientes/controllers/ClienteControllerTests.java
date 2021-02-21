package vass.assessment.server.clientes.controllers;

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

import vass.assessment.server.clientes.controllers.ClienteController;
import vass.assessment.server.clientes.models.Cliente;
import vass.assessment.server.clientes.services.read.IClienteServiceRead;
import vass.assessment.server.clientes.utils.Constantes;
import vass.assessment.server.clientes.utils.DataMocks;
import vass.assessment.server.clientes.write.IClienteServiceWrite;

@SpringBootTest
public class ClienteControllerTests {
	
	@InjectMocks
	ClienteController controller;
	
	@Mock
	IClienteServiceRead clienteReadMock;
	
	@Mock
	IClienteServiceWrite clienteWriteMock;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void listarCorrecto() {
		List<Cliente> lsclientesMock = new ArrayList<>();
		when(clienteReadMock.listarTodo()).thenReturn(lsclientesMock);
		ResponseEntity<Map<String,Object>> resp = controller.listar();
		Assertions.assertThat(resp.getStatusCodeValue()).isEqualByComparingTo(HttpStatus.OK.value());
		Assertions.assertThat(resp.getBody().get("estado")).isEqualTo(Constantes.ESTADO_OK);
	}
	
	@Test
	void listarError() {
		when(clienteReadMock.listarTodo()).thenThrow(new RuntimeException("Ocurrio un error"));
		ResponseEntity<Map<String,Object>> resp = controller.listar();
		Assertions.assertThat(resp.getStatusCodeValue()).isEqualByComparingTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
		Assertions.assertThat(resp.getBody().get("estado")).isEqualTo(Constantes.ESTADO_ERROR);
		Assertions.assertThat(resp.getBody().get("mensaje")).isEqualTo(Constantes.MSG_LISTAR_CLIENTES_ERROR);
	}
	
	@Test
	void registrarOk() {
		Cliente cliente = DataMocks.retornarCliente();
		
		when(clienteWriteMock.registrarCliente(cliente)).thenReturn(true);
		ResponseEntity<Map<String,Object>> resp = controller.registrar(cliente);
		Assertions.assertThat(resp.getStatusCodeValue()).isEqualByComparingTo(HttpStatus.CREATED.value());
		Assertions.assertThat(resp.getBody().get("estado")).isEqualTo(Constantes.ESTADO_OK);
		Assertions.assertThat(resp.getBody().get("mensaje")).isEqualTo(Constantes.MSG_REGISTRAR_CLIENTE_OK);
	}
	
	@Test
	void registrarError() {
		Cliente cliente = DataMocks.retornarCliente();
		
		when(clienteWriteMock.registrarCliente(cliente)).thenThrow(new RuntimeException("Ocurrio un error"));
		ResponseEntity<Map<String,Object>> resp = controller.registrar(cliente);
		Assertions.assertThat(resp.getStatusCodeValue()).isEqualByComparingTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
		Assertions.assertThat(resp.getBody().get("estado")).isEqualTo(Constantes.ESTADO_ERROR);
		Assertions.assertThat(resp.getBody().get("mensaje")).isEqualTo(Constantes.MSG_REGISTRAR_CLIENTE_ERROR);
	}
	
	@Test
	void actualizarOk() {
		Cliente cliente = DataMocks.retornarCliente();
		cliente.setIdCliente((long) 1);
		
		when(clienteWriteMock.actualizarCliente(cliente)).thenReturn(true);
		ResponseEntity<Map<String,Object>> resp = controller.actualizar(cliente);
		Assertions.assertThat(resp.getStatusCodeValue()).isEqualByComparingTo(HttpStatus.CREATED.value());
		Assertions.assertThat(resp.getBody().get("estado")).isEqualTo(Constantes.ESTADO_OK);
		Assertions.assertThat(resp.getBody().get("mensaje")).isEqualTo(Constantes.MSG_ACTUALIZAR_CLIENTE_OK);
	}
	
	@Test
	void actualizarError() {
		Cliente cliente = DataMocks.retornarCliente();
		cliente.setIdCliente((long) 1);

		when(clienteWriteMock.actualizarCliente(cliente)).thenThrow(new RuntimeException("Ocurrio un error"));
		ResponseEntity<Map<String,Object>> resp = controller.actualizar(cliente);
		Assertions.assertThat(resp.getStatusCodeValue()).isEqualByComparingTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
		Assertions.assertThat(resp.getBody().get("estado")).isEqualTo(Constantes.ESTADO_ERROR);
		Assertions.assertThat(resp.getBody().get("mensaje")).isEqualTo(Constantes.MSG_ACTUALIZAR_CLIENTE_ERROR);
	}
	
	@Test
	void eliminarOk() {
		Long idcliente = (long) 1;
		when(clienteWriteMock.eliminarClientePorId(idcliente)).thenReturn(true);
		ResponseEntity<Map<String,Object>> resp = controller.eliminar(idcliente);
		Assertions.assertThat(resp.getStatusCodeValue()).isEqualByComparingTo(HttpStatus.ACCEPTED.value());
		Assertions.assertThat(resp.getBody().get("estado")).isEqualTo(Constantes.ESTADO_OK);
		Assertions.assertThat(resp.getBody().get("mensaje")).isEqualTo(Constantes.MSG_ELIMINAR_CLIENTE_OK);
	}
	
	@Test
	void eliminarError() {
		Long idcliente = (long) 1;	
		when(clienteWriteMock.eliminarClientePorId(idcliente)).thenThrow(new RuntimeException("Ocurrio un error"));
		ResponseEntity<Map<String,Object>> resp = controller.eliminar(idcliente);
		Assertions.assertThat(resp.getStatusCodeValue()).isEqualByComparingTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
		Assertions.assertThat(resp.getBody().get("estado")).isEqualTo(Constantes.ESTADO_ERROR);
		Assertions.assertThat(resp.getBody().get("mensaje")).isEqualTo(Constantes.MSG_ELIMINAR_ERROR);
	}
	
	@Test
	void eliminarRegistroNotFound() {
		Long idcliente = (long) 1;	
		when(clienteWriteMock.eliminarClientePorId(idcliente)).thenReturn(false);
		ResponseEntity<Map<String,Object>> resp = controller.eliminar(idcliente);
		Assertions.assertThat(resp.getStatusCodeValue()).isEqualByComparingTo(HttpStatus.ACCEPTED.value());
		Assertions.assertThat(resp.getBody().get("estado")).isEqualTo(Constantes.ESTADO_ERROR);
		Assertions.assertThat(resp.getBody().get("mensaje")).isEqualTo(Constantes.MSG_ELIMINAR_NOEXISTS);
	}
}
