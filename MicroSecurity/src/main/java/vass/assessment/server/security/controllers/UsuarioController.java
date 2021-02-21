package vass.assessment.server.security.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vass.assessment.server.security.models.Usuario;
import vass.assessment.server.security.services.read.IUsuarioServiceRead;
import vass.assessment.server.security.services.write.IUsuarioServiceWrite;
import vass.assessment.server.security.utils.Constantes;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
	
	@Autowired
	IUsuarioServiceRead usuarioRead;
	
	@Autowired
	IUsuarioServiceWrite usuarioWrite;
	
	@GetMapping("/listar")
	public ResponseEntity<Map<String,Object>> listar(){
		Map<String,Object> map = new HashMap<>();
		try {
			List<Usuario> listarusuarios = usuarioRead.listarTodo();
			map.put("estado", Constantes.ESTADO_OK );
			map.put("aaData",listarusuarios);
			return new ResponseEntity<>(map,HttpStatus.OK);
		} catch (Exception e) {
			map.put("estado", Constantes.ESTADO_ERROR );
			map.put("mensaje", Constantes.MSG_LISTAR_ERROR );
			return new ResponseEntity<>(map,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<Map<String,Object>> registrar(@RequestBody Usuario usuario){
		Map<String,Object> map = new HashMap<>();
		try {
			if(usuarioWrite.registrarUsuario(usuario)) {
				map.put("estado", Constantes.ESTADO_OK );
				map.put("mensaje",Constantes.MSG_REGISTRAR_USUARIO_OK);
				return new ResponseEntity<>(map,HttpStatus.CREATED);
			}else {
				map.put("estado", Constantes.ESTADO_ERROR );
				map.put("mensaje",Constantes.MSG_REGISTRAR_USUARIO_ALREADYEXISTS);
				return new ResponseEntity<>(map,HttpStatus.CONFLICT);
			}	
		} catch (Exception e) {
			map.put("estado", Constantes.ESTADO_ERROR );
			map.put("mensaje", Constantes.MSG_REGISTRAR_USUARIO_ERROR );
			return new ResponseEntity<>(map,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
