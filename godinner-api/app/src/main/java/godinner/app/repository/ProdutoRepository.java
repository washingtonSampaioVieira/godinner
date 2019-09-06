package godinner.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import godinner.app.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	@Query("SELECT p FROM Produto p WHERE p.id = ?1")
	List<Produto> getProdutosById(int id);
	
	@Query("SELECT p FROM Produto p WHERE p.restaurante.id = ?1")
	List<Produto> getProdutosByIdRestaurante(int id);

}
