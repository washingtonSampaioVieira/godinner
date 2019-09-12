package godinner.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import godinner.app.model.Restaurante;
import godinner.app.model.RestauranteExibicao;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

	@Query("SELECT COUNT(r.id) FROM Restaurante r WHERE r.email = ?1")
	public int validarEmailUnico(String email);

	@Query("SELECT COUNT(r.id) FROM Restaurante r WHERE r.cnpj = ?1 ")
	public int validarCnpjUnico(String cnpj);

	@Query("SELECT r FROM Restaurante r WHERE r.id = ?1 ")
	public Restaurante getPorId(int id);

	@Query("SELECT r FROM Restaurante r WHERE r.email = ?1")
	public Restaurante getRestauranteByEmail(String email);

	@Query("SELECT r FROM Restaurante r WHERE r.email = ?1 and r.senha = ?2")
	public Restaurante getRestauranteByEmailAndPass(String email, String senha);

	@Query(value = "SELECT r.* FROM tbl_restaurante AS r where id_endereco = ?1 limit 12;", nativeQuery = true)
	public List<Restaurante> getRestauranteExibicao(int idEndereco);

}
