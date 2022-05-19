package es.taw.swishbay.dao;

import es.taw.swishbay.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoriaRepository  extends JpaRepository<Categoria, Integer> {

    @Query("select c from Categoria c where c.nombre like :nombre")
    public Categoria findByName(String nombre);

}
