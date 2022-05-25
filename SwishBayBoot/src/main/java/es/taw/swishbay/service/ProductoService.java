package es.taw.swishbay.service;

import es.taw.swishbay.dao.CategoriaRepository;
import es.taw.swishbay.dao.ProductoRepository;
import es.taw.swishbay.dao.PujaRepository;
import es.taw.swishbay.dao.UsuarioRepository;
import es.taw.swishbay.dto.ProductoDTO;
import es.taw.swishbay.entity.Categoria;
import es.taw.swishbay.entity.Producto;
import es.taw.swishbay.entity.Puja;
import es.taw.swishbay.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductoService {

    private ProductoRepository productoRepository;
    private CategoriaRepository categoriaRepository;
    private UsuarioRepository usuarioRepository;
    private PujaRepository pujaRepository;

    @Autowired
    public void setProductoRepository(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Autowired
    public void setCategoriaRepository(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Autowired
    public void setPujaRepository(PujaRepository pujaRepository) {
        this.pujaRepository = pujaRepository;
    }

    private List<ProductoDTO> listaEntityADTO (List<Producto> lista) { // Luis
        List<ProductoDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Producto producto : lista) {
                listaDTO.add(producto.toDTO());
            }
        }
        return listaDTO;
    }

    public List<ProductoDTO> listarProductos (String filtroNombre, String filtroCategoria) { // Luis
        List<Producto> productos = null;

        if (filtroNombre == null || filtroNombre.isEmpty()) {
            if (filtroCategoria==null || filtroCategoria.equals("Categoria")) {
                productos = this.productoRepository.findAll();

            } else {
                productos= this.productoRepository.findAll(filtroCategoria);

            }
        } else {
            if (filtroCategoria==null || filtroCategoria.equals("Categoria")) {
                productos = this.productoRepository.findByNombre(filtroNombre);

            } else {
                productos = productoRepository.findByNombre(filtroNombre,filtroCategoria);

            }
        }

        return this.listaEntityADTO(productos);
    }

    public List<ProductoDTO> listarProductos (String filtroNombre, String filtroCategoria, String filtroDesde, String filtroHasta) { // Luis
        List<Producto> productos = null;

        if (filtroNombre == null || filtroNombre.isEmpty()) {

            if (filtroDesde == null) {   // En este caso filtroHasta también sería null
                if (filtroCategoria==null || filtroCategoria.equals("Categoria")) {
                    productos = this.productoRepository.findAll();

                } else {
                    productos= this.productoRepository.findAll(filtroCategoria);

                }
            } else {
                if (filtroCategoria==null || filtroCategoria.equals("Categoria")) {
                    productos = this.productoRepository.findAllDesde(Double.parseDouble(filtroDesde), Double.parseDouble(filtroHasta));

                } else {
                    productos= this.productoRepository.findAllFilteredDesde(filtroCategoria, Double.parseDouble(filtroDesde), Double.parseDouble(filtroHasta));

                }
            }

        } else {

            if (filtroDesde == null) {   // En este caso filtroHasta también sería null
                if (filtroCategoria==null || filtroCategoria.equals("Categoria")) {
                    productos = this.productoRepository.findByNombre(filtroNombre);

                } else {
                    productos = productoRepository.findByNombre(filtroNombre, filtroCategoria);

                }
            } else {
                if (filtroCategoria==null || filtroCategoria.equals("Categoria")) {
                    productos = this.productoRepository.findByNombreDesde(filtroNombre, Double.parseDouble(filtroDesde), Double.parseDouble(filtroHasta));

                } else {
                    productos = productoRepository.findByNombreFilteredDesde(filtroNombre, filtroCategoria, Double.parseDouble(filtroDesde), Double.parseDouble(filtroHasta));

                }
            }


        }

        return this.listaEntityADTO(productos);
    }

    public ProductoDTO buscarProducto(String strId){ // Galo
        Producto p=null;
        if(strId !=null && !strId.isEmpty()){
            p = productoRepository.getById(Integer.parseInt(strId));


        }
        return (ProductoDTO) (p!=null ? p.toDTO(): p);
    }

    public Double precioMax(String strId){ // Galo

        Double p=0.0;

        if(strId !=null && !strId.isEmpty()){
            p = productoRepository.findPrecioMax(Integer.parseInt(strId));
        }
        return p;
    }

    private void rellenarProducto(Producto p, String titulo, String desc, String foto, Date date, String categoria, String precio, int vendedor){ // Galo
        p.setTitulo(titulo);
        p.setDescripcion(desc);
        p.setFoto(foto);
        p.setFinPuja(date);
        p.setCategoria(categoriaRepository.findByName(categoria));
        p.setPrecioSalida(Double.parseDouble(precio));
        short n=0;
        p.setEnPuja(n);
        p.setVendedor(usuarioRepository.getById(vendedor));
    }

    private void rellenarProducto(Producto p, String titulo, String desc, String foto, Date date, String categoria, String precio){ // Galo
        p.setTitulo(titulo);
        p.setDescripcion(desc);
        p.setFoto(foto);
        p.setFinPuja(date);
        p.setCategoria(categoriaRepository.findByName(categoria));
        p.setPrecioSalida(Double.parseDouble(precio));
        short n=0;
        p.setEnPuja(n);

    }

    public void crearProducto(String nombre, String descripcion, String foto, java.sql.Date date, String categoria, String precio, Integer id) {

        Producto p = new Producto();
        Categoria c = categoriaRepository.findByName(categoria);
        Usuario seller = usuarioRepository.getById(id);

        rellenarProducto(p,nombre,descripcion,foto,date,categoria,precio,id);
        c.getProductoList().add(p);
        seller.getProductoList2().add(p);

        productoRepository.save(p);
        categoriaRepository.save(c);
        usuarioRepository.save(seller);
    }

    public void modificarProducto(String strId, String nombre, String descripcion, String foto, java.sql.Date date, String categoria, String precio) {

        Producto p = productoRepository.getById(Integer.parseInt(strId));
        Categoria anteriorCategoria = p.getCategoria();
        Categoria c = categoriaRepository.findByName(categoria);

        anteriorCategoria.getProductoList().remove(p);
        categoriaRepository.save(anteriorCategoria);

        rellenarProducto(p,nombre,descripcion,foto,date,categoria,precio);
        c.getProductoList().add(p);

        categoriaRepository.save(c);
        productoRepository.save(p);
    }

    public void borrarProducto(int id) {

        Producto p = productoRepository.getById(id);
        productoRepository.delete(p);
    }

    public void modificarPuja(int id, Date d) {

        Producto p = productoRepository.getById(id);

        if(p.getEnPuja()==0){
            p.setEnPuja((short) 1);
        }
        p.setFinPuja(d);

        productoRepository.save(p);
    }

    public void modificarPuja(int id, Double precio, Date d) {

        Producto p = productoRepository.getById(id);

        if(p.getEnPuja()==0){
            p.setEnPuja((short) 1);
            p.setPrecioSalida(precio);
        }
        p.setFinPuja(d);

        //this.grupoService.notificarComienzoPuja("Grupo_"+p.getId(), p.toDTO());
        productoRepository.save(p);
    }

    public void quitarPuja(String id) {

        Producto p = this.productoRepository.getById(Integer.parseInt(id));
        p.getPujaList().clear();
        for(Puja pu : p.getPujaList()){
            pujaRepository.delete(pu);

        }
        p.setEnPuja((short) 0);
        productoRepository.save(p);
    }

    public void finalizarPuja(String id) {

        Producto p = productoRepository.getById(Integer.parseInt(id));

        //this.grupoService.notificarFinPuja("Grupo_"+p.getId(), p.toDTO());

        Double d=p.getPrecioSalida();
        Puja puja=null;


        if(!p.getPujaList().isEmpty()){

            puja = pujaRepository.findMax(p.getId());
            Usuario comprador =puja.getUsuario();
            List<Puja> pujasPerdedoras = productoRepository.findLosers(Integer.parseInt(id), puja.getPujaPK());


            p.setEnPuja((short) 0);
            p.setComprador(puja.getUsuario());
            comprador.getProductoList1().add(p);

            this.usuarioRepository.save(comprador);
            this.productoRepository.save(p);

            for(Puja pu : pujasPerdedoras){
                sumarSaldo(pu.getPujaPK().getPrecio(),pu.getUsuario());
            }

        }else{
            p.setEnPuja((short) 0);
            this.productoRepository.save(p);

        }

    }

    private void sumarSaldo(double cantidad, Usuario user){ //Galo

        double saldo = user.getSaldo();
        saldo += cantidad;
        user.setSaldo(saldo);

        usuarioRepository.save(user);
    }
}
