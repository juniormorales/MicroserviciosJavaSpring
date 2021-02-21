package vass.assessment.server.productos.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vass.assessment.server.productos.models.Producto;
import vass.assessment.server.productos.services.read.IProductoServiceRead;
import vass.assessment.server.productos.services.write.IProductoServiceWrite;
import vass.assessment.server.productos.utils.Constantes;

@RestController
@RequestMapping("/api/producto")
public class ProductoController {
	
	@Autowired
	IProductoServiceRead productoRead;
	
	@Autowired
	IProductoServiceWrite productoWrite;
	
	@GetMapping("/listar")
	public ResponseEntity<Map<String,Object>> listar(){
		Map<String,Object> map = new HashMap<>();
		try {
			List<Producto> listaproductos = productoRead.listarTodo();
			map.put("estado", Constantes.ESTADO_OK );
			map.put("aaData",listaproductos);
			return new ResponseEntity<>(map,HttpStatus.OK);
		} catch (Exception e) {
			map.put("estado", Constantes.ESTADO_ERROR );
			map.put("mensaje", Constantes.MSG_LISTAR_PRODUCTOS_ERROR );
			return new ResponseEntity<>(map,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<Map<String,Object>> registrar(@RequestBody Producto producto){
		Map<String,Object> map = new HashMap<>();
		try {
			productoWrite.registrarProducto(producto);
			map.put("estado", Constantes.ESTADO_OK );
			map.put("mensaje",Constantes.MSG_REGISTRAR_PRODUCTO_OK);
			return new ResponseEntity<>(map,HttpStatus.CREATED);
		} catch (Exception e) {
			map.put("estado", Constantes.ESTADO_ERROR );
			map.put("mensaje", Constantes.MSG_REGISTRAR_PRODUCTO_ERROR );
			return new ResponseEntity<>(map,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/actualizar")
	public ResponseEntity<Map<String,Object>> actualizar(@RequestBody Producto producto){
		Map<String,Object> map = new HashMap<>();
		try {
			productoWrite.actualizarProducto(producto);
			map.put("estado", Constantes.ESTADO_OK);
			map.put("mensaje",Constantes.MSG_ACTUALIZAR_PRODUCTO_OK);
			return new ResponseEntity<>(map,HttpStatus.CREATED);
		} catch (Exception e) {
			map.put("estado", Constantes.ESTADO_ERROR );
			map.put("mensaje", Constantes.MSG_ACTUALIZAR_PRODUCTO_ERROR );
			return new ResponseEntity<>(map,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/eliminar/{id}") 
	public ResponseEntity<Map<String,Object>> eliminar(@PathVariable("id") Long id){
		Map<String,Object> map = new HashMap<>();
		try {
			if(productoWrite.eliminarProductoPorId(id)) {
				map.put("estado", Constantes.ESTADO_OK);
				map.put("mensaje",Constantes.MSG_ELIMINAR_PRODUCTO_OK);
			}else {
				map.put("estado", Constantes.ESTADO_ERROR);
				map.put("mensaje",Constantes.MSG_ELIMINAR_NOEXISTS);
			}
			return new ResponseEntity<>(map,HttpStatus.ACCEPTED);
		} catch (Exception e) {
			map.put("estado", Constantes.ESTADO_ERROR );
			map.put("mensaje", Constantes.MSG_ELIMINAR_ERROR );
			return new ResponseEntity<>(map,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
