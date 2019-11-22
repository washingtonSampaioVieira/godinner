package godinner.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import godinner.app.model.Pedido;
import godinner.app.model.Restaurante;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	@Query("SELECT p FROM Pedido p WHERE p.id = ?1")
	public Pedido getPedidoById(int id);

	@Query(value = "SELECT * FROM tbl_pedido as p where p.id_restaurante = ?1 and id_status_pedido = 1 ", nativeQuery = true)
	public List<Pedido> getPedidosEmAbertoDoRestaurante(int id);

	@Query(value = "SELECT * FROM tbl_pedido as p where p.id_restaurante = ?1 and id_status_pedido = 2 ", nativeQuery = true)
	public List<Pedido> getPedidosEmProducaoPorRestaurante(int id);

	@Query(value = "SELECT * FROM tbl_pedido as p where p.id_restaurante = ?1 and id_status_pedido = 3 ", nativeQuery = true)
	public List<Pedido> getPedidosEmEntregaPorRestaurante(int id);

	@Query(value = "SELECT * FROM tbl_pedido as p where p.id_restaurante = ?1 and id_status_pedido = 4 ", nativeQuery = true)
	public List<Pedido> getPedidosFinalizadoPorRestaurante(int id);
	
	@Query(value = "select sum(p.valor_total*9/100) as Total_Comissao from tbl_pedido as p where p.comissao_paga = 1;", nativeQuery = true)
	public float getTotalComissaoPaga();
	
	@Query(value="SELECT count(*) as Pedido from tbl_pedido as p where p.id_restaurante = ?1 and p.id_status_pedido = 4", nativeQuery = true)
	public int getTotalPedidoPorRestaurante(int id);
	
	@Query(value="select sum(p.valor_total) as resultado, p.id_restaurante from tbl_pedido as p where p.id_status_pedido = 4 group by p.id_restaurante", nativeQuery = true)
	public List<Pedido> getValorVendasRestaurantes();
	
	
	@Query(value = "SELECT * FROM tbl_pedido as p WHERE p.data_do_pedido <= NOW() AND p.id_restaurante = ?1", nativeQuery = true)
	public List<Pedido> setPedidosDebito(Integer id);
	
	
	
	@Query(value="select count(p.valor_total) as resultado, p.id_restaurante from tbl_pedido as p where p.id_status_pedido = 4 group by p.id_restaurante", nativeQuery = true)
	public List<Pedido> getTotalPedidoFeitoRestaurante();
	
}
