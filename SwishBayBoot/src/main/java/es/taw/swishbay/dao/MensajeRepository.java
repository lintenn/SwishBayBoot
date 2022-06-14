package es.taw.swishbay.dao;

import es.taw.swishbay.entity.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MensajeRepository  extends JpaRepository<Mensaje, Integer> {

    @Query("select m from Mensaje m where m.grupo.id = :grupo and m.asunto like CONCAT('%',:asunto,'%')")
    public List<Mensaje> findByAsuntoAndIdGrupo (Integer idGrupo, String asunto);

    @Query("select m from Mensaje m where m.grupo.id = :grupo")
    public List<Mensaje> findByIdGrupo (Integer idGrupo);

    @Query("select m from Usuario u Join u.mensajeList m where u.id = :idUsuario and m.asunto like CONCAT('%',:asunto,'%')")
    public List<Mensaje> findByAsuntoAndAreInAUser (String asunto, Integer idUsuario);

    @Query("select m from Mensaje m where m.grupo.id = :grupo and m.asunto like CONCAT('%',:asunto,'%') and m.id IN :mensajes")
    public List<Mensaje> findByAsuntoAndIdGrupoByMessages (Integer idGrupo, String asunto, List<Integer> mensajes);

    @Query("select m from Mensaje m where m.grupo.id = :grupo and m.contenido like CONCAT('%',:contenido,'%') and m.id IN :mensajes")
    public List<Mensaje> findByContenidoAndIdGrupoByMessages (Integer idGrupo, String contenido, List<Integer> mensajes);

    @Query("select m from Usuario u Join u.mensajeList m where u.id = :idUsuario and m.asunto like CONCAT('%',:asunto,'%') and m.id IN :mensajes")
    public List<Mensaje> findByAsuntoAndIdUserByMessages (Integer idUsuario, String asunto, List<Integer> mensajes);

    @Query("select m from Usuario u Join u.mensajeList m where u.id = :idUsuario and m.contenido like CONCAT('%',:contenido,'%') and m.id IN :mensajes")
    public List<Mensaje> findByContenidoAndIdUserByMessages (Integer idUsuario, String contenido, List<Integer> mensajes);
}
