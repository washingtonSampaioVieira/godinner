package godinner.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import godinner.app.model.Restaurante;




public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
	
	
	@Query("SELECT COUNT(r.id) FROM Restaurante r WHERE r.email = ?1")
	public int validarEmailUnico(String email);
	
	
	
	@Query("SELECT COUNT(r.id) FROM Restaurante r WHERE r.cnpj = ?1 ")
	public int validarCnpjUnico(String cnpj);


	@Query("SELECT r FROM Restaurante r WHERE r.id = ?1 ")
	public Restaurante getPorId(int id);

}
