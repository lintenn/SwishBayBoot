package es.taw.swishbay.dao;

import es.taw.swishbay.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsuarioRepository  extends JpaRepository<Usuario, Integer> {

    Usuario findByCorreo(String correo); // Luis

    @Query("select u from Usuario u where u.correo = :correo and u.password = :password")
    Usuario comprobarUsuario(String correo, String password); // Luis

    @Query("select u from Usuario u where u.nombre like CONCAT('%',:nombre,'%')")
    List<Usuario> findByNombre(String nombre); // Luis

    @Query("select u from Usuario u where u.rol.nombre like :filtroRol")
    List<Usuario> findAll(String filtroRol); // Luis

    @Query("select u from Usuario u where u.nombre like CONCAT('%',:nombre,'%') and u.rol.nombre like :filtroRol")
    List<Usuario> findByNombre(String nombre, String filtroRol); // Luis

}
