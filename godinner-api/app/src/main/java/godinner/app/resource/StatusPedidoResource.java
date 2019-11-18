package godinner.app.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import godinner.app.model.StatusPedido;
import godinner.app.repository.StatusPedidoRepository;

@RestController
@RequestMapping("/statuspedido")
@CrossOrigin(origins = "http://localhost:3000")
public class StatusPedidoResource {
	
	@Autowired
	private StatusPedidoRepository statusPedidoRepository;
	
	@GetMapping("/restaurantes")
	public List<StatusPedido> getStatusPedidoRestaurante() {
		return statusPedidoRepository.getStatusPedidoRestaurante();
	}

}
