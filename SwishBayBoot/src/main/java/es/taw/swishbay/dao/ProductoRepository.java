package es.taw.swishbay.dao;

import es.taw.swishbay.entity.Producto;
import es.taw.swishbay.entity.Puja;
import es.taw.swishbay.entity.PujaPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductoRepository  extends JpaRepository<Producto, Integer> {

    @Query("select p from Producto p where p.titulo like CONCAT('%',:titulo,'%')")
    List<Producto> findByNombre(String titulo); // Luis

    @Query("select p from Producto p where p.categoria.nombre like :filtroCategoria")
    List<Producto> findAll(String filtroCategoria); // Luis

    @Query("select p from Producto p where p.titulo like CONCAT('%',:titulo,'%') and p.categoria.nombre like :filtroCategoria")
    List<Producto> findByNombre(String titulo, String filtroCategoria); // Luis

    @Query("select p from Producto p where p.precioSalida >= :desde and p.precioSalida <= :hasta")
    List<Producto> findAllDesde(Double desde, Double hasta); // Luis

    @Query("select p from Producto p where p.categoria.nombre like :filtroCategoria and p.precioSalida >= :desde and p.precioSalida <= :hasta")
    List<Producto> findAllFilteredDesde(String filtroCategoria, Double desde, Double hasta); // Luis

    @Query("select p from Producto p where p.titulo like CONCAT('%',:titulo,'%') and p.precioSalida >= :desde and p.precioSalida <= :hasta")
    List<Producto> findByNombreDesde(String titulo, Double desde, Double hasta); // Luis

    @Query("select p from Producto p where p.titulo like CONCAT('%',:titulo,'%') and p.categoria.nombre like :filtroCategoria and p.precioSalida >= :desde and p.precioSalida <= :hasta")
    List<Producto> findByNombreFilteredDesde(String titulo, String filtroCategoria, Double desde, Double hasta); // Luis

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

    @Query("select p, MAX(pu.pujaPK.precio) from Producto p JOIN p.pujaList pu where p.vendedor.id=:user and p.comprador is not null GROUP BY p")
    List<Object[]> findVendidosAUser(Integer user);

    @Query("select p, MAX(pu.pujaPK.precio) from Producto p JOIN p.pujaList pu where p.vendedor.id=:user and p.categoria.nombre like :filtroCategoria and p.comprador is not null GROUP BY p")
    List<Object[]> findVendidosAUserFiltered(Integer user, String filtroCategoria);

    @Query("select p, MAX(pu.pujaPK.precio) from Producto p JOIN p.pujaList pu where p.vendedor.id=:user and p.titulo like CONCAT('%', :titulo,'%') and p.comprador is not null GROUP BY p")
    List<Object[]> findVendidosAUserByNombre(Integer user, String titulo);

    @Query("select p, MAX(pu.pujaPK.precio) from Producto p JOIN p.pujaList pu where p.vendedor.id=:user and p.titulo like CONCAT('%', :titulo,'%') and p.categoria.nombre like :filtroCategoria and p.comprador is not null GROUP BY p")
    List<Object[]> findVendidosAUserByNombreFiltered(Integer user, String titulo, String filtroCategoria);

    @Query("select MAX(pu.pujaPK.precio) from Producto p LEFT JOIN p.pujaList pu where p.id=:id Group by p")
    Double findPrecioMax(Integer id);

    @Query("select pu from Puja pu where pu.producto1.id= :pId and pu.pujaPK<>:pujaId")
    List<Puja> findLosers(Integer pId, PujaPK pujaId);

    @Query("select p from Producto p where NOT (p.vendedor.id = :id) and p.comprador is null")
    List<Producto> findAllExistentes(Integer id); //Miguel Oña Guerrero

    @Query("select p from Producto p where p.titulo like CONCAT('%', :titulo,'%')  and p.precioSalida <= :precio and p.categoria.nombre like CONCAT('%',:filtroCategoria ,'%') and NOT (p.vendedor.id = :id) and p.comprador is null")
    List<Producto> findExistentesByFiltro(String titulo, String filtroCategoria, Double precio, Integer id); //Miguel Oña Guerrero

    @Query("select p from Producto p where p.enPuja = 1 and NOT (p.vendedor.id = :id) and p.comprador is null")
    List<Producto> findAllEnPuja(Integer id); //Miguel Oña Guerrero

    @Query("select p from Producto p where p.enPuja = 1 and p.titulo like CONCAT('%', :titulo,'%') and p.precioSalida <= :precio and p.categoria.nombre like CONCAT('%',:filtroCategoria ,'%') and NOT (p.vendedor.id = :id) and p.comprador is null")
    List<Producto> findEnPujaByFiltro(String titulo, String filtroCategoria, Double precio, Integer id); //Miguel Oña Guerrero

    @Query("select p from Producto p join p.usuarioList u where u.id = :id and NOT (p.vendedor.id = :id) and (p.comprador is null or p.comprador.id = :id)")
    List<Producto> findAllFavoritos(Integer id); //Miguel Oña Guerrero

    @Query("select p from Producto p join p.usuarioList u where u.id = :id and p.precioSalida <= :precio and p.titulo like CONCAT('%', :titulo,'%') and p.categoria.nombre like CONCAT('%',:filtroCategoria ,'%') and NOT (p.vendedor.id = :id) and (p.comprador is null or p.comprador.id = :id)")
    List<Producto> findFavoritosByFiltro(String titulo, String filtroCategoria, Double precio, Integer id); //Miguel Oña Guerrero

    @Query("select p from Producto p where p.comprador.id = :id")
    List<Producto> findAllComprados(Integer id); //Miguel Oña Guerrero

    @Query("select p from Producto p where p.titulo like CONCAT('%', :titulo,'%') and p.precioSalida <= :precio and p.categoria.nombre like CONCAT('%',:filtroCategoria ,'%') and p.comprador.id = :id")
    List<Producto> findCompradosByFiltro(String titulo, String filtroCategoria, Double precio, Integer id); //Miguel Oña Guerrero

    @Query("select max(p.precioSalida) from Producto p where p.id in :ids")
    Double findPrecioMaximo(List<Integer> ids); //Miguel Oña Guerrero

    @Query("select pu from Puja pu where pu.producto1.id = :idProducto and pu.usuario.id NOT IN :idsUsers")
    List<Puja> findUsersPujaNoGrupo(Integer idProducto, List<Integer> idsUsers); //angel

}
