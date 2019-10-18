package godinner.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import godinner.app.helper.Template;

public interface TemplateRestauranteRepository extends JpaRepository<Template, Long> {
	
	

}
