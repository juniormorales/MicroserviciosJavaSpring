package vass.assessment.server.security.controllers;

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

import vass.assessment.server.security.models.Usuario;
import vass.assessment.server.security.services.read.IUsuarioServiceRead;
import vass.assessment.server.security.services.write.IUsuarioServiceWrite;
import vass.assessment.server.security.utils.Constantes;
import vass.assessment.server.security.utils.DataMocks;


@SpringBootTest
public class UsuarioControllerTests {
	
	@InjectMocks
	UsuarioController controller;
	
	@Mock
	IUsuarioServiceRead usuarioReadMock;
	
	@Mock
	IUsuarioServiceWrite usuarioWriteMock;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void listarCorrecto() {
		List<Usuario> lsUsuariosMock = new ArrayList<>();
		when(usuarioReadMock.listarTodo()).thenReturn(lsUsuariosMock);
		ResponseEntity<Map<String,Object>> resp = controller.listar();
		Assertions.assertThat(resp.getStatusCodeValue()).isEqualByComparingTo(HttpStatus.OK.value());
		Assertions.assertThat(resp.getBody().get("estado")).isEqualTo(Constantes.ESTADO_OK);
	}
	
	@Test
	void listarError() {
		when(usuarioReadMock.listarTodo()).thenThrow(new RuntimeException("Ocurrio un error"));
		ResponseEntity<Map<String,Object>> resp = controller.listar();
		Assertions.assertThat(resp.getStatusCodeValue()).isEqualByComparingTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
		Assertions.assertThat(resp.getBody().get("estado")).isEqualTo(Constantes.ESTADO_ERROR);
		Assertions.assertThat(resp.getBody().get("mensaje")).isEqualTo(Constantes.MSG_LISTAR_ERROR);
	}
		
	@Test
	void registrarOk() {
		Usuario Usuario = DataMocks.retornarUsuario();
		
		when(usuarioWriteMock.registrarUsuario(Usuario)).thenReturn(true);
		ResponseEntity<Map<String,Object>> resp = controller.registrar(Usuario);
		Assertions.assertThat(resp.getStatusCodeValue()).isEqualByComparingTo(HttpStatus.CREATED.value());
		Assertions.assertThat(resp.getBody().get("estado")).isEqualTo(Constantes.ESTADO_OK);
		Assertions.assertThat(resp.getBody().get("mensaje")).isEqualTo(Constantes.MSG_REGISTRAR_USUARIO_OK);
	}
	
	@Test
	void registrarUsuarioRepetido() {
		Usuario Usuario = DataMocks.retornarUsuario();
		
		when(usuarioWriteMock.registrarUsuario(Usuario)).thenReturn(false);
		ResponseEntity<Map<String,Object>> resp = controller.registrar(Usuario);
		Assertions.assertThat(resp.getStatusCodeValue()).isEqualByComparingTo(HttpStatus.CONFLICT.value());
		Assertions.assertThat(resp.getBody().get("estado")).isEqualTo(Constantes.ESTADO_ERROR);
		Assertions.assertThat(resp.getBody().get("mensaje")).isEqualTo(Constantes.MSG_REGISTRAR_USUARIO_ALREADYEXISTS);
	}
	
	@Test
	void registrarError() {
		Usuario Usuario = DataMocks.retornarUsuario();
		
		when(usuarioWriteMock.registrarUsuario(Usuario)).thenThrow(new RuntimeException("Ocurrio un error"));
		ResponseEntity<Map<String,Object>> resp = controller.registrar(Usuario);
		Assertions.assertThat(resp.getStatusCodeValue()).isEqualByComparingTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
		Assertions.assertThat(resp.getBody().get("estado")).isEqualTo(Constantes.ESTADO_ERROR);
		Assertions.assertThat(resp.getBody().get("mensaje")).isEqualTo(Constantes.MSG_REGISTRAR_USUARIO_ERROR);
	}
}
