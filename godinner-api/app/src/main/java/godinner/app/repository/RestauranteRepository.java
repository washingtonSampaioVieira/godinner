package godinner.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import godinner.app.model.Produto;
import godinner.app.model.Restaurante;

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
	
	@Query("SELECT r.id from Restaurante r WHERE r.id >= LAST_INSERT_ID()")
	public String getRestaurante();


	@Query(value = "SELECT *"
			+ "FROM" 
			+"    tbl_restaurante AS r" 
			+"        INNER JOIN" 
			+"    tbl_endereco AS e ON e.id_endereco = r.id_endereco" 
			+"        INNER JOIN" 
			+"    tbl_cidade AS c ON c.id_cidade = e.id_cidade" 
			+"        INNER JOIN" 
			+"    tbl_estado AS es ON es.id_estado = c.id_estado" 
			+" WHERE" 
			+"		es.estado = ?1 order by rand() limit 13", nativeQuery = true)
	public List<Restaurante> getRestauranteExibicao(String estado);
	
	
	@Query(value = "SELECT *"
			+ "FROM" 
			+"    tbl_restaurante AS r" 
			+"        INNER JOIN" 
			+"    tbl_endereco AS e ON e.id_endereco = r.id_endereco" 
			+"        INNER JOIN" 
			+"    tbl_cidade AS c ON c.id_cidade = e.id_cidade" 
			+"        INNER JOIN" 
			+"    tbl_estado AS es ON es.id_estado = c.id_estado" 
			+" WHERE" 
			+"		es.estado = ?1 order by rand() limit 8", nativeQuery = true)
	public List<Restaurante> getRestauranteDestaque(String estado);
	
	@Query(value = "select * from tbl_restaurante as r where  (select count(*) from tbl_produto as p inner join tbl_categoria_produto as cp"
				+ " on cp.id_produto = p.id_produto where p.id_restaurante  = r.id_restaurante and cp.id_categoria = ?1 ) > 2 order by rand() ", nativeQuery = true)
	List<Restaurante> getRestauranteFromCategoriaMaiorQue4(int id);

	@Query(value = "select sum(p.valor_total*9/100) as Total_Comissao from tbl_pedido as p where p.comissao_paga = 0 AND p.id_restaurante = ?1", nativeQuery = true)
	public float getDebitoRestaurante(int id);

	
	@Query(value="select count(*) as Total_Restaurantes from tbl_restaurante", nativeQuery = true)
	public int getTotalRestaurante();
	
	
	@Query(value="select * from tbl_restaurante as r where r.status = 0", nativeQuery = true)
	public List<Restaurante> getRestaurantesDesativados();
	
	@Query(value="select * from tbl_restaurante as r where r.status = 1", nativeQuery = true)
	public List<Restaurante> getRestaurantesAtivos();
	
	@Query(value="select sum(valor_total) - sum(valor_total)*9/100  as total from tbl_pedido as p where p.id_restaurante  = ?1 and p.id_status_pedido = 4", nativeQuery = true)
	public float getSaldoRestaurante(float id);

	
	
}

