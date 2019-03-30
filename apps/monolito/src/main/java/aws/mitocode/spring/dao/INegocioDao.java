package aws.mitocode.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aws.mitocode.spring.model.Negocio;

@Repository
public interface INegocioDao extends JpaRepository<Negocio, Integer> {

}
