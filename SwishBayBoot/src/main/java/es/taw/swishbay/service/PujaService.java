package es.taw.swishbay.service;

import es.taw.swishbay.dao.ProductoRepository;
import es.taw.swishbay.dao.PujaRepository;
import es.taw.swishbay.dao.UsuarioRepository;
import es.taw.swishbay.dto.ProductoDTO;
import es.taw.swishbay.dto.PujaDTO;
import es.taw.swishbay.entity.Producto;
import es.taw.swishbay.entity.Puja;
import es.taw.swishbay.entity.PujaPK;
import es.taw.swishbay.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Miguel Oña Guerrero
 */

@Service
public class PujaService {

    private PujaRepository pujaRepository;
    private ProductoRepository productoRepository;
    private UsuarioRepository usuarioRepository;

    @Autowired
    public void setPujaRepository(PujaRepository pujaRepository){
        this.pujaRepository = pujaRepository;
    }

    @Autowired
    public void setProductoRepository(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    private List<PujaDTO> listaEntityADTO (List<Puja> lista) { // Miguel Oña Guerrero
        List<PujaDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Puja puja : lista) {
                listaDTO.add(puja.toDTO());
            }
        }
        return listaDTO;
    }

    public PujaDTO buscarPuja(Integer idUsuario, Integer idProducto){ //Miguel Oña Guerrero
        Puja puja = pujaRepository.findByUsuarioProducto(idUsuario, idProducto);

        return (puja == null) ? null : puja.toDTO();
    }

    public List<PujaDTO> buscarPujasOrdenadas(Integer idProducto){ //Miguel Oña Guerrero
        List<Puja> pujas = pujaRepository.findOrdenado(idProducto);

        return this.listaEntityADTO(pujas);
    }

    public PujaDTO buscarMayorPuja(Integer idProducto){ //Miguel Oña Guerrero
        Puja puja = pujaRepository.findMayor(idProducto);

        return (puja == null) ? null : puja.toDTO();
    }

    public List<PujaDTO> buscarMayoresPujas(List<ProductoDTO> productos){ //Miguel Oña Guerrero
        List<PujaDTO> pujas = new ArrayList();

        for(ProductoDTO producto : productos){
            pujas.add(this.buscarMayorPuja(producto.getId()));
        }

        return pujas;
    }

    public String checkPuja(PujaDTO puja){ //Miguel Oña Guerrero
        String error = "";

        Producto producto = productoRepository.findById(puja.getProducto()).orElse(null);
        PujaDTO ultimaPuja = buscarPuja(puja.getComprador().getId(), puja.getProducto());
        PujaDTO mayorPuja = buscarMayorPuja(puja.getProducto());

        if(ultimaPuja == null && puja.getComprador().getSaldo() < puja.getPrecio()){
            error = "¡No tienes suficiente saldo!";

        }else if(ultimaPuja != null && puja.getComprador().getSaldo() < (puja.getPrecio() - ultimaPuja.getPrecio())){
            error = "¡No tienes suficiente saldo!";

        }else if(mayorPuja == null && puja.getPrecio() < producto.getPrecioSalida()){
            error = "¡Cantidad insuficiente!";

        }else if(mayorPuja != null && puja.getPrecio() < mayorPuja.getPrecio()){
            error = "¡Cantidad insuficiente!";

        }else if(mayorPuja != null && mayorPuja.getComprador().getId() == puja.getComprador().getId()) {
            error = "¡Ya has realizado la máxima puja!";
        }

        return error;
    }

    public Double realizarPuja(PujaDTO pujaDTO){ //Miguel Oña Guerrero
        Producto producto = productoRepository.findById(pujaDTO.getProducto()).orElse(null);
        Usuario usuario = usuarioRepository.findById(pujaDTO.getComprador().getId()).orElse(null);
        Puja puja = pujaRepository.findByUsuarioProducto(usuario.getId(), producto.getId());
        Double cantidadRestada;

        if(puja == null){
            cantidadRestada = pujaDTO.getPrecio();
        }else{
            cantidadRestada = pujaDTO.getPrecio() - puja.getPujaPK().getPrecio();
            pujaRepository.delete(puja);

            producto.getPujaList().remove(puja);
            productoRepository.save(producto);

            usuario.getPujaList().remove(puja);
            usuarioRepository.save(usuario);
        }

        Puja pujaNueva = new Puja();

        pujaNueva.setFecha(new Date());
        pujaNueva.setUsuario(usuario);
        pujaNueva.setProducto1(producto);

        PujaPK pujapk = new PujaPK();
        pujapk.setComprador(usuario.getId());
        pujapk.setProducto(producto.getId());
        pujapk.setPrecio(pujaDTO.getPrecio());

        pujaNueva.setPujaPK(pujapk);
        pujaRepository.save(pujaNueva);

        producto.getPujaList().add(pujaNueva);
        productoRepository.save(producto);

        usuario.getPujaList().add(pujaNueva);
        usuarioRepository.save(usuario);

        return cantidadRestada;
    }
}
