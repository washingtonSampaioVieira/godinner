package godinner.app.resource;

import java.util.List;

import javax.annotation.processing.SupportedOptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import godinner.app.helper.Template;
import godinner.app.model.Restaurante;
import godinner.app.repository.TemplateRestauranteRepository;

@RestController
@RequestMapping("/templates")
@CrossOrigin(origins = "http://localhost:3000")
public class TemplateRestauranteResource {
	
	@Autowired
	private TemplateRestauranteRepository templateRepository;
	
	
	@GetMapping
	public List<Template> getTemplates() {
		return templateRepository.findAll();
	}

}
