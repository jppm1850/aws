package aws.mitocode.spring.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import aws.mitocode.spring.model.TipoNegocio;

public interface ITipoNegocioService {

	List<TipoNegocio> obtenerTodos();
	
	Page<TipoNegocio> obtenerDatosPaginados(Pageable pageable);
	void guardarDatos(TipoNegocio tipoNegocio);
	void eliminarDatos(int id);
}
