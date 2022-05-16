package es.taw.swishbay.dao;

import es.taw.swishbay.entity.Puja;
import es.taw.swishbay.entity.PujaPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PujaRepository  extends JpaRepository<Puja, PujaPK> {
}
