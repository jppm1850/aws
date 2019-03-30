package aws.mitocode.spring.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.fasterxml.jackson.databind.ObjectMapper;

import aws.mitocode.spring.model.FeedBack;
import aws.mitocode.spring.service.INotificacionSNS;

@Service
public class NotificacionSNSServiceImpl implements INotificacionSNS {
	
	private Logger logger = Logger.getLogger(NotificacionSNSServiceImpl.class);

	/**
	* Colocar la ARN que identifica a su SNS, es importante que este demo está trabajando con la region us-east-1 (virginia)
	* Cambiar el numero 123456789012 por su numero de cuenta AWS
	* procesarFeedBack es el nombre del topico de SNS, puede cambiarse por el que UD configura
	*/
	private static final String ARN_TOPICO_PROCESA_FEEDBACK = "arn:aws:sns:us-east-1:281263139355:miSNSCurso";

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private AmazonSNSClient servicioSNS;

	public void enviarNotificacionSubscriptores(FeedBack feedback) {
		try {
			PublishRequest publishRequest = new PublishRequest(ARN_TOPICO_PROCESA_FEEDBACK,
					mapper.writeValueAsString(feedback));
			
			PublishResult publishResult = servicioSNS.publish(publishRequest);
			
			logger.info("MessageId - " + publishResult.getMessageId());
		} catch (Exception e) {
			logger.error("Error al enviar mensaje a SNS");
		}
	}
}
