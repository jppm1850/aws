package aws.mitocode.spring.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import aws.mitocode.spring.model.Negocio;

public interface INegocioService {

	List<Negocio> obtenerTodos();
	Page<Negocio> obtenerDatosPaginados(Pageable pageable);
	void guardarDatos(Negocio negocio);
	void eliminarDatos(int id);
}
