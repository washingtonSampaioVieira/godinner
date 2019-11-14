package godinner.app.resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import godinner.app.config.JwtTokenUtill;
import godinner.app.helper.AES;
import godinner.app.model.Consumidor;
import godinner.app.model.Pedido;
import godinner.app.model.Produto;
import godinner.app.model.ProdutoPedido;
import godinner.app.model.Restaurante;
import godinner.app.model.RetornoFloat;
import godinner.app.model.RetornoFloat;
import godinner.app.model.RetornoInt;
import godinner.app.model.StatusPedido;
import godinner.app.repository.ConsumidorRepository;
import godinner.app.repository.PedidoRepository;
import godinner.app.repository.ProdutoPedidoRepository;
import godinner.app.repository.ProdutoRepository;
import godinner.app.repository.RestauranteRepository;
import godinner.app.repository.StatusPedidoRepository;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "http://localhost")
@Controller
public class PedidoResource {
	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	ProdutoPedidoRepository produtoPedidoRepository;

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	StatusPedidoRepository statusPedidoRepository;

	@Autowired
	JwtTokenUtill jwtTokenUtil;

	@Autowired
	ConsumidorRepository consumidorRepository;

	@Autowired
	RestauranteRepository restauranteRepository;
	
	@Value("aes.secret.key")
	private String secret;

	@GetMapping
	public List<Pedido> getPedidos() {
		return pedidoRepository.findAll();
	}

	@PostMapping
	public Pedido setPedido(@RequestBody Pedido pedido) {

		// produto aguardando confirmação
		StatusPedido statusPedido = new StatusPedido(1);
		pedido.setStatusPedido(statusPedido);

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

		return pedidoSalvo;
	}

	@GetMapping("/abertos/{id}")
	public List<Pedido> getPedidosEmAberto(@PathVariable int id) {
		List<Pedido> pedidos = pedidoRepository.getPedidosEmAbertoDoRestaurante(id);

		return atualizarStatusPedidos(pedidos, 2);
	}

	@PutMapping("/recebido/{id}")
	public void setPedidoRecebido(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
		Pedido pedido = pedidoRepository.getPedidoById(id);
		String token = request.getHeader("token");
		
		AES aes = new AES(this.secret);
		token = aes.decrypt(token);
		String email = jwtTokenUtil.getUsernameFromToken(token);
		Consumidor consumidor = consumidorRepository.getConsumidorByEmail(email);

		if (consumidor == null) {
			try {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Não autorizado");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		if (consumidor.getId() == pedido.getConsumidor().getId()) {
			StatusPedido statusPedido = new StatusPedido(4);
			pedido.setStatusPedido(statusPedido);

			pedidoRepository.save(pedido);
			try {
				response.sendError(HttpServletResponse.SC_OK);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Não autorizado.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return;
	}

	@PutMapping("/entregue/{id}")
	public void setPedidoEntregue(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
		Pedido pedido = pedidoRepository.getPedidoById(id);

		String token = request.getHeader("token");
		
		AES aes = new AES(this.secret);
		token = aes.decrypt(token);
		String email = jwtTokenUtil.getUsernameFromToken(token);
		System.out.println(email);
		Restaurante restaurante = restauranteRepository.getRestauranteByEmail(email);
		if (restaurante == null) {
			try {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Não autorizado.");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		if (restaurante.getId() == pedido.getRestaurante().getId()) {
			StatusPedido statusPedido = new StatusPedido(3);
			pedido.setStatusPedido(statusPedido);

			pedidoRepository.save(pedido);
			try {
				response.sendError(HttpServletResponse.SC_OK);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Não autorizado.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return;
	}

	@GetMapping("/producoes/{id}")
	public List<Pedido> getPedidosEmProducao(@PathVariable int id) {
		return pedidoRepository.getPedidosEmProducaoPorRestaurante(id);
	}

	@GetMapping("/entregas/{id}")
	public List<Pedido> getPedidosEmEntrega(@PathVariable int id) {
		return pedidoRepository.getPedidosEmEntregaPorRestaurante(id);
	}

	@GetMapping("/finalizados/{id}")
	public List<Pedido> getPedidosFinalizados(@PathVariable int id) {
		return pedidoRepository.getPedidosFinalizadoPorRestaurante(id);
	}

	private List<Pedido> atualizarStatusPedidos(List<Pedido> pedidos, int idStatus) {
		int tamanho = pedidos.size();

		StatusPedido statusPedido = statusPedidoRepository.getStatusPedidoById(idStatus);
		for (int i = 0; i < tamanho; i++) {
			pedidos.get(i).setStatusPedido(statusPedido);
		}

		return pedidoRepository.saveAll(pedidos);
	}
	
	
	@GetMapping("/totalcomissao")
	public RetornoFloat getTotalComissaoPaga() {
		float totalComissao = pedidoRepository.getTotalComissaoPaga();
		RetornoFloat retornoFloat =  new RetornoFloat(totalComissao);
		return retornoFloat;
	}
	
	
	@GetMapping("/pedidorestaurante/{id}")
	public RetornoInt getTotalPedidoPorRestaurante(@PathVariable int id) {
		int totalPedidoRestaurante = pedidoRepository.getTotalPedidoPorRestaurante(id);
		RetornoInt retornoInt = new RetornoInt(totalPedidoRestaurante);
		return retornoInt;
	}
	
	

	@PutMapping("/debito/{idRestaurante}")
	public List<Pedido> setPedidosDebito(@PathVariable int idRestaurante, HttpServletResponse response) throws IOException {
		List<Pedido> p = restauranteRepository.setPedidosDebito(idRestaurante);
		
		int tamanho = p.size();
		
		for (int i = 0; i < tamanho; i++) {
			p.get(i).setComissaoPaga(1);
		}
		
		pedidoRepository.saveAll(p);
			
		return p;
	}
}
	
	
	
	
	
