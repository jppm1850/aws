package aws.mitocode.spring.controller.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aws.mitocode.spring.dto.RespuestaApi;
import aws.mitocode.spring.model.Negocio;
import aws.mitocode.spring.service.INegocioService;

@RestController
@CrossOrigin
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("api/negocio")
public class ApiNegocio {

private static final Logger logger = LoggerFactory.getLogger(ApiNegocio.class);
	
	@Autowired
	private INegocioService serviceNegocio;
	
	@GetMapping(value="listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Negocio>> obtenerTodos(Pageable pageable){
		try {
			return new ResponseEntity<List<Negocio>>(
					serviceNegocio.obtenerTodos(), HttpStatus.OK);
		}catch(Exception e) {
			logger.error("Error: ",e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> obtenerTodosPaginado(Pageable pageable){
		try {
			return new ResponseEntity<Page<Negocio>>(
					serviceNegocio.obtenerDatosPaginados(pageable), HttpStatus.OK);
		}catch(Exception e) {
			logger.error("Error: ",e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value="registrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaApi> guardar(
			@RequestBody Negocio negocio){
		try {
			serviceNegocio.guardarDatos(negocio);
			return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK",""), HttpStatus.OK);
		}catch(Exception e) {
			logger.error("Error: ",e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value="eliminar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaApi> eliminar(
			@PathVariable int id){
		try {
			serviceNegocio.eliminarDatos(id);
			return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK",""), HttpStatus.OK);
		}catch(Exception e) {
			logger.error("Error: ",e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
