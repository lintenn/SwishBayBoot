package es.taw.swishbay.dao;

import es.taw.swishbay.entity.RolUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Luis
 */
public interface RolUsuarioRepository  extends JpaRepository<RolUsuario, Integer> {

    @Query("select r from RolUsuario r where r.nombre like :nombre")
    public RolUsuario findByNombre(String nombre); // Luis

}
