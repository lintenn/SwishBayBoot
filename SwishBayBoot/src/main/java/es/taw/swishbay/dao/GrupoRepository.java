package es.taw.swishbay.dao;

import es.taw.swishbay.entity.Grupo;
import es.taw.swishbay.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GrupoRepository extends JpaRepository<Grupo, Integer> {

    @Query("select g from Grupo g where g.nombre like CONCAT('%',:nombre,'%')")
    public List<Grupo> findGrupoByGrupoNombre(String nombre);

    @Query("select g from Grupo g where g.nombre like :nombre")
    public List<Grupo> findGrupoByGrupoNombreExtricto (String nombre);

    @Query("select u from Grupo g JOIN g.usuarioList u where g.id = :id")
    public List<Usuario> findUsuariosByGrupoId (Integer id);

    @Query("select u from Grupo g JOIN g.usuarioList u where g.id = :id and u.nombre like CONCAT('%',:nombre,'%')")
    public List<Usuario> findUsuariosByGrupoIdAndGrupoNombre (Integer id, String nombre);

    @Query("select u from Usuario u where u.rol.nombre like 'compradorvendedor' and u.id NOT IN :idsUsuarios")
    public List<Usuario> findUsuariosNotInGrupoId (List<Integer> idsUsuarios);

    @Query("select u from Usuario u where u.rol.nombre like 'compradorvendedor' and u.nombre like CONCAT('%',:nombre,'%') and u.id NOT IN :idsUsuarios")
    public List<Usuario> findUsuariosNotInGrupoIdByNombre (List<Integer> idsUsuarios, String nombre);

    @Query("select g from Grupo g where g.nombre like CONCAT('%',:nombre,'%') and g.id IN :ids")
    public List<Grupo> findGrupoByGrupoNombreAndGroups (String nombre, List<Integer> ids);

    @Query("select g from Grupo g where g.marketing.nombre like CONCAT('%',:nombre,'%') and g.marketing.apellidos like CONCAT('%',:apellidos,'%') and g.id IN :ids")
    public List<Grupo> findGrupoByGrupoNombreCreadorAndApellidosCreadorAndGroups (String nombre, String apellidos, List<Integer> ids);

    @Query("select g from Grupo g where g.marketing.nombre like CONCAT('%',:nombre,'%') and g.id IN :ids")
    public List<Grupo> findGrupoByGrupoNombreCreadorAndGroups (String nombre, List<Integer> ids);

    @Query("select g from Grupo g where g.marketing.apellidos like CONCAT('%',:apellidos,'%') and g.id IN :ids")
    public List<Grupo> findGrupoByGrupoApellidosCreadorAndGroups (String apellidos, List<Integer> ids);

}
