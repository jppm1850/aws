package aws.mitocode.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import aws.mitocode.spring.dao.INegocioDao;
import aws.mitocode.spring.model.Negocio;
import aws.mitocode.spring.service.INegocioService;

@Service
public class NegocioServiceImpl implements INegocioService{

	@Autowired
	private INegocioDao negocioDao;
	
	@Override
	public Page<Negocio> obtenerDatosPaginados(Pageable pageable) {
		return this.negocioDao.findAll(pageable);
	}

	@Override
	public void guardarDatos(Negocio negocio) {
		this.negocioDao.save(negocio);
	}

	@Override
	public void eliminarDatos(int id) {
		this.negocioDao.delete(id);		
	}

	@Override
	public List<Negocio> obtenerTodos() {
		return this.negocioDao.findAll();
	}

}
