package vass.assessment.server.reclamos.controllers;

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

import vass.assessment.server.reclamos.models.Reclamo;
import vass.assessment.server.reclamos.service.read.IReclamoServiceRead;
import vass.assessment.server.reclamos.service.write.IReclamoServiceWrite;
import vass.assessment.server.reclamos.utils.Constantes;


@RestController
@RequestMapping("/api/reclamo")
public class ReclamoController {
	
	@Autowired
	IReclamoServiceRead reclamoRead;
	
	@Autowired
	IReclamoServiceWrite reclamoWrite;
	
	@GetMapping("/listar")
	public ResponseEntity<Map<String,Object>> listar(){
		Map<String,Object> map = new HashMap<>();
		try {
			List<Reclamo> listareclamos = reclamoRead.listarTodo();
			map.put("estado", Constantes.ESTADO_OK );
			map.put("aaData",listareclamos);
			return new ResponseEntity<>(map,HttpStatus.OK);
		} catch (Exception e) {
			map.put("estado", Constantes.ESTADO_ERROR );
			map.put("mensaje", Constantes.MSG_LISTAR_ERROR );
			return new ResponseEntity<>(map,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/listarPorCliente/{nrodoc}")
	public ResponseEntity<Map<String,Object>> listarPorCliente(@PathVariable("nrodoc")String nrodoc){
		Map<String,Object> map = new HashMap<>();
		try {
			List<Reclamo> listareclamos = reclamoRead.listarPorCliente(nrodoc);
			map.put("estado", Constantes.ESTADO_OK );
			map.put("aaData",listareclamos);
			return new ResponseEntity<>(map,HttpStatus.OK);
		} catch (Exception e) {
			map.put("estado", Constantes.ESTADO_ERROR );
			map.put("mensaje", Constantes.MSG_LISTAR_ERROR );
			return new ResponseEntity<>(map,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<Map<String,Object>> registrar(@RequestBody Reclamo reclamo){
		Map<String,Object> map = new HashMap<>();
		try {
			reclamoWrite.registrarReclamo(reclamo);
			map.put("estado", Constantes.ESTADO_OK );
			map.put("mensaje",Constantes.MSG_REGISTRAR_RECLAMO_OK);
			return new ResponseEntity<>(map,HttpStatus.CREATED);
		} catch (Exception e) {
			map.put("estado", Constantes.ESTADO_ERROR );
			map.put("mensaje", Constantes.MSG_REGISTRAR_RECLAMO_ERROR );
			return new ResponseEntity<>(map,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/actualizar")
	public ResponseEntity<Map<String,Object>> actualizar(@RequestBody Reclamo reclamo){
		Map<String,Object> map = new HashMap<>();
		try {
			reclamoWrite.actualizarReclamo(reclamo);
			map.put("estado", Constantes.ESTADO_OK);
			map.put("mensaje",Constantes.MSG_ACTUALIZAR_RECLAMO_OK);
			return new ResponseEntity<>(map,HttpStatus.CREATED);
		} catch (Exception e) {
			map.put("estado", Constantes.ESTADO_ERROR );
			map.put("mensaje", Constantes.MSG_ACTUALIZAR_RECLAMO_ERROR );
			return new ResponseEntity<>(map,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/eliminar/{id}") 
	public ResponseEntity<Map<String,Object>> eliminar(@PathVariable("id") Long id){
		Map<String,Object> map = new HashMap<>();
		try {
			if(reclamoWrite.eliminarReclamoPorId(id)) {
				map.put("estado", Constantes.ESTADO_OK);
				map.put("mensaje",Constantes.MSG_ELIMINAR_RECLAMO_OK);
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
