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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aws.mitocode.spring.dto.RespuestaApi;
import aws.mitocode.spring.model.Sede;
import aws.mitocode.spring.service.ISedeService;

@RestController
@CrossOrigin
@RequestMapping("api/sede")
public class ApiSedeController {
	
	private static final Logger logger = LoggerFactory.getLogger(ApiSedeController.class);

	@Autowired
	private ISedeService sedeService;
	
	@GetMapping(value="listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Sede>> obtenerTodos(
			@RequestParam int tipoNegocio,
			@RequestParam double latitud,
			@RequestParam double longitud,
			@RequestParam double radio){
		try {
			return new ResponseEntity<List<Sede>>(
					sedeService.obtenerSedesPorRadioLatitudLongitud(tipoNegocio, latitud, longitud, radio), 
					HttpStatus.OK);
		}catch(Exception e) {
			logger.error("Error: ",e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value="listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> obtenerTodosPaginado(Pageable pageable){
		try {
			return new ResponseEntity<Page<Sede>>(
					sedeService.obtenerDatosPaginados(pageable), HttpStatus.OK);
		}catch(Exception e) {
			logger.error("Error: ",e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value="registrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaApi> guardar(
			@RequestBody Sede sede){
		try {
			sedeService.guardarDatos(sede);
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
			sedeService.eliminarDatos(id);
			return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK",""), HttpStatus.OK);
		}catch(Exception e) {
			logger.error("Error: ",e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
