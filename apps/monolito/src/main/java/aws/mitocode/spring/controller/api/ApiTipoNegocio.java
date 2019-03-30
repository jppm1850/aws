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
import aws.mitocode.spring.model.TipoNegocio;
import aws.mitocode.spring.service.ITipoNegocioService;

@RestController
@CrossOrigin
@RequestMapping("api/tiponegocio")
public class ApiTipoNegocio {

	private static final Logger logger = LoggerFactory.getLogger(ApiTipoNegocio.class);
	
	@Autowired
	private ITipoNegocioService serviceTipoNegocio;
	
	@GetMapping(value="listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TipoNegocio>> obtenerTodos(){
		try {
			return new ResponseEntity<List<TipoNegocio>>(
					serviceTipoNegocio.obtenerTodos(), HttpStatus.OK);
		}catch(Exception e) {
			logger.error("Error: ",e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value="listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> obtenerTodosPaginados(Pageable pageable){
		try {
			return new ResponseEntity<Page<TipoNegocio>>(
					serviceTipoNegocio.obtenerDatosPaginados(pageable), HttpStatus.OK);
		}catch(Exception e) {
			logger.error("Error: ",e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value="registrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaApi> guardar(
			@RequestBody TipoNegocio tipoNegocio){
		try {
			serviceTipoNegocio.guardarDatos(tipoNegocio);
			return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK",""), HttpStatus.OK);
		}catch(Exception e) {
			logger.error("Error: ",e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping(value="eliminar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaApi> eliminar(
			@PathVariable int id){
		try {
			serviceTipoNegocio.eliminarDatos(id);
			return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK",""), HttpStatus.OK);
		}catch(Exception e) {
			logger.error("Error: ",e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
