package godinner.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import godinner.app.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	
	@Query("SELECT c from Cidade c WHERE c.id = ?1 ")
	public Cidade getCidade(Integer id_cidade);
	
	
	 
	@Query("SELECT c from Cidade c WHERE c.cidade LIKE  ?1 ")
	public Cidade getCidadePorCidade(String cidade);
}
