package godinner.app.resource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import godinner.app.model.Consumidor;
import godinner.app.model.FotoProduto;
import godinner.app.model.Produto;
import godinner.app.model.Restaurante;
import godinner.app.repository.ConsumidorRepository;
import godinner.app.repository.FotoProdutoRepository;
import godinner.app.repository.ProdutoRepository;
import godinner.app.repository.RestauranteRepository;
import godinner.app.storage.Disco;


@RestController
@RequestMapping("/foto")
@CrossOrigin(origins = "http://localhost:3000")
public class FotoResource {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private Disco disco;
	
	@Autowired
	private ConsumidorRepository consumidorRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private FotoProdutoRepository fotoProdutoRepository;
	
	@PostMapping("/restaurante")
	public Restaurante uploadRestaurante(@RequestParam MultipartFile foto, @RequestParam int id){
		Restaurante r = null;
		String localFoto = disco.salvarFoto(foto, "restaurante");
		if(localFoto != null) {
		  r = restauranteRepository.getPorId(id);
		  r.setFoto(localFoto);
		  restauranteRepository.save(r);
		}
		return r;
	}
	
	@PostMapping("/consumidor")
	public Consumidor uploadConsumidor(@RequestParam MultipartFile foto, @RequestParam int id){
		Consumidor c = null;
		
		String localFoto = disco.salvarFoto(foto, "consumidor");
		if(localFoto != null) {
			c = consumidorRepository.getPorId(id);
			c.setFotoPerfil(localFoto);
			consumidorRepository.save(c);
		}
		return c;
	}
	
	
	@PostMapping("/produto")
	public FotoProduto uploadProduto(@RequestParam MultipartFile foto, @RequestParam int id, @RequestParam int index, @RequestParam String legenda){
		
		Produto p = null;
		FotoProduto fp = new FotoProduto();
		System.out.println(foto.getOriginalFilename());
		
		String localFoto = disco.salvarFoto(foto, "restaurante/produto");
		if(localFoto != null) {
			p  = produtoRepository.getProdutosById(id);
			fp.setFoto(localFoto);
			fp.setIndexFoto(index);
			fp.setProduto(p);
			fp.setLegenda(legenda);
			fp = fotoProdutoRepository.save(fp);
			
			
		}
		
		return fp;
	}
	
	
	
	
}
