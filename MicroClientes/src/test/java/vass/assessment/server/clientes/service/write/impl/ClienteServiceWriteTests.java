package vass.assessment.server.clientes.service.write.impl;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import vass.assessment.server.clientes.models.Cliente;
import vass.assessment.server.clientes.repository.ClienteRepository;
import vass.assessment.server.clientes.utils.DataMocks;
import vass.assessment.server.clientes.write.impl.ClienteServiceWriteImpl;

@SpringBootTest
public class ClienteServiceWriteTests {
	
	@InjectMocks
	ClienteServiceWriteImpl clienteWrite;

	@Mock
	ClienteRepository repoMock;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void registrarClienteOk() {
		Cliente cliente = DataMocks.retornarCliente();
		
		when(repoMock.save(cliente)).thenReturn(cliente);
		Boolean resp = clienteWrite.registrarCliente(cliente);
		Assertions.assertThat(true).isEqualTo(resp);
	}
	
	@Test
	void registrarClienteFail() {
		Cliente cliente = DataMocks.retornarCliente();
		when(repoMock.save(cliente)).thenReturn(null);
		Boolean resp = clienteWrite.registrarCliente(cliente);
		Assertions.assertThat(false).isEqualTo(resp);
	}

	@Test
	void registrarClienteError() {
		Cliente cliente = DataMocks.retornarCliente();
		when(repoMock.save(cliente)).thenThrow(new RuntimeException("Error de conexion"));
		try {
			clienteWrite.registrarCliente(cliente);
		} catch (Exception e) {
			Assertions.assertThat("Error de conexion").isEqualTo(e.getMessage());
		}
	}
	
	@Test
	void actualizarClienteOk() {
		Cliente cliente = DataMocks.retornarCliente();
		cliente.setIdCliente((long) 1);
		when(repoMock.save(cliente)).thenReturn(cliente);
		Boolean resp = clienteWrite.actualizarCliente(cliente);
		Assertions.assertThat(true).isEqualTo(resp);
	}
	
	@Test
	void actualizarClienteFail() {
		Cliente cliente = DataMocks.retornarCliente();
		cliente.setIdCliente((long) 1);
		when(repoMock.save(cliente)).thenReturn(null);
		Boolean resp = clienteWrite.actualizarCliente(cliente);
		Assertions.assertThat(false).isEqualTo(resp);
	}

	@Test
	void actualizarClienteError() {
		Cliente cliente = DataMocks.retornarCliente();
		cliente.setIdCliente((long) 1);
		when(repoMock.save(cliente)).thenThrow(new RuntimeException("Error de conexion"));
		try {
			clienteWrite.actualizarCliente(cliente);
		} catch (Exception e) {
			Assertions.assertThat("Error de conexion").isEqualTo(e.getMessage());
		}
	}
	
	@Test
	void eliminarClienteOk() {
		Long idcliente = (long) 1;
		when(repoMock.existsById(idcliente)).thenReturn(false);
		doNothing().when(repoMock).deleteById(idcliente);
		Boolean resp = clienteWrite.eliminarClientePorId(idcliente);
		Assertions.assertThat(true).isEqualTo(resp);
	}
	
	@Test
	void eliminarClienteNoDelete() {
		Long idcliente = (long) 1;
		when(repoMock.existsById(idcliente)).thenReturn(true);
		doNothing().when(repoMock).deleteById(idcliente);
		Boolean resp = clienteWrite.eliminarClientePorId(idcliente);
		Assertions.assertThat(false).isEqualTo(resp);
	}
	
	@Test
	void eliminarClienteError() {
		Long idcliente = (long) 1;
		when(repoMock.existsById(idcliente)).thenThrow(new RuntimeException("Error de conexion"));
		try {
			clienteWrite.eliminarClientePorId(idcliente);
		} catch (Exception e) {
			Assertions.assertThat("Error de conexion").isEqualTo(e.getMessage());
		}
	}
}
