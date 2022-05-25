package es.taw.swishbay.dao;

import es.taw.swishbay.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Luis 50%, Galo 50%
 */

public interface CategoriaRepository  extends JpaRepository<Categoria, Integer> {

    @Query("select c from Categoria c where c.nombre like :nombre")
    public Categoria findByName(String nombre); // Galo

    @Query("select c from Categoria c where c.nombre like CONCAT('%',:nombre,'%')")
    public List<Categoria> findByNombre(String nombre); // Luis

}
