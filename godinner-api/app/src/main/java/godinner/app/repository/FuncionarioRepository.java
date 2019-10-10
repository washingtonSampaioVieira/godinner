package godinner.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import godinner.app.model.Consumidor;
import godinner.app.model.Funcionario;
import godinner.app.model.Restaurante;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	@Query("SELECT f FROM Funcionario f WHERE f.id = ?1 ")
	public Funcionario getPorId(int idFuncinario);

	@Query("SELECT f FROM Funcionario f WHERE f.email = ?1 and f.senha = ?2")
	public Funcionario getFuncionarioByEmailAndPass(String email, String password);
	
	@Query("SELECT f FROM Funcionario f WHERE f.email = ?1")
	public Funcionario getFuncionarioByEmail(String email);

}
