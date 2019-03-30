package aws.mitocode.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aws.mitocode.spring.dao.IProblemaDao;
import aws.mitocode.spring.model.Problema;
import aws.mitocode.spring.service.IProblemaService;

@Service
public class ProblemaServiceImpl implements IProblemaService {

	@Autowired
	private IProblemaDao problemaDao;
	
	@Override
	public List<Problema> obtenerTodos() {
		return this.problemaDao.findAll();
	}

}
