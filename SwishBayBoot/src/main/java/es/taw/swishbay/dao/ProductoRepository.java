package es.taw.swishbay.dao;

import es.taw.swishbay.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductoRepository  extends JpaRepository<Producto, Integer> {

    @Query("select p from Producto p where p.vendedor.id= :user")
    List<Producto> findVendidos(Integer user);

    @Query("select p from Producto p where p.vendedor.id= :user and p.precioSalida>=:desde and p.precioSalida<=:hasta")
    List<Producto> findVendidosDesde(Integer user, Double desde, Double hasta);

    @Query("select p from Producto p where p.categoria.nombre like :filtroCategoria and p.vendedor.id=:user")
    List<Producto> findVendidosFiltered(Integer user, String filtroCategoria);

    @Query("select p from Producto p where p.categoria.nombre like :filtroCategoria and p.vendedor.id=:user and p.precioSalida>=:desde and p.precioSalida<=:hasta")
    List<Producto> findVendidosFilteredDesde(Integer user, String filtroCategoria, Double desde, Double hasta);

    @Query("select p from Producto p where p.titulo like CONCAT('%', :titulo,'%') and p.vendedor.id=:user")
    List<Producto> findVendidosByNombre(Integer user, String titulo);

    @Query("select p from Producto p where p.titulo like CONCAT('%', :titulo,'%') and p.categoria.nombre like :filtroCategoria and p.vendedor.id=:user")
    List<Producto> findVendidosByNombreFiltered(Integer user, String titulo, String filtroCategoria);

    @Query("select p from Producto p where p.titulo like CONCAT('%', :titulo,'%') and p.vendedor.id=:user and p.precioSalida>=:desde and p.precioSalida<=:hasta")
    List<Producto> findVendidosByNombreDesde(Integer user, String titulo, Double desde, Double hasta);

    @Query("select p from Producto p where p.titulo like CONCAT('%', :titulo,'%') and p.categoria.nombre like :filtroCategoria and p.vendedor.id=:user and p.precioSalida>=:desde and p.precioSalida<=:hasta")
    List<Producto> findVendidosByNombreFilteredDesde(Integer user, String titulo, String filtroCategoria, Double desde, Double hasta);

    @Query("select p, MAX(pu.pujaPK.precio) from Producto p LEFT JOIN p.pujaList pu where p.enPuja=1 and p.vendedor.id= :user GROUP BY p")
    List<Object[]> findEnPuja(Integer user);

    @Query("select p, MAX(pu.pujaPK.precio) from Producto p LEFT JOIN p.pujaList pu where p.categoria.nombre like :filtroCategoria and p.enPuja=1 and p.vendedor.id= :user GROUP BY p")
    List<Object[]> findEnPujaFiltered(Integer user, String filtroCategoria);

    @Query("select p, MAX(pu.pujaPK.precio) from Producto p LEFT JOIN p.pujaList pu where p.titulo like CONCAT('%', :titulo,'%') and p.enPuja=1 and p.vendedor.id= :user GROUP BY p")
    List<Object[]> findEnPujaByNombre(Integer user, String titulo);

    @Query("select p, MAX(pu.pujaPK.precio) from Producto p LEFT JOIN p.pujaList pu where p.titulo like CONCAT('%', :titulo,'%') and p.categoria.nombre like :filtroCategoria and p.enPuja=1 and p.vendedor.id= :user GROUP BY p")
    List<Object[]> findEnPujaByNombreFiltered(Integer user, String titulo, String filtroCategoria);

}
