package aws.mitocode.spring.service;

import java.util.List;

import aws.mitocode.spring.model.Problema;

public interface IProblemaService {
	
	public List<Problema> obtenerTodos();
}
