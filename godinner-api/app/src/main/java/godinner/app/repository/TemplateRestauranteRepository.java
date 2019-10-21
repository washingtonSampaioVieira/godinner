package godinner.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import godinner.app.helper.Template;
import godinner.app.model.TemplateRestaurante;

public interface TemplateRestauranteRepository extends JpaRepository<TemplateRestaurante, Long> {
	
	

}
