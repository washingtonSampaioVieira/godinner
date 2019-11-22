package godinner.app.resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.SupportedOptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import godinner.app.config.JwtTokenUtill;
import godinner.app.helper.AES;
import godinner.app.helper.Date;
import godinner.app.helper.Template;
import godinner.app.helper.ValidaCadastro;
import godinner.app.model.Cidade;
import godinner.app.model.Consumidor;
import godinner.app.model.Endereco;
import godinner.app.model.Produto;
import godinner.app.model.Restaurante;
import godinner.app.model.RestauranteArrecadacaoDTO;
import godinner.app.model.RestauranteExibicao;
import godinner.app.model.RetornoFloat;
import godinner.app.model.RetornoInt;
import godinner.app.model.RetornoFloat;
import godinner.app.repository.CidadeRepository;
import godinner.app.repository.ConsumidorRepository;
import godinner.app.repository.EnderecoRepository;
import godinner.app.repository.RestauranteArrecadacaoDTORepository;
import godinner.app.repository.RestauranteRepository;
import godinner.app.storage.Disco;

@RestController
@RequestMapping("/restaurante")
@CrossOrigin(origins = "http://localhost:3000")
@SupportedOptions(value = { "eventBusIndex", "verbose" })
public class RestauranteResource {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private RestauranteArrecadacaoDTORepository restauranteArrecadacaoDTORepository;


	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private ConsumidorRepository consumidorRepository;

	@Autowired
	private JwtTokenUtill jwtTokenUtil;
	
	@Value("aes.secret.key")
	private String secret;

	@GetMapping
	public List<Restaurante> getRestaurantes() {
		return restauranteRepository.findAll();
	}

	@PostMapping
	public Restaurante setRestaurante(@Validated @RequestBody Restaurante restaurante) {
		Endereco endereco = restaurante.getEndereco();
		Endereco enderecoSalvo = enderecoRepository.save(endereco);

		Cidade c = cidadeRepository.getCidade(enderecoSalvo.getCidade().getId());

		enderecoSalvo.setCidade(c);
		restaurante.setEndereco(enderecoSalvo);

		Restaurante restauranteSalvo = restauranteRepository.save(restaurante);

		return restauranteSalvo;
	}

	@GetMapping("/valida/cnpj/{cnpj}")
	public boolean validarCnpj(@PathVariable String cnpj) {
		cnpj = cnpj.replace("@", "/");
		if (cnpj.matches("[0-9]{2}\\.?[0-9]{3}\\.?[0-9]{3}\\/?[0-9]{4}\\-?[0-9]{2}")) {
			ValidaCadastro validaCadastro = new ValidaCadastro();
			if (validaCadastro.isCNPJ(cnpj)) {
				return restauranteRepository.validarCnpjUnico(cnpj) == 0 ? true : false;
			} else {
				return false;
			}

		} else {
			return false;
		}

	}

	@GetMapping("/valida/email/{email}")
	public boolean validarEmail(@PathVariable String email) {
		if (email.matches("^[a-z0-9.]+@[a-z0-9]+\\.[a-z]+(\\.[a-z]+)?")) {

			return restauranteRepository.validarEmailUnico(email) == 0 ? true : false;
		} else {
			return false;
		}

	}

	private List<RestauranteExibicao> castListRestauranteExibicao(List<Restaurante> rs) {
		List<RestauranteExibicao> es = new ArrayList<>();

		for (int i = 0; i < rs.size(); i++) {

			RestauranteExibicao e = new RestauranteExibicao();

			e.setId(rs.get(i).getId());
			e.setCnpj(rs.get(i).getCnpj());
			e.setEmail(rs.get(i).getEmail());
			e.setEndereco(rs.get(i).getEndereco());
			e.setFoto(rs.get(i).getFoto());
			e.setRazaoSocial(rs.get(i).getRazaoSocial());
			e.setTelefone(rs.get(i).getTelefone());

			es.add(e);

		}

		return es;
	}

	@GetMapping("/destaque/{id}")
	public List<RestauranteExibicao> getRestaurantesExibicaoDestaque(@PathVariable int id) {
		Consumidor c = consumidorRepository.getPorId(id);
		if (c == null)
			return null;

		List<Restaurante> r = restauranteRepository.getRestauranteDestaque(c.getEndereco().getCidade().getEstado().getEstado());
		List<RestauranteExibicao> e = castListRestauranteExibicao(r);
		e = setDadosExibicao(e, c);
		return e;
	}

	@GetMapping("/exibicao/{id}")
	public List<RestauranteExibicao> getRestaurantesExibicao(@PathVariable int id) {
		Consumidor c = consumidorRepository.getPorId(id);
		if (c == null)
			return null;

		List<Restaurante> r = restauranteRepository.getRestauranteExibicao(c.getEndereco().getCidade().getEstado().getEstado());
		List<RestauranteExibicao> e = castListRestauranteExibicao(r);
		e = setDadosExibicao(e, c);
		return e;
	}

	private List<RestauranteExibicao> setDadosExibicao(List<RestauranteExibicao> restaurantes, Consumidor c) {
		String origin = c.getEndereco().getCep().replace("-", "");
		for (int i = 0; i < restaurantes.size(); i++) {
			ArrayList<String> dados = new ArrayList<>();

			String destino = restaurantes.get(i).getEndereco().getCep().replace("-", "");

			dados = this.buscarDistanciaTempoGoogle(origin, destino);
			
//			dados.add("9 Km");
//			dados.add("15 mins");

			restaurantes.get(i).setDistancia(dados.get(0).replace("\"", ""));
			restaurantes.get(i).setTempoEntrega(dados.get(1).replace("\"", ""));
			restaurantes.get(i).setValorEntrega(5.50);
			restaurantes.get(i).setNota(5.0);

		}
		return restaurantes;
	}


	private ArrayList<String> buscarDistanciaTempoGoogle(String origin, String destino) {
		URL url;
		String urlString = "https://maps.googleapis.com/maps/api/directions/json?origin=" + origin + ""
				+ "&destination=" + destino + "&key=AIzaSyCVVT9Dl4bQDouAtP_PBniF2qtY8hL9CHE";

		ArrayList<String> retorno = new ArrayList<String>();
		retorno.add("9 Km");
		retorno.add("15 mins");

		try {
			url = new URL(urlString);

			HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
			conexao.setRequestProperty("Accept", "application/json");
			conexao.setRequestMethod("GET");
			conexao.setDoInput(true);
			conexao.connect();

			// Vendo o status da requisição
			if (conexao.getResponseCode() != 200)
				return retorno;

			InputStream inputStream = conexao.getInputStream();
			conexao.hashCode();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String linha = "";
			String dados = "";

			while (linha != null) {

				dados = dados + linha;
				linha = bufferedReader.readLine();
			}
			
			//tratamento caso nao haja rota
			JsonObject jsonStatus = new JsonParser().parse(dados).getAsJsonObject();
			String status = jsonStatus.get("status").toString().replace("\"", "");
			if(!status.equals("OK")) {
				return retorno;
			}
				
			
			
			
			JsonObject json = new JsonParser().parse(dados).getAsJsonObject();
			JsonObject primeiraFicha = json.get("routes").getAsJsonArray().get(0).getAsJsonObject();

			JsonArray legs = primeiraFicha.get("legs").getAsJsonArray();
			
			
			
			JsonObject legsJson = new JsonParser().parse(legs.get(0).toString()).getAsJsonObject();

			JsonObject durationText = legsJson.get("duration").getAsJsonObject();
			JsonObject distanceText = legsJson.get("distance").getAsJsonObject();

			retorno.set(0, distanceText.get("text").toString());
			retorno.set(1, durationText.get("text").toString());

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return retorno;
	}

	@GetMapping("/este")
	public Restaurante getRestauranteByToken(@RequestHeader String token) {
		AES aes = new AES(this.secret);
		token = aes.decrypt(token);
		String email = jwtTokenUtil.getUsernameFromToken(token);
		Restaurante restauranteLogado = restauranteRepository.getRestauranteByEmail(email);
		return restauranteLogado;
	}

	@GetMapping("/{id}")
	public Restaurante getRestaurante(@PathVariable int id) {
		Restaurante restaurante = restauranteRepository.getPorId(id);
		Date data = new Date();
		return restaurante;
	}

	@GetMapping("/templates/{id}")
	public Restaurante getRestauranteTemplate(@PathVariable int id) {
		Restaurante restaurante = restauranteRepository.getPorId(id);
		return restaurante;
	}
	
	@GetMapping("/categoria/{id}")
	public List<Restaurante> getRestaurantePorCategoria(@PathVariable int id) {
		return restauranteRepository.getRestauranteFromCategoriaMaiorQue4(id);
	}
	
	@GetMapping("/cadastrados")
	public RetornoInt getTotalRestaurantesCadastrados() {
		int totalRestaurante = restauranteRepository.getTotalRestaurante();
		RetornoInt retornoInt =  new RetornoInt(totalRestaurante);
		return retornoInt;
	}
	
	
	@GetMapping("/saldorestaurante/{id}")
	public RetornoFloat getSaldoRestaurante(@PathVariable float id) {
		float saldoRestaurante = restauranteRepository.getSaldoRestaurante(id);
		RetornoFloat retornoFloat = new RetornoFloat(saldoRestaurante);
		return retornoFloat;
	}
	
	
	
	
	
	@GetMapping("/desativo")
	public List<Restaurante> getRestaurantesDesativados(){
		
		List<Restaurante> r = restauranteRepository.getRestaurantesDesativados();
		int total = r.size();
		
		for (int i = 0; i < total; i++) {
			r.get(i).setSenha(null);
		}
		return  r;
	}
	
		
	@GetMapping("/debito/{idRestaurante}")
	public RetornoFloat getDebitoRestaurante(@PathVariable int idRestaurante) {
		
		return new RetornoFloat(restauranteRepository.getDebitoRestaurante(idRestaurante));
	}

	
	@GetMapping("/ativo")
	public List<Restaurante> getRestaurantesAtivos(){
		
		List<Restaurante> r = restauranteRepository.getRestaurantesAtivos();
		int total = r.size();
		
		for (int i = 0; i < total; i++) {
			r.get(i).setSenha(null);	
		}
	
		return  r;
	}
	
	@GetMapping("/arrecadacao")
	public List<RestauranteArrecadacaoDTO> getArrecadacaoRestaurante(){
		return restauranteArrecadacaoDTORepository.getArrecadacaoRestaurante();
	}
	
}
