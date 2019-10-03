package godinner.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import godinner.app.model.AvaliacaoUsuario;

public interface AvaliacaoUsuarioRepository   extends JpaRepository<AvaliacaoUsuario, Long>{
	
}
