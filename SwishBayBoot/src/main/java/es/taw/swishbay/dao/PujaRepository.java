package es.taw.swishbay.dao;

import es.taw.swishbay.entity.Puja;
import es.taw.swishbay.entity.PujaPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PujaRepository  extends JpaRepository<Puja, PujaPK> {

    @Query("select pu from Producto p JOIN p.pujaList pu WHERE p.id = :producto ORDER BY pu.pujaPK.precio DESC")
    public Puja findMax(int producto);

    @Query("select p from Puja p where p.producto1.id = :id and p.pujaPK.precio = (select max(p.pujaPK.precio) from Puja p where p.producto1.id = :id)")
    Puja findMayor(int id);
}
