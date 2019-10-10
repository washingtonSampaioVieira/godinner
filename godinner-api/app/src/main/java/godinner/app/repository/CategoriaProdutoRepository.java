package godinner.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import godinner.app.model.Categoria;
import godinner.app.model.CategoriaProduto;

public interface CategoriaProdutoRepository extends JpaRepository<CategoriaProduto, Long>{

	@Query("SELECT c FROM Categoria c where c.id =?1")
	Categoria getById(Integer id);
	
	// select de todos as categorias cadastradas em um produto
	@Query(value = "SELECT c FROM CategoriaProduto c where c.produto.id = ?1")
	List<CategoriaProduto> todosDoRestaurante(int id);
	
	@Query("SELECT c FROM CategoriaProduto c where c.id =?1")
	CategoriaProduto getIdCategoriaProduto(int id);
}
