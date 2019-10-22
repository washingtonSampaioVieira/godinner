package godinner.app.resource;

import java.util.ArrayList;
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
import godinner.app.repository.ProdutoRepository;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "http://localhost:3000")
public class PedidoResource {
	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	ProdutoPedidoRepository produtoPedidoRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;

	@GetMapping
	public List<Pedido> getPedidos() {
		return pedidoRepository.findAll();
	}

	@PostMapping
	public Pedido setPedido(@RequestBody Pedido pedido) {
		
		
		Pedido pedidoSalvo = pedidoRepository.save(pedido);
		int id = pedidoSalvo.getId();
		List<ProdutoPedido> p = new ArrayList<>();
		
		int total = pedido.getProdutos().size();
		for (int i = 0; i < total; i++) {
			ProdutoPedido pd = pedido.getProdutos().get(i);
			pd.setPedido(pedidoSalvo);
			p.add(pd);
		}
		
		List<ProdutoPedido> produtoPedidosSalvos = produtoPedidoRepository.saveAll(p);
		produtoPedidosSalvos = produtoPedidoRepository.getProdutosPedido(id);
		pedidoSalvo.setProdutos(produtoPedidosSalvos);
		
//		enviarNotificacaoRestaurante(id);
		
		
		return pedidoSalvo;

	}

}
