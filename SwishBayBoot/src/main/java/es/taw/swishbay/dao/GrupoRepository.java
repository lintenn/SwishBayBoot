package es.taw.swishbay.dao;

import es.taw.swishbay.entity.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoRepository extends JpaRepository<Grupo, Integer> {
}
