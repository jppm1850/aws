package aws.mitocode.spring.service;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;

import aws.mitocode.spring.model.FeedBack;

public interface IFeedBackService {

	Page<FeedBack> obtenerDatosPaginados(Pageable pageable, String usuario, Collection<GrantedAuthority> ltaRoles);
	void guardarDatos(FeedBack feedback);
	void eliminarDatos(int id);
}
