package godinner.app.resource;

import java.io.BufferedReader;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;

import godinner.app.model.Cidade;
import godinner.app.model.Endereco;
import godinner.app.model.EnderecoViaCep;
import godinner.app.repository.CidadeRepository;
import godinner.app.repository.EnderecoRepository;
import javassist.expr.NewArray;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/endereco")
public class EnderecoResource {
	@Autowired EnderecoRepository enderecoRepository;
	@Autowired CidadeRepository cidadeRepository;
	
	
	@GetMapping("/cep/{cep}")
	public Endereco montaEnderecoViaCep(@PathVariable  String cep){
		
		
		Endereco enderecoTratado = viaCep("https://viacep.com.br/ws/"+cep+"/json/");
		
		return enderecoTratado;
	}
	
	private Endereco viaCep(String urlString){
		
//		https://viacep.com.br/ws/06653430/json/
		URL url;
		EnderecoViaCep enderecoViaCep = null;
		Endereco endereco = new Endereco();
		try {
			url = new URL(urlString);
			
			HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
	        conexao.setRequestProperty("Accept", "application/json");
	        conexao.setRequestMethod("GET");
	        conexao.setDoInput(true);
	        conexao.connect();
	        
	        InputStream inputStream = conexao.getInputStream();
	        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String linha = "";
            String dados = "";

            while (linha != null){ 
                linha = bufferedReader.readLine();
                dados = dados + linha;
            }
	        ObjectMapper mapper = new ObjectMapper();
	        EnderecoViaCep viaCep = mapper.readValue(dados, EnderecoViaCep.class);
 	        endereco.setLogradouro(viaCep.logradouro);
 	        endereco.setBairro(viaCep.bairro);
 	        endereco.setCep(viaCep.cep);
 	        Cidade c = cidadeRepository.getCidadePorCidade(viaCep.localidade);
 	        endereco.setCidade(c);
 	        
 	        
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return endereco;
  }
}
