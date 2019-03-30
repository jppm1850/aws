package aws.mitocode.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
public class AngularController {

	@RequestMapping(value= {"/security**","/about**","/logout**","/mapa**","/problema**"})
	public String redirectTo(){
		return "index"; 
	}	
}
