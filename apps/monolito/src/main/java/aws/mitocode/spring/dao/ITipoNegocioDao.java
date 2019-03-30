package aws.mitocode.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import aws.mitocode.spring.model.TipoNegocio;

@Repository
public interface ITipoNegocioDao extends JpaRepository<TipoNegocio, Integer> {

}
