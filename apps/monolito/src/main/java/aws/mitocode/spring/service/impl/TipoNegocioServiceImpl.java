package aws.mitocode.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import aws.mitocode.spring.dao.ITipoNegocioDao;
import aws.mitocode.spring.model.TipoNegocio;
import aws.mitocode.spring.service.ITipoNegocioService;

@Service
public class TipoNegocioServiceImpl implements ITipoNegocioService {

	@Autowired
	private ITipoNegocioDao tipoNegocioDao;
	
	@Override
	public List<TipoNegocio> obtenerTodos() {
		return this.tipoNegocioDao.findAll();
	}

	@Override
	public Page<TipoNegocio> obtenerDatosPaginados(Pageable pageable) {
		return this.tipoNegocioDao.findAll(pageable);
	}

	@Override
	public void guardarDatos(TipoNegocio tipoNegocio) {
		this.tipoNegocioDao.save(tipoNegocio);
		
	}

	@Override
	public void eliminarDatos(int id) {
		this.tipoNegocioDao.delete(id);
		
	}

}