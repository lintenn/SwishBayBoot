package es.taw.swishbay.dao;

import es.taw.swishbay.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Luis 16%, Ángel 84%
 */

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

    @Query("select u from Usuario u where u.rol.nombre like 'compradorvendedor'")
    List<Usuario> findByCompradorVendedor(); // angel

    @Query("select u from Usuario u where u.rol.nombre like 'compradorvendedor' and u.nombre like CONCAT('%',:nombre,'%')")
    List<Usuario> findByCompradorVendedorByName(String nombre); // angel

    @Query("select u from Usuario u where u.rol.nombre like 'compradorvendedor' and u.correo like CONCAT('%',:correo,'%')")
    List<Usuario> findByCompradorVendedorByCorreo(String correo); // angel

    @Query("select u from Usuario u where u.rol.nombre like 'compradorvendedor' and u.apellidos like CONCAT('%',:apellidos,'%')")
    List<Usuario> findByCompradorVendedorByApellidos(String apellidos); // angel

    @Query("select u from Usuario u where u.rol.nombre like 'compradorvendedor' and u.ciudad like CONCAT('%',:ciudad,'%')")
    List<Usuario> findByCompradorVendedorByCiudad(String ciudad); // angel

    @Query("select u from Usuario u where u.rol.nombre like 'compradorvendedor' and u.domicilio like CONCAT('%',:domicilio,'%')")
    List<Usuario> findByCompradorVendedorByDomicilio(String domicilio); // angel

    @Query("select u from Usuario u where u.rol.nombre like 'compradorvendedor' and u.sexo like CONCAT('%',:sexo,'%')")
    List<Usuario> findByCompradorVendedorBySexo(String sexo); // angel

    @Query("select u from Usuario u where u.saldo >= :saldoDesde and u.id IN :usuarios")
    List<Usuario> findByCompradorVendedorBySaldoDesde(Double saldoDesde, List<Integer> usuarios); // angel

    @Query("select u from Usuario u where u.saldo <= :saldoDesde and u.id IN :usuarios")
    List<Usuario> findByCompradorVendedorBySaldoHasta(Double saldoDesde, List<Integer> usuarios); // angel

    @Query("select u from Usuario u where u.rol.nombre like 'compradorvendedor' and u.nombre like CONCAT('%',:nombre,'%') and u.id NOT IN :idsGrupo")
    List<Usuario> findByCompradorVendedorByNameQueNoPertencenAUnGrupo(String nombre, List<Integer> idsGrupo); // angel

    @Query("select u from Usuario u where u.rol.nombre like 'compradorvendedor' and u.correo like CONCAT('%',:correo,'%') and u.id NOT IN :idsGrupo")
    List<Usuario> findByCompradorVendedorByCorreoQueNoPertencenAUnGrupo(String correo, List<Integer> idsGrupo); // angel

    @Query("select u from Usuario u where u.rol.nombre like 'compradorvendedor' and u.apellidos like CONCAT('%',:apellidos,'%') and u.id NOT IN :idsGrupo")
    List<Usuario> findByCompradorVendedorByApellidosQueNoPertencenAUnGrupo(String apellidos, List<Integer> idsGrupo); // angel

    @Query("select u from Usuario u where u.rol.nombre like 'compradorvendedor' and u.ciudad like CONCAT('%',:ciudad,'%') and u.id NOT IN :idsGrupo")
    List<Usuario> findByCompradorVendedorByCiudadQueNoPertencenAUnGrupo(String ciudad, List<Integer> idsGrupo); // angel

    @Query("select u from Usuario u where u.rol.nombre like 'compradorvendedor' and u.domicilio like CONCAT('%',:domicilio,'%') and u.id NOT IN :idsGrupo")
    List<Usuario> findByCompradorVendedorByDomicilioQueNoPertencenAUnGrupo(String domicilio, List<Integer> idsGrupo); // angel

    @Query("select u from Usuario u where u.rol.nombre like 'compradorvendedor' and u.sexo like CONCAT('%',:sexo,'%') and u.id NOT IN :idsGrupo")
    List<Usuario> findByCompradorVendedorBySexoQueNoPertencenAUnGrupo(String sexo, List<Integer> idsGrupo); // angel

    @Query("select u from Usuario u where u.saldo >= :saldoDesde and u.id IN :usuarios and u.id NOT IN :idsGrupo")
    List<Usuario> findByCompradorVendedorBySaldoDesdeQueNoPertencenAUnGrupo(Integer saldoDesde, List<Integer> usuarios, List<Integer> idsGrupo); // angel

    @Query("select u from Usuario u where u.saldo <= :saldoDesde and u.id IN :usuarios and u.id NOT IN :idsGrupo")
    List<Usuario> findByCompradorVendedorBySaldoHastaQueNoPertencenAUnGrupo(Integer saldoDesde, List<Integer> usuarios, List<Integer> idsGrupo); // angel

    @Query("select u from Usuario u where u.rol.nombre like 'compradorvendedor' and u.nombre like CONCAT('%',:nombre,'%') and u.id IN :idsGrupo")
    List<Usuario> findByCompradorVendedorByNameQuePertencenAUnGrupo(String nombre, List<Integer> idsGrupo); // angel

    @Query("select u from Usuario u where u.rol.nombre like 'compradorvendedor' and u.correo like CONCAT('%',:correo,'%') and u.id IN :idsGrupo")
    List<Usuario> findByCompradorVendedorByCorreoQuePertencenAUnGrupo(String correo, List<Integer> idsGrupo); // angel

    @Query("select u from Usuario u where u.rol.nombre like 'compradorvendedor' and u.apellidos like CONCAT('%',:apellidos,'%') and u.id IN :idsGrupo")
    List<Usuario> findByCompradorVendedorByApellidosQuePertencenAUnGrupo(String apellidos, List<Integer> idsGrupo); // angel

    @Query("select u from Usuario u where u.rol.nombre like 'compradorvendedor' and u.ciudad like CONCAT('%',:ciudad,'%') and u.id IN :idsGrupo")
    List<Usuario> findByCompradorVendedorByCiudadQuePertencenAUnGrupo(String ciudad, List<Integer> idsGrupo); // angel

    @Query("select u from Usuario u where u.rol.nombre like 'compradorvendedor' and u.domicilio like CONCAT('%',:domicilio,'%') and u.id IN :idsGrupo")
    List<Usuario> findByCompradorVendedorByDomicilioQuePertencenAUnGrupo(String domicilio, List<Integer> idsGrupo); // angel

    @Query("select u from Usuario u where u.rol.nombre like 'compradorvendedor' and u.sexo like CONCAT('%',:sexo,'%') and u.id IN :idsGrupo")
    List<Usuario> findByCompradorVendedorBySexoQuePertencenAUnGrupo(String sexo, List<Integer> idsGrupo); // angel

    @Query("select u from Usuario u where u.saldo >= :saldoDesde and u.id IN :usuarios")
    List<Usuario> findByCompradorVendedorBySaldoDesdeQuePertencenAUnGrupo(Integer saldoDesde, List<Integer> usuarios); // angel

    @Query("select u from Usuario u where u.saldo <= :saldoDesde and u.id IN :usuarios")
    List<Usuario> findByCompradorVendedorBySaldoHastaQuePertencenAUnGrupo(Integer saldoDesde, List<Integer> usuarios); // angel

    @Query("select u from Usuario u where u.rol.nombre like 'marketing'")
    List<Usuario> findByMarketing(); // angel
}
