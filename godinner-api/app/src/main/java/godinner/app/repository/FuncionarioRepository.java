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

	
	
	@Query(value = "SELECT (SELECT COUNT(tbl_restaurante.cnpj) AS Total FROM tbl_restaurante WHERE tbl_restaurante.criacao LIKE '2019-01-%%' AND tbl_restaurante.status = 1 GROUP BY MONTH(tbl_restaurante.criacao)) AS janeiro,(SELECT COUNT(tbl_restaurante.cnpj) AS Total FROM tbl_restaurante WHERE tbl_restaurante.criacao LIKE '2019-02-%%' AND tbl_restaurante.status = 1 GROUP BY MONTH(tbl_restaurante.criacao) ) as fevereiro,(SELECT COUNT(tbl_restaurante.cnpj) AS Total FROM tbl_restaurante WHERE tbl_restaurante.criacao LIKE '2019-03-%%' AND tbl_restaurante.status = 1 GROUP BY MONTH(tbl_restaurante.criacao) ) as marco,(SELECT COUNT(tbl_restaurante.cnpj) AS Total FROM tbl_restaurante WHERE tbl_restaurante.criacao LIKE '2019-04-%%' AND tbl_restaurante.status = 1 GROUP BY MONTH(tbl_restaurante.criacao) ) as abril,(SELECT COUNT(tbl_restaurante.cnpj) AS Total FROM tbl_restaurante WHERE tbl_restaurante.criacao LIKE '2019-05-%%' AND tbl_restaurante.status = 1 GROUP BY MONTH(tbl_restaurante.criacao) ) as maio,(SELECT COUNT(tbl_restaurante.cnpj) AS Total FROM tbl_restaurante WHERE tbl_restaurante.criacao LIKE '2019-06-%%' AND tbl_restaurante.status = 1 GROUP BY MONTH(tbl_restaurante.criacao) ) as junho,(SELECT COUNT(tbl_restaurante.cnpj) AS Total FROM tbl_restaurante WHERE tbl_restaurante.criacao LIKE '2019-07-%%' AND tbl_restaurante.status = 1 GROUP BY MONTH(tbl_restaurante.criacao) ) as julho,(SELECT COUNT(tbl_restaurante.cnpj) AS Total FROM tbl_restaurante WHERE tbl_restaurante.criacao LIKE '2019-08-%%' AND tbl_restaurante.status = 1 GROUP BY MONTH(tbl_restaurante.criacao) ) as agosto,(SELECT COUNT(tbl_restaurante.cnpj) AS Total FROM tbl_restaurante WHERE tbl_restaurante.criacao LIKE '2019-09-%%' AND tbl_restaurante.status = 1 GROUP BY MONTH(tbl_restaurante.criacao) ) as setembro,(SELECT COUNT(tbl_restaurante.cnpj) AS Total FROM tbl_restaurante WHERE tbl_restaurante.criacao LIKE '2019-10-%%' AND tbl_restaurante.status = 1 GROUP BY MONTH(tbl_restaurante.criacao) ) as outubro,(SELECT COUNT(tbl_restaurante.cnpj) AS Total FROM tbl_restaurante WHERE tbl_restaurante.criacao LIKE '2019-11-%%' AND tbl_restaurante.status = 1 GROUP BY MONTH(tbl_restaurante.criacao) ) as novembro,(SELECT COUNT(tbl_restaurante.cnpj) AS Total FROM tbl_restaurante WHERE tbl_restaurante.criacao LIKE '2019-12-%%' AND tbl_restaurante.status = 1 GROUP BY MONTH(tbl_restaurante.criacao) ) as dezembro", nativeQuery = true)
	public String getRestauranteCadastradoPorMes();

}
