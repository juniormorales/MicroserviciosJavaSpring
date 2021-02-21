package vass.assessment.server.productos.controllers;

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

import vass.assessment.server.productos.models.Producto;
import vass.assessment.server.productos.services.read.IProductoServiceRead;
import vass.assessment.server.productos.services.write.IProductoServiceWrite;
import vass.assessment.server.productos.utils.Constantes;
import vass.assessment.server.productos.utils.DataMocks;

@SpringBootTest
public class ProductoControllerTests {
	
	@InjectMocks
	ProductoController controller;
	
	@Mock
	IProductoServiceRead productoReadMock;
	
	@Mock
	IProductoServiceWrite productoWriteMock;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void listarCorrecto() {
		List<Producto> lsProductosMock = new ArrayList<>();
		when(productoReadMock.listarTodo()).thenReturn(lsProductosMock);
		ResponseEntity<Map<String,Object>> resp = controller.listar();
		Assertions.assertThat(resp.getStatusCodeValue()).isEqualByComparingTo(HttpStatus.OK.value());
		Assertions.assertThat(resp.getBody().get("estado")).isEqualTo(Constantes.ESTADO_OK);
	}
	
	@Test
	void listarError() {
		when(productoReadMock.listarTodo()).thenThrow(new RuntimeException("Ocurrio un error"));
		ResponseEntity<Map<String,Object>> resp = controller.listar();
		Assertions.assertThat(resp.getStatusCodeValue()).isEqualByComparingTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
		Assertions.assertThat(resp.getBody().get("estado")).isEqualTo(Constantes.ESTADO_ERROR);
		Assertions.assertThat(resp.getBody().get("mensaje")).isEqualTo(Constantes.MSG_LISTAR_PRODUCTOS_ERROR);
	}
	
	@Test
	void registrarOk() {
		Producto Producto = DataMocks.retornarProducto();
		
		when(productoWriteMock.registrarProducto(Producto)).thenReturn(true);
		ResponseEntity<Map<String,Object>> resp = controller.registrar(Producto);
		Assertions.assertThat(resp.getStatusCodeValue()).isEqualByComparingTo(HttpStatus.CREATED.value());
		Assertions.assertThat(resp.getBody().get("estado")).isEqualTo(Constantes.ESTADO_OK);
		Assertions.assertThat(resp.getBody().get("mensaje")).isEqualTo(Constantes.MSG_REGISTRAR_PRODUCTO_OK);
	}
	
	@Test
	void registrarError() {
		Producto Producto = DataMocks.retornarProducto();
		
		when(productoWriteMock.registrarProducto(Producto)).thenThrow(new RuntimeException("Ocurrio un error"));
		ResponseEntity<Map<String,Object>> resp = controller.registrar(Producto);
		Assertions.assertThat(resp.getStatusCodeValue()).isEqualByComparingTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
		Assertions.assertThat(resp.getBody().get("estado")).isEqualTo(Constantes.ESTADO_ERROR);
		Assertions.assertThat(resp.getBody().get("mensaje")).isEqualTo(Constantes.MSG_REGISTRAR_PRODUCTO_ERROR);
	}
	
	@Test
	void actualizarOk() {
		Producto Producto = DataMocks.retornarProducto();
		Producto.setIdProducto((long) 1);
		
		when(productoWriteMock.actualizarProducto(Producto)).thenReturn(true);
		ResponseEntity<Map<String,Object>> resp = controller.actualizar(Producto);
		Assertions.assertThat(resp.getStatusCodeValue()).isEqualByComparingTo(HttpStatus.CREATED.value());
		Assertions.assertThat(resp.getBody().get("estado")).isEqualTo(Constantes.ESTADO_OK);
		Assertions.assertThat(resp.getBody().get("mensaje")).isEqualTo(Constantes.MSG_ACTUALIZAR_PRODUCTO_OK);
	}
	
	@Test
	void actualizarError() {
		Producto Producto = DataMocks.retornarProducto();
		Producto.setIdProducto((long) 1);

		when(productoWriteMock.actualizarProducto(Producto)).thenThrow(new RuntimeException("Ocurrio un error"));
		ResponseEntity<Map<String,Object>> resp = controller.actualizar(Producto);
		Assertions.assertThat(resp.getStatusCodeValue()).isEqualByComparingTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
		Assertions.assertThat(resp.getBody().get("estado")).isEqualTo(Constantes.ESTADO_ERROR);
		Assertions.assertThat(resp.getBody().get("mensaje")).isEqualTo(Constantes.MSG_ACTUALIZAR_PRODUCTO_ERROR);
	}
	
	@Test
	void eliminarOk() {
		Long idProducto = (long) 1;
		when(productoWriteMock.eliminarProductoPorId(idProducto)).thenReturn(true);
		ResponseEntity<Map<String,Object>> resp = controller.eliminar(idProducto);
		Assertions.assertThat(resp.getStatusCodeValue()).isEqualByComparingTo(HttpStatus.ACCEPTED.value());
		Assertions.assertThat(resp.getBody().get("estado")).isEqualTo(Constantes.ESTADO_OK);
		Assertions.assertThat(resp.getBody().get("mensaje")).isEqualTo(Constantes.MSG_ELIMINAR_PRODUCTO_OK);
	}
	
	@Test
	void eliminarError() {
		Long idProducto = (long) 1;	
		when(productoWriteMock.eliminarProductoPorId(idProducto)).thenThrow(new RuntimeException("Ocurrio un error"));
		ResponseEntity<Map<String,Object>> resp = controller.eliminar(idProducto);
		Assertions.assertThat(resp.getStatusCodeValue()).isEqualByComparingTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
		Assertions.assertThat(resp.getBody().get("estado")).isEqualTo(Constantes.ESTADO_ERROR);
		Assertions.assertThat(resp.getBody().get("mensaje")).isEqualTo(Constantes.MSG_ELIMINAR_ERROR);
	}
	
	@Test
	void eliminarRegistroNotFound() {
		Long idProducto = (long) 1;	
		when(productoWriteMock.eliminarProductoPorId(idProducto)).thenReturn(false);
		ResponseEntity<Map<String,Object>> resp = controller.eliminar(idProducto);
		Assertions.assertThat(resp.getStatusCodeValue()).isEqualByComparingTo(HttpStatus.ACCEPTED.value());
		Assertions.assertThat(resp.getBody().get("estado")).isEqualTo(Constantes.ESTADO_ERROR);
		Assertions.assertThat(resp.getBody().get("mensaje")).isEqualTo(Constantes.MSG_ELIMINAR_NOEXISTS);
	}
}
