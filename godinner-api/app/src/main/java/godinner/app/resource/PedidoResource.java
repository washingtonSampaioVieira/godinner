package godinner.app.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import godinner.app.model.Pedido;
import godinner.app.model.ProdutoPedido;
import godinner.app.repository.PedidoRepository;
import godinner.app.repository.ProdutoPedidoRepository;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "http://localhost:3000")
public class PedidoResource {
	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	ProdutoPedidoRepository produtoPedidoRepository;

	@GetMapping
	public List<Pedido> getPedidos() {
		return pedidoRepository.findAll();
	}

	@PostMapping
	public Pedido setPedido(@RequestBody Pedido pedido){
		
		Pedido pedidoSalvo = pedidoRepository.save(pedido);
		Integer id = pedidoSalvo.getId();
		System.out.println(pedido.toString());
		List<ProdutoPedido> p = null ;
		
//		for (int i = 0; i < pedido.getProdutos().size(); i++) {
//			p.add(pedido.getProdutos().get(i));
//			p.get(i).getPedido().setId(id);			
//		}

		
		
		
		
		return pedidoRepository.getPedidoById(id);
		
		
		
		
	}

}
