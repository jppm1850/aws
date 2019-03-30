package aws.mitocode.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import aws.mitocode.spring.dao.ISedeDao;
import aws.mitocode.spring.model.Sede;
import aws.mitocode.spring.service.ISedeService;

@Service
public class SedeServiceImpl implements ISedeService {

	@Autowired
	private ISedeDao sedeDao;
	
	@Override
	public List<Sede> obtenerSedesPorRadioLatitudLongitud(int tipoNegocio, double latitud, double longitud,
			double radio) {
		double latitudMenor = latitud - radio;
		double latitudMayor = latitud + radio;
		double longitudMenor = longitud - radio;
		double longitudMayor = longitud + radio;
		return sedeDao.obtenerSedesPorRadioCoordenadas(latitudMenor, latitudMayor, longitudMenor, longitudMayor, tipoNegocio);
	}
	
	@Override
	public Sede obtenerSedePorID(int id) {
		Sede sede = sedeDao.findOne(id);
		//sede.getTiposServicios().size();
		
		return sede;
	}

	@Override
	public Page<Sede> obtenerDatosPaginados(Pageable pageable) {
		return this.sedeDao.findAll(pageable);
	}

	@Override
	public void guardarDatos(Sede sede) {
		this.sedeDao.save(sede);
		
	}

	@Override
	public void eliminarDatos(int id) {
		this.sedeDao.delete(id);		
	}

}
