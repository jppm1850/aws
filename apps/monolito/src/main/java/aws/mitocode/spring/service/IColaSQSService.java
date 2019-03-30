package aws.mitocode.spring.service;

import java.io.IOException;

import aws.mitocode.spring.model.FeedBack;

public interface IColaSQSService {

	public void readJMS(String feedback);
	public void sendDataJMS(FeedBack feedBack) throws IOException;
}
