package vass.assessment.server.productos.service.write.impl;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import vass.assessment.server.productos.models.Producto;
import vass.assessment.server.productos.repository.ProductoRepository;
import vass.assessment.server.productos.services.write.impl.ProductoServiceWriteImpl;
import vass.assessment.server.productos.utils.DataMocks;


@SpringBootTest
public class ProductoServiceWriteTests {
	
	@InjectMocks
	ProductoServiceWriteImpl productoWrite;

	@Mock
	ProductoRepository repoMock;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void registrarProductoOk() {
		Producto Producto = DataMocks.retornarProducto();
		
		when(repoMock.save(Producto)).thenReturn(Producto);
		Boolean resp = productoWrite.registrarProducto(Producto);
		Assertions.assertThat(true).isEqualTo(resp);
	}
	
	@Test
	void registrarProductoFail() {
		Producto Producto = DataMocks.retornarProducto();
		when(repoMock.save(Producto)).thenReturn(null);
		Boolean resp = productoWrite.registrarProducto(Producto);
		Assertions.assertThat(false).isEqualTo(resp);
	}

	@Test
	void registrarProductoError() {
		Producto Producto = DataMocks.retornarProducto();
		when(repoMock.save(Producto)).thenThrow(new RuntimeException("Error de conexion"));
		try {
			productoWrite.registrarProducto(Producto);
		} catch (Exception e) {
			Assertions.assertThat("Error de conexion").isEqualTo(e.getMessage());
		}
	}
	
	@Test
	void actualizarProductoOk() {
		Producto Producto = DataMocks.retornarProducto();
		Producto.setIdProducto((long) 1);
		when(repoMock.save(Producto)).thenReturn(Producto);
		Boolean resp = productoWrite.actualizarProducto(Producto);
		Assertions.assertThat(true).isEqualTo(resp);
	}
	
	@Test
	void actualizarProductoFail() {
		Producto Producto = DataMocks.retornarProducto();
		Producto.setIdProducto((long) 1);
		when(repoMock.save(Producto)).thenReturn(null);
		Boolean resp = productoWrite.actualizarProducto(Producto);
		Assertions.assertThat(false).isEqualTo(resp);
	}

	@Test
	void actualizarProductoError() {
		Producto Producto = DataMocks.retornarProducto();
		Producto.setIdProducto((long) 1);
		when(repoMock.save(Producto)).thenThrow(new RuntimeException("Error de conexion"));
		try {
			productoWrite.actualizarProducto(Producto);
		} catch (Exception e) {
			Assertions.assertThat("Error de conexion").isEqualTo(e.getMessage());
		}
	}
	
	@Test
	void eliminarProductoOk() {
		Long idProducto = (long) 1;
		when(repoMock.existsById(idProducto)).thenReturn(false);
		doNothing().when(repoMock).deleteById(idProducto);
		Boolean resp = productoWrite.eliminarProductoPorId(idProducto);
		Assertions.assertThat(true).isEqualTo(resp);
	}
	
	@Test
	void eliminarProductoNoDelete() {
		Long idProducto = (long) 1;
		when(repoMock.existsById(idProducto)).thenReturn(true);
		doNothing().when(repoMock).deleteById(idProducto);
		Boolean resp = productoWrite.eliminarProductoPorId(idProducto);
		Assertions.assertThat(false).isEqualTo(resp);
	}
	
	@Test
	void eliminarProductoError() {
		Long idProducto = (long) 1;
		when(repoMock.existsById(idProducto)).thenThrow(new RuntimeException("Error de conexion"));
		try {
			productoWrite.eliminarProductoPorId(idProducto);
		} catch (Exception e) {
			Assertions.assertThat("Error de conexion").isEqualTo(e.getMessage());
		}
	}
}
