package es.taw.swishbay.dao;

import es.taw.swishbay.entity.Puja;
import es.taw.swishbay.entity.PujaPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

/**
 *
 * @author Miguel Oña Guerrero
 */

public interface PujaRepository  extends JpaRepository<Puja, PujaPK> {

    @Query("select p from Puja p where p.producto1.id = :idProducto and p.pujaPK.precio = (select max(p.pujaPK.precio) from Puja p where p.producto1.id = :idProducto)")
    Puja findMayor(int idProducto); //Miguel Oña Guerrero

    @Query("select p from Puja p where p.producto1.id = :idProducto order by p.pujaPK.precio desc")
    List<Puja> findOrdenado(int idProducto); //Miguel Oña Guerrero

    @Query("select p from Puja p where p.pujaPK.comprador = :idUsuario and p.pujaPK.producto = :idProducto")
    Puja findByUsuarioProducto(int idUsuario, int idProducto); //Miguel Oña Guerrero
}
