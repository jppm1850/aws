package aws.mitocode.spring.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import aws.mitocode.spring.model.FeedBack;

@Repository
public interface IFeedBackDao extends JpaRepository<FeedBack, Integer> {

	@Query(value = "select f"
			+ " from FeedBack f"
			+ "	where f.idUsuario=:usuario",
			countQuery = "select count(f)"
					+ " from FeedBack f"
					+ "	where f.idUsuario=:usuario")
	Page<FeedBack> obtenerFeedBacksPorUsuario(Pageable pageable, @Param("usuario") String usuario);
	
	@Query(value = "select f from FeedBack f",
			countQuery = "select count(f) from FeedBack f")
	Page<FeedBack> obtenerFeedBacks(Pageable pageable);
}
