package es.taw.swishbay.dao;

import es.taw.swishbay.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository  extends JpaRepository<Usuario, Integer> {
}
