package es.taw.swishbay.dao;

import es.taw.swishbay.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository  extends JpaRepository<Producto, Integer> {
}
