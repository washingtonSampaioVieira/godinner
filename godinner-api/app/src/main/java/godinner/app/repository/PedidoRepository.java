package godinner.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import godinner.app.model.Pedido;

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
}
