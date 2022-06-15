package es.taw.swishbay.service;

import es.taw.swishbay.dao.ProductoRepository;
import es.taw.swishbay.dto.ProductoDTO;
import es.taw.swishbay.entity.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Service para mostrar productos al comprador
 *
 * @author Miguel Oña Guerrero
 */

@Service
public class CompradorService {

    private ProductoRepository productoRepository;

    @Autowired
    public void setProductoService(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
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

    public List<ProductoDTO> listarProductosExistentes(int idUsuario){
        List<Producto> productos = productoRepository.findAllExistentes(idUsuario);

        return listaProductoEntityADTO(productos);
    }

    public List<ProductoDTO> listarProductosExistentes(String filtroTitulo, String filtroCategoria, Double filtroPrecio, int idUsuario){ // Miguel
        List<Producto> productos = productoRepository.findExistentesByFiltro(filtroTitulo, filtroCategoria, filtroPrecio, idUsuario);

        return listaProductoEntityADTO(productos);
    }

    public List<ProductoDTO> listarProductosEnPuja(int idUsuario){
        List<Producto> productos = productoRepository.findAllEnPuja(idUsuario);

        return listaProductoEntityADTO(productos);
    }

    public List<ProductoDTO> listarProductosEnPuja(String filtroTitulo, String filtroCategoria, Double filtroPrecio, int idUsuario){ // Miguel
        List<Producto> productos = productoRepository.findEnPujaByFiltro(filtroTitulo, filtroCategoria, filtroPrecio, idUsuario);

        return this.listaProductoEntityADTO(productos);
    }

    public List<ProductoDTO> listarProductosFavoritos(int idUsuario){
        List<Producto> productos = productoRepository.findAllFavoritos(idUsuario);

        return listaProductoEntityADTO(productos);
    }

    public List<ProductoDTO> listarProductosFavoritos(String filtroTitulo, String filtroCategoria, Double filtroPrecio, int idUsuario){ // Miguel
        List<Producto> productos = productoRepository.findFavoritosByFiltro(filtroTitulo, filtroCategoria, filtroPrecio, idUsuario);

        return this.listaProductoEntityADTO(productos);
    }

    public List<ProductoDTO> listarProductosComprados(int idUsuario){
        List<Producto> productos = productoRepository.findAllComprados(idUsuario);

        return listaProductoEntityADTO(productos);
    }

    public List<ProductoDTO> listarProductosComprados(String filtroTitulo, String filtroCategoria, Double filtroPrecio, int idUsuario){ // Miguel
        List<Producto> productos = productoRepository.findCompradosByFiltro(filtroTitulo, filtroCategoria, filtroPrecio, idUsuario);

        return this.listaProductoEntityADTO(productos);
    }

}