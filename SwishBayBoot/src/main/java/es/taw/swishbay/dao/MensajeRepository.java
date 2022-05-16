package es.taw.swishbay.dao;

import es.taw.swishbay.entity.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensajeRepository  extends JpaRepository<Mensaje, Integer> {
}
