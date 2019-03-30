package aws.mitocode.spring.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import aws.mitocode.spring.model.Sede;

@Repository
public interface ISedeDao extends JpaRepository<Sede, Integer>{

	@Query("SELECT s FROM Sede s WHERE (s.latitud BETWEEN :latitudMenor AND :latitudMayor) AND "
			+ "(s.longitud BETWEEN :longitudMenor AND :longitudMayor) AND "
			+ "s.negocio.id = :tipoNegocio")
	public List<Sede> obtenerSedesPorRadioCoordenadas(
			@Param("latitudMenor") double latitudMenor,
			@Param("latitudMayor") double latitudMayor,
			@Param("longitudMenor") double longitudMenor,
			@Param("longitudMayor") double longitudMayor,
			@Param("tipoNegocio") int tipoNegocio);
}
