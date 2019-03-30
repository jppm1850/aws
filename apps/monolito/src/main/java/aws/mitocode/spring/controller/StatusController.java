package aws.mitocode.spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("status")
public class StatusController {

	@GetMapping(value="verificar")
	public ResponseEntity<String> verificarToken(){
		return new ResponseEntity<String>("OK", HttpStatus.OK);
	}
}
