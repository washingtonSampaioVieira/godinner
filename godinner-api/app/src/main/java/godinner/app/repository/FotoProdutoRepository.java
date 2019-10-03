package godinner.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import godinner.app.model.FotoProduto;

public interface FotoProdutoRepository extends JpaRepository<FotoProduto, Long> {

	@Query("SELECT fp FROM FotoProduto fp WHERE fp.produto.id = ?1")
	List<FotoProduto> findByIdProduto(int id);
}
