package aws.mitocode.spring.service;

import aws.mitocode.spring.model.FeedBack;

public interface IEmailService {
	
	public boolean sendEmail(FeedBack feedback);
}
