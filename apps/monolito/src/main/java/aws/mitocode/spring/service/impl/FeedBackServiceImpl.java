package aws.mitocode.spring.service.impl;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import aws.mitocode.spring.dao.IFeedBackDao;
import aws.mitocode.spring.model.FeedBack;
import aws.mitocode.spring.service.IFeedBackService;
import aws.mitocode.spring.service.INotificacionSNS;

@Service
public class FeedBackServiceImpl implements IFeedBackService {
	
	private Logger logger = Logger.getLogger(FeedBackServiceImpl.class);

	@Autowired
	private IFeedBackDao feedbackDao;
	
	@Autowired
	private INotificacionSNS notificacionSNS;
	
	@Override
	public Page<FeedBack> obtenerDatosPaginados(Pageable pageable, String usuario, Collection<GrantedAuthority> ltaRoles) {
		boolean isAdmin = false;
		if(ltaRoles != null && ltaRoles.size() > 0) {
			for(GrantedAuthority rol : ltaRoles) {
				if("ROLE_ADMIN".equalsIgnoreCase(rol.getAuthority())) {
					isAdmin = true;
					break;
				}
			}
		}
		if(isAdmin) {
			return feedbackDao.obtenerFeedBacks(pageable);
		}
		return feedbackDao.obtenerFeedBacksPorUsuario(pageable, usuario);
	}

	@Override
	public void guardarDatos(FeedBack feedback) {
		feedbackDao.save(feedback);
		try {
			notificacionSNS.enviarNotificacionSubscriptores(feedback);
		}catch(Exception e) {
			logger.info("Error al enviar datos a la cola");
		}
	}

	@Override
	public void eliminarDatos(int id) {
		feedbackDao.delete(id);
		
	}

}
