package godinner.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import godinner.app.model.RestauranteArrecadacaoDTO;

public interface RestauranteArrecadacaoDTORepository extends JpaRepository<RestauranteArrecadacaoDTO, Long> {
	
	@Query(value="select * from view_arrecadacao_restaurante", nativeQuery=true)
	public List<RestauranteArrecadacaoDTO> getArrecadacaoRestaurante();
	
	
}
