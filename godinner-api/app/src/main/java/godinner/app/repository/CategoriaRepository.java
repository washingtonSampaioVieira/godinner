package godinner.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import godinner.app.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	@Query(value = "select * from tbl_categoria order by rand() limit 7;",nativeQuery = true)
	List<Categoria> buscar10Aleatorios();
}
