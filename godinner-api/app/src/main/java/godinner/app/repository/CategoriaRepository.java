package godinner.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import godinner.app.model.Categoria;


public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
