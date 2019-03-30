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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aws.mitocode.spring.dto.RespuestaApi;
import aws.mitocode.spring.model.FeedBack;
import aws.mitocode.spring.model.Problema;
import aws.mitocode.spring.service.IFeedBackService;
import aws.mitocode.spring.service.IProblemaService;

@RestController
@CrossOrigin
@RequestMapping("api/feedback")
public class ApiFeedBackController {

	private static final Logger logger = LoggerFactory.getLogger(ApiFeedBackController.class);
	
	@Autowired
	private IFeedBackService feedbackService;
	
	@Autowired
	private IProblemaService problemaService;
	
	@GetMapping(value="listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> obtenerTodos(Pageable pageable){
		try {
			User usuario = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return new ResponseEntity<Page<FeedBack>>(
					feedbackService.obtenerDatosPaginados(pageable, usuario.getUsername(), usuario.getAuthorities()), HttpStatus.OK);
		}catch(Exception e) {
			logger.error("Error: ",e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value="registrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaApi> guardarFeedBack(
			@RequestBody FeedBack feedback){
		try {
			User usuario = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			feedback.setIdUsuario(usuario.getUsername());
			feedbackService.guardarDatos(feedback);
			return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK",""), HttpStatus.OK);
		}catch(Exception e) {
			logger.error("Error: ",e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value="eliminar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaApi> eliminarFeedBack(
			@PathVariable int id){
		try {
			feedbackService.eliminarDatos(id);
			return new ResponseEntity<RespuestaApi>(new RespuestaApi("OK",""), HttpStatus.OK);
		}catch(Exception e) {
			logger.error("Error: ",e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="problema/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Problema>> obtenerTodos(){
		try {
			return new ResponseEntity<List<Problema>>(problemaService.obtenerTodos(), HttpStatus.OK);
		}catch(Exception e) {
			logger.error("Error: ",e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
