package aws.mitocode.spring.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aws.mitocode.spring.dto.BasicAccessDTO;
import aws.mitocode.spring.dto.LoginDTO;
import aws.mitocode.spring.dto.RenewPasswordFirstDTO;
import aws.mitocode.spring.dto.RespuestaApi;
import aws.mitocode.spring.dto.UpdatePasswordDTO;
import aws.mitocode.spring.service.SecurityService;

@RestController
@CrossOrigin
@RequestMapping("api/security")
public class ApiSecurityController {

	@Autowired
	private SecurityService securityService;

	@PostMapping(value="token", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaApi> verificarToken(){
		return new ResponseEntity<RespuestaApi>(
				new RespuestaApi("OK",SecurityContextHolder.getContext().getAuthentication().getPrincipal()), HttpStatus.OK);
	}
	
	@PostMapping(value="login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaApi> login(
			@RequestBody LoginDTO login){
		return new ResponseEntity<RespuestaApi>(
				securityService.getToken(login.getUsername(), login.getPassword()), HttpStatus.OK);
	}
	
	@PostMapping(value="first-reset-password", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaApi> renewPasswordFirst(
			@RequestBody RenewPasswordFirstDTO updatePassword){
		return new ResponseEntity<RespuestaApi>(
				securityService.resetNewPasswordFirst(updatePassword), HttpStatus.OK);
	}
	
	@PostMapping(value="change-password", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaApi> updatePassword(
			@RequestBody UpdatePasswordDTO updatePassword){
		return new ResponseEntity<RespuestaApi>(
				securityService.updatePassword(updatePassword), HttpStatus.OK);
	}
	
	@PostMapping(value="signout", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaApi> signOut(
			@RequestBody BasicAccessDTO token){
		return new ResponseEntity<RespuestaApi>(
				securityService.signOut(token.getToken()), HttpStatus.OK);
	}
	
	@PostMapping(value="refresh-token", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RespuestaApi> refreshToken(
			@RequestBody BasicAccessDTO token){
		return new ResponseEntity<RespuestaApi>(
				securityService.refreshToken(token.getToken()), HttpStatus.OK);
	}
}
