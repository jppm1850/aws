package aws.mitocode.spring.service.impl;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import aws.mitocode.spring.dto.SNSMessage;
import aws.mitocode.spring.model.FeedBack;
import aws.mitocode.spring.service.IColaSQSService;
import aws.mitocode.spring.service.IEmailService;

@Service
public class SQSServiceImpl implements IColaSQSService {

	private Logger logger = Logger.getLogger(SQSServiceImpl.class);
	
	@Autowired
    private JmsTemplate jmsTemplate;
	
	@Autowired
    private ObjectMapper mapper;
	
	@Autowired
	private IEmailService emailService;
	
	//Nombre de la cola SQS configurado en AWS
	@JmsListener(destination = "sqs-sns-miColaNormal-1Y09A84XCJATB")
    public void readJMS(String json) {
    	try {
    		logger.info("JSON SQS: "+json);
    		
    		//SNSMessage mensajeSNS = mapper.readValue(json, SNSMessage.class);
    		
			//FeedBack feedback = mapper.readValue(mensajeSNS.getMessage(), FeedBack.class);
			
			FeedBack feedback = mapper.readValue(json, FeedBack.class);
    		
    		logger.info("Received " + feedback.getIdUsuario() + " | "+feedback.getMensaje());
    		
    		if(emailService.sendEmail(feedback)) {
    			logger.info("Correo enviado");
    		}else {
    			logger.error("No se pudo enviar el correo");
    		}
    	}catch(Exception e) {
    		logger.info("Error al leer json", e);
    	}
    }
    
    public void sendDataJMS(FeedBack feedBack) throws IOException {
    	jmsTemplate.convertAndSend("sqs-sns-miColaNormal-1Y09A84XCJATB", mapper.writeValueAsString(feedBack));
    }
}
