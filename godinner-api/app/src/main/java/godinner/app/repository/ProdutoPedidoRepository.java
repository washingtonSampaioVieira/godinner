package godinner.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import godinner.app.model.ProdutoPedido;

public interface ProdutoPedidoRepository extends JpaRepository<ProdutoPedido, Long> {

	
	@Query(value = "SELECT * FROM " + 
			"    tbl_produto_pedido AS pp " + 
			"        INNER JOIN " + 
			"    tbl_produto AS p ON p.id_produto = pp.id_produto " + 
			"WHERE " + 
			"    pp.id_pedido = 57;", nativeQuery= true)
	public List<ProdutoPedido> getProdutosPedido(int id);
}
