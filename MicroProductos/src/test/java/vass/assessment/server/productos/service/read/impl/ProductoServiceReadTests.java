package vass.assessment.server.productos.service.read.impl;

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

import vass.assessment.server.productos.models.Producto;
import vass.assessment.server.productos.repository.ProductoRepository;
import vass.assessment.server.productos.services.read.impl.ProductoServiceReadImpl;


@SpringBootTest
public class ProductoServiceReadTests {
	

	@InjectMocks
	ProductoServiceReadImpl productoRead;

	@Mock
	ProductoRepository repoMock;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void listarTodoOk() {
		List<Producto> lsProductosMock = new ArrayList<>();
		when(repoMock.findAll()).thenReturn(lsProductosMock);
		List<Producto> lssend = productoRead.listarTodo();
		Assertions.assertThat(lsProductosMock).isEqualTo(lssend);
	}

	@Test
	void listarTodoError() {
		when(repoMock.findAll()).thenThrow(new RuntimeException("Error de conexion"));
		try {
			productoRead.listarTodo();
		} catch (Exception e) {
			Assertions.assertThat("Error de conexion").isEqualTo(e.getMessage());
		}
	}
}
