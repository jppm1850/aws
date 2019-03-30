package aws.mitocode.spring.service;

import aws.mitocode.spring.model.FeedBack;

public interface INotificacionSNS {

	public void enviarNotificacionSubscriptores(FeedBack feedback);
}
