package godinner.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import godinner.app.model.Categoria;
import godinner.app.model.CategoriaProduto;
import godinner.app.model.Produto;

public interface CategoriaProdutoRepository extends JpaRepository<CategoriaProduto, Long>{

	@Query("SELECT c FROM Categoria c where c.id =?1")
	Categoria getById(Integer id);
	
	// select de todos as categorias cadastradas em um produto
	@Query(value = "SELECT c FROM CategoriaProduto c where c.produto.id = ?1")
	List<CategoriaProduto> todosDoRestaurante(int id);
	
	@Query("SELECT c FROM CategoriaProduto c where c.id =?1")
	CategoriaProduto getIdCategoriaProduto(int id);
	
	//SELECIONA TODAS AS CATEGORIAS QUE O RESTAURANTE TEM PRODUTOS CADASTRADOS
	@Query("SELECT c FROM Categoria c"
			+ " INNER JOIN CategoriaProduto cp"
			+ " ON cp.categoria.id = c.id"
			+ " INNER JOIN Produto p"
			+ " ON p.id = cp.produto.id"
			+ " INNER JOIN Restaurante r"
			+ " ON r.id = p.restaurante.id"			
			+ " WHERE r.id = ?1 GROUP BY c.id")
	List<Categoria> getCategoriaRestaurante(int idRestaurante);
	
	//SELECIONA TODAS OS PRODUTOS PELA CATEGORIA E RESTAURANTE 
	@Query(value = "SELECT DISTINCT p FROM Produto AS p "
			+ " INNER JOIN CategoriaProduto AS cp"
			+ " ON p.id = cp.produto.id"
			+ " INNER JOIN Restaurante AS r "
			+ " ON r.id = p.restaurante.id"
			+ " WHERE r.id = ?1"
			+ " AND cp.categoria.id = ?2")
	List<Produto> getProdutosPorCategeoria(int idRestaurante, int idCategoria);
	
}
