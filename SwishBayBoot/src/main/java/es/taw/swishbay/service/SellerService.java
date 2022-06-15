package es.taw.swishbay.service;

import es.taw.swishbay.dao.CategoriaRepository;
import es.taw.swishbay.dao.ProductoRepository;
import es.taw.swishbay.dto.CategoriaDTO;
import es.taw.swishbay.dto.ProductoDTO;
import es.taw.swishbay.dto.UsuarioDTO;
import es.taw.swishbay.entity.Categoria;
import es.taw.swishbay.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service del vendedor
 * @author Galo
 */

@Service
public class SellerService {

    private CategoriaRepository categoriaRepository;
    private ProductoRepository productoRepository;

    @Autowired
    public void setCategoriaRepository(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Autowired
    public void setProductoRepository(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    private List<CategoriaDTO> listaCategoriaEntityADTO (List<Categoria> lista) {
        List<CategoriaDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Categoria c : lista) {
                listaDTO.add(c.toDTO());
            }
        }
        return listaDTO;
    }

    private List<ProductoDTO> listaProductoEntityADTO (List<Producto> lista) {
        List<ProductoDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Producto p : lista) {
                listaDTO.add(p.toDTO());
            }
        }
        return listaDTO;
    }

    private void listaEnPujaEntityADTO (List<Object[]> lista) {

        if (lista != null) {
            for (Object[] p : lista) {
                Producto pr = (Producto) p[0];
                p[0]= pr.toDTO();
            }
        }

    }

    public List<CategoriaDTO> listarCategorias(){
        List<Categoria> list = categoriaRepository.findAll();
        return this.listaCategoriaEntityADTO(list);
    }

    public List<ProductoDTO> listarTodos(UsuarioDTO user){ // Galo

        List<Producto> productos = productos = productoRepository.findVendidos(user.getId());

        return this.listaProductoEntityADTO(productos);
    }

    public List<ProductoDTO> listarProductos(UsuarioDTO user, String filtroNombre, String filtroCategoria, String filtroDesde, String filtroHasta){ // Galo

        List<Producto> productos = null;
        if(filtroNombre == null || filtroNombre.isEmpty()){

            if(filtroDesde==null){   // En este caso filtroHasta también sería null
                if(filtroCategoria==null || filtroCategoria.equals("Categoria")){
                    productos = productoRepository.findVendidos(user.getId());
                }else{
                    productos= productoRepository.findVendidosFiltered(user.getId(), filtroCategoria);
                }
            }else{
                if(filtroCategoria==null || filtroCategoria.equals("Categoria")){
                    productos = productoRepository.findVendidosDesde(user.getId(), Double.parseDouble(filtroDesde), Double.parseDouble(filtroHasta));
                }else{
                    productos= productoRepository.findVendidosFilteredDesde(user.getId(), filtroCategoria, Double.parseDouble(filtroDesde), Double.parseDouble(filtroHasta));
                }
            }

        }else{
            if(filtroDesde==null){   // En este caso filtroHasta también sería null
                if(filtroCategoria==null || filtroCategoria.equals("Categoria")){
                    productos = productoRepository.findVendidosByNombre(user.getId(),filtroNombre);

                }else{
                    productos = productoRepository.findVendidosByNombreFiltered(user.getId(),filtroNombre,filtroCategoria);

                }
            }else{
                if(filtroCategoria==null || filtroCategoria.equals("Categoria")){
                    productos = productoRepository.findVendidosByNombreDesde(user.getId(),filtroNombre, Double.parseDouble(filtroDesde), Double.parseDouble(filtroHasta));

                }else{
                    productos = productoRepository.findVendidosByNombreFilteredDesde(user.getId(),filtroNombre,filtroCategoria, Double.parseDouble(filtroDesde), Double.parseDouble(filtroHasta));

                }
            }
        }
        return this.listaProductoEntityADTO(productos);
    }

    public List<Object[]> listarTodosEnPuja(UsuarioDTO user) {

        List<Object[]> lista = productoRepository.findEnPuja(user.getId());
        this.listaEnPujaEntityADTO(lista);
        return lista;
    }

    public List<Object[]> listarEnPuja(UsuarioDTO user, String filtroNombre, String filtroCategoria){

        List<Object[]> productos = null;

        if(filtroNombre == null || filtroNombre.isEmpty()){
            if(filtroCategoria==null || filtroCategoria.equals("Categoria")){
                productos = productoRepository.findEnPuja(user.getId());

            }else{
                productos= productoRepository.findEnPujaFiltered(user.getId(),filtroCategoria);

            }
        }else{
            if(filtroCategoria==null || filtroCategoria.equals("Categoria")){
                productos = productoRepository.findEnPujaByNombre(user.getId(),filtroNombre);

            }else{
                productos = productoRepository.findEnPujaByNombreFiltered(user.getId(),filtroNombre,filtroCategoria);

            }
        }
        listaEnPujaEntityADTO(productos);
        return productos;
    }

    public List<Object[]> listarTodosVendidos(UsuarioDTO user) {

        List<Object[]> productos = productoRepository.findVendidosAUser(user.getId());

        listaEnPujaEntityADTO(productos);
        return productos;
    }

    public List<Object[]> listarVendidos(UsuarioDTO user, String filtroNombre, String filtroCategoria) {

        List<Object[]> productos = null;

        if(filtroNombre == null || filtroNombre.isEmpty()){
            if(filtroCategoria==null || filtroCategoria.equals("Categoria")){
                productos = productoRepository.findVendidosAUser(user.getId());

            }else{
                productos= productoRepository.findVendidosAUserFiltered(user.getId(), filtroCategoria);

            }
        }else{
            if(filtroCategoria==null || filtroCategoria.equals("Categoria")){
                productos = productoRepository.findVendidosAUserByNombre(user.getId(), filtroNombre);

            }else{
                productos = productoRepository.findVendidosAUserByNombreFiltered(user.getId(), filtroNombre,filtroCategoria);

            }
        }

        listaEnPujaEntityADTO(productos);
        return productos;
    }
}
