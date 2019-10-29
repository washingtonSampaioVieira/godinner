package godinner.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import godinner.app.model.StatusPedido;

public interface StatusPedidoRepository extends JpaRepository<StatusPedido, Long>{
	@Query(value = "Select * from tbl_status_pedido where id_status_pedido = ?1",nativeQuery = true)
	public StatusPedido getStatusPedidoById(int id);
}
