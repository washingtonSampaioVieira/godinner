package godinner.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import godinner.app.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
