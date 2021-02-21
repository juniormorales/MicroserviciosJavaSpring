package vass.assessment.server.clientes.controllers;

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

import vass.assessment.server.clientes.models.Cliente;
import vass.assessment.server.clientes.services.read.IClienteServiceRead;
import vass.assessment.server.clientes.utils.Constantes;
import vass.assessment.server.clientes.write.IClienteServiceWrite;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
	
	@Autowired
	IClienteServiceRead clienteRead;
	
	@Autowired
	IClienteServiceWrite clienteWrite;
	
	@GetMapping("/listar")
	public ResponseEntity<Map<String,Object>> listar(){
		Map<String,Object> map = new HashMap<>();
		try {
			List<Cliente> listaclientes = clienteRead.listarTodo();
			map.put("estado", Constantes.ESTADO_OK );
			map.put("aaData",listaclientes);
			return new ResponseEntity<>(map,HttpStatus.OK);
		} catch (Exception e) {
			map.put("estado", Constantes.ESTADO_ERROR );
			map.put("mensaje", Constantes.MSG_LISTAR_CLIENTES_ERROR );
			return new ResponseEntity<>(map,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<Map<String,Object>> registrar(@RequestBody Cliente cliente){
		Map<String,Object> map = new HashMap<>();
		try {
			clienteWrite.registrarCliente(cliente);
			map.put("estado", Constantes.ESTADO_OK );
			map.put("mensaje",Constantes.MSG_REGISTRAR_CLIENTE_OK);
			return new ResponseEntity<>(map,HttpStatus.CREATED);
		} catch (Exception e) {
			map.put("estado", Constantes.ESTADO_ERROR );
			map.put("mensaje", Constantes.MSG_REGISTRAR_CLIENTE_ERROR );
			return new ResponseEntity<>(map,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/actualizar")
	public ResponseEntity<Map<String,Object>> actualizar(@RequestBody Cliente cliente){
		Map<String,Object> map = new HashMap<>();
		try {
			clienteWrite.actualizarCliente(cliente);
			map.put("estado", Constantes.ESTADO_OK);
			map.put("mensaje",Constantes.MSG_ACTUALIZAR_CLIENTE_OK);
			return new ResponseEntity<>(map,HttpStatus.CREATED);
		} catch (Exception e) {
			map.put("estado", Constantes.ESTADO_ERROR );
			map.put("mensaje", Constantes.MSG_ACTUALIZAR_CLIENTE_ERROR );
			return new ResponseEntity<>(map,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/eliminar/{id}") 
	public ResponseEntity<Map<String,Object>> eliminar(@PathVariable("id") Long id){
		Map<String,Object> map = new HashMap<>();
		try {
			if(clienteWrite.eliminarClientePorId(id)) {
				map.put("estado", Constantes.ESTADO_OK);
				map.put("mensaje",Constantes.MSG_ELIMINAR_CLIENTE_OK);
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
