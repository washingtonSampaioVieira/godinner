package godinner.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import godinner.app.model.AvaliacaoUsuario;

public interface AvaliacaoUsuarioRepository   extends JpaRepository<AvaliacaoUsuario, Long>{
	
	
	@Query(value="select  distinct ((select SUM(nota) from tbl_avaliacao_usuario where id_restaurante=153)"
			+ "/(select count(distinct id_consumidor) from tbl_avaliacao_usuario where id_restaurante = 153 )) "
			+ "AS MEDIA from tbl_avaliacao_usuario as au  "
			+ "INNER JOIN tbl_restaurante as r ON r.id_restaurante = au.id_restaurante  "
			+ "where au.id_restaurante = 153",nativeQuery = true)
	public int getMediaAvaliacaoRestaurante(int id);
}
