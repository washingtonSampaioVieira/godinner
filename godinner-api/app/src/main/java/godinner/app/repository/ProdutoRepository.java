package godinner.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import godinner.app.model.Produto;
import godinner.app.model.ProdutoExibicao;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	@Query("SELECT p FROM Produto p WHERE p.id = ?1")
	Produto getProdutosById(int id);
	
	@Query("SELECT p FROM Produto p WHERE p.restaurante.id = ?1")
	List<Produto> getProdutosByIdRestaurante(int id);
	
//	SELECT p.nome, (SELECT fp.foto FROM tbl_foto_produto AS fp WHERE fp.id_produto = p.id_produto AND fp.index_foto = '1' LIMIT 1) AS foto FROM tbl_produto AS p;
//	@Query(value = "SELECT p.nome, (SELECT fp.foto FROM FotoProduto fp WHERE fp.produto.id = p.id AND fp.indexFoto = '1' limit 1) AS foto FROM Produto p", nativeQuery = true)
	@Query(value = "SELECT p.*, (SELECT fp.foto FROM tbl_foto_produto AS fp WHERE fp.id_produto = p.id_produto AND fp.index_foto = '1' LIMIT 1) AS foto,  '1' AS distacia, '2' as tempo_entrega  FROM tbl_produto AS p limit 10;", nativeQuery = true)
	List<ProdutoExibicao> getProdutoExibicao();

	

}
