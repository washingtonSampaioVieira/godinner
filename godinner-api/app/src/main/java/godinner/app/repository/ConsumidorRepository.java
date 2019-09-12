package godinner.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import godinner.app.model.Consumidor;

public interface ConsumidorRepository extends JpaRepository<Consumidor, Long> {

	@Query("SELECT COUNT(c.id) FROM Consumidor c WHERE c.email = ?1")
	public int validarEmailUnico(String email);

	@Query("SELECT COUNT(c.id) FROM Consumidor c WHERE c.cpf = ?1 ")
	public int validarCpfUnico(String cpf);

	@Query("SELECT c FROM Consumidor c WHERE c.id = ?1 ")
	public Consumidor getPorId(int id);

	@Query("SELECT c FROM Consumidor c WHERE c.email = ?1 ")
	public Consumidor getConsumidorByEmail(String email);

	@Query("SELECT c FROM Consumidor c WHERE c.email = ?1 and c.senha = ?2")
	public Consumidor getConsumidorByEmailAndPass(String email, String password);

	@Query(value = "select * from  tbl_consumidor where id_consumidor = ?1 and id_endereco = ?2;", nativeQuery = true)
	public Consumidor getPorIdAndCidade(int idConsumidor, int idEndereco);
}
