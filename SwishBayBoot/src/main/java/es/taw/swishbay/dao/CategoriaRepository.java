package es.taw.swishbay.dao;

import es.taw.swishbay.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository  extends JpaRepository<Categoria, Integer> {
}
