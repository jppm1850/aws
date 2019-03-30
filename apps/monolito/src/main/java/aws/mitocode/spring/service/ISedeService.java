package aws.mitocode.spring.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import aws.mitocode.spring.model.Sede;

public interface ISedeService {

	List<Sede> obtenerSedesPorRadioLatitudLongitud(
			int tipoNegocio,
			double latitud,
			double longitud,
			double radio);
	
	Sede obtenerSedePorID(int id);
	
	Page<Sede> obtenerDatosPaginados(Pageable pageable);
	void guardarDatos(Sede sede);
	void eliminarDatos(int id);
}
