package godinner.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import godinner.app.model.StatusPedido;

public interface StatusPedidoRepository extends JpaRepository<StatusPedido, Long>{
	@Query(value = "Select * from tbl_status_pedido where id_status_pedido = ?1",nativeQuery = true)
	public StatusPedido getStatusPedidoById(int id);
	
	@Query(value = "SELECT sp FROM StatusPedido sp WHERE sp.id = 2 OR sp.id = 3")
	public List<StatusPedido> getStatusPedidoRestaurante();
}
