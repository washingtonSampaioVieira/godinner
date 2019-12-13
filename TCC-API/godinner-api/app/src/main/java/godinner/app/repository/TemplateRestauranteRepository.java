package godinner.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import godinner.app.helper.Template;
import godinner.app.model.Produto;
import godinner.app.model.TemplateRestaurante;

public interface TemplateRestauranteRepository extends JpaRepository<TemplateRestaurante, Long> {
	
	@Query(value ="SELECT * FROM tbl_informacoes_template as t WHERE t.id_restaurante = ?1 limit 1", nativeQuery=true)
	TemplateRestaurante getTemplate(int idRestaurante);
	
	@Query(value ="SELECT * FROM tbl_informacoes_template as t WHERE t.id_informacoes_template = ?1 limit 1", nativeQuery=true)
	TemplateRestaurante getTemplateById(int id);
	

}
