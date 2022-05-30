package es.taw.swishbay.controller;

import es.taw.swishbay.dto.CategoriaDTO;
import es.taw.swishbay.dto.ProductoDTO;
import es.taw.swishbay.dto.PujaDTO;
import es.taw.swishbay.dto.UsuarioDTO;
import es.taw.swishbay.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Recupera todos los productos existentes en la tienda que no sean vendidos por el usuario.
 *
 * @author Miguel Oña Guerrero
 */

@Controller
@RequestMapping("comprador")
public class CompradorProductosController extends SwishBayController{

    private CompradorService compradorService;
    private CategoriaService categoriaService;
    private PujaService pujaService;
    private ProductoService productoService;
    private UsuarioService usuarioService;

    private UsuarioDTO usuario;
    private List<ProductoDTO> productos;
    private List<CategoriaDTO> categorias;
    private List<PujaDTO> mayoresPujas;
    private String filtroTitulo;
    private String filtroCategoria;
    private Double mayorPrecio;
    private Double filtroPrecio;
    private String mapping;

    @Autowired
    public void setCompradorService(CompradorService compradorService){
        this.compradorService = compradorService;
    }

    @Autowired
    public void setCategoriaService(CategoriaService categoriaService){
        this.categoriaService = categoriaService;
    }

    @Autowired
    public void setPujaService(PujaService pujaService){
        this.pujaService = pujaService;
    }

    @Autowired
    public void setProductoService(ProductoService productoService){
        this.productoService = productoService;
    }

    @Autowired
    public void setUsuarioService(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping("/productos")
    public String doListarDisponiblesGET(Model model, HttpSession session){

        if (!super.comprobarCompradorVendedorSession(session)) {
            return super.redirectComprobarCompradorVendedorSession(session);
        }

        usuario = (UsuarioDTO) session.getAttribute("usuario");
        setFiltros("", "", null);
        productos = compradorService.listarProductosExistentes(this.filtroTitulo,this.filtroCategoria,this.filtroPrecio,usuario.getId());
        setAttributes(null);
        mapping = "/productos";

        addAttributes(model, session);

        return "compradorProductos";
    }

    @PostMapping("/productos")
    public String doListarDisponiblesPOST(@RequestParam("filtro") String filtroTitulo,
                                          @RequestParam("filtroCategoria") String filtroCategoria,
                                          @RequestParam("precioMaximo") String precioMaximo,
                                          @RequestParam("filtroPrecio") Double filtroPrecio,
                                          Model model, HttpSession session){

        if (!super.comprobarCompradorVendedorSession(session)) {
            return super.redirectComprobarCompradorVendedorSession(session);
        }

        usuario = (UsuarioDTO) session.getAttribute("usuario");
        setFiltros(filtroTitulo, filtroCategoria, filtroPrecio);
        productos = compradorService.listarProductosExistentes(this.filtroTitulo,this.filtroCategoria,this.filtroPrecio,usuario.getId());
        setAttributes(precioMaximo);
        mapping = "/productos";

        addAttributes(model, session);

        return "compradorProductos";
    }

    @GetMapping("/enPuja")
    public String doListarEnPujaGET(Model model, HttpSession session){

        if (!super.comprobarCompradorVendedorSession(session)) {
            return super.redirectComprobarCompradorVendedorSession(session);
        }

        usuario = (UsuarioDTO) session.getAttribute("usuario");
        setFiltros("", "", null);
        productos = compradorService.listarProductosEnPuja(this.filtroTitulo,this.filtroCategoria,this.filtroPrecio,usuario.getId());
        setAttributes(null);
        mapping = "/enPuja";

        addAttributes(model, session);

        return "compradorProductos";
    }

    @PostMapping("/enPuja")
    public String doListarEnPujaPOST(@RequestParam("filtro") String filtroTitulo,
                                     @RequestParam("filtroCategoria") String filtroCategoria,
                                     @RequestParam("precioMaximo") String precioMaximo,
                                     @RequestParam("filtroPrecio") Double filtroPrecio,
                                     Model model, HttpSession session){

        if (!super.comprobarCompradorVendedorSession(session)) {
            return super.redirectComprobarCompradorVendedorSession(session);
        }

        usuario = (UsuarioDTO) session.getAttribute("usuario");
        setFiltros(filtroTitulo, filtroCategoria, filtroPrecio);
        productos = compradorService.listarProductosEnPuja(this.filtroTitulo,this.filtroCategoria,this.filtroPrecio,usuario.getId());
        setAttributes(precioMaximo);
        mapping = "/enPuja";

        addAttributes(model, session);

        return "compradorProductos";
    }

    @GetMapping("/favoritos")
    public String doListarFavoritosGET(Model model, HttpSession session){

        if (!super.comprobarCompradorVendedorSession(session)) {
            return super.redirectComprobarCompradorVendedorSession(session);
        }

        usuario = (UsuarioDTO) session.getAttribute("usuario");
        setFiltros("", "", null);
        productos = compradorService.listarProductosFavoritos(this.filtroTitulo,this.filtroCategoria,this.filtroPrecio,usuario.getId());
        setAttributes(null);
        mapping = "/favoritos";

        addAttributes(model, session);

        return "compradorProductos";
    }

    @PostMapping("/favoritos")
    public String listarFavoritos(@RequestParam("filtro") String filtroTitulo,
                                  @RequestParam("filtroCategoria") String filtroCategoria,
                                  @RequestParam("precioMaximo") String precioMaximo,
                                  @RequestParam("filtroPrecio") Double filtroPrecio,
                                  Model model, HttpSession session){

        if (!super.comprobarCompradorVendedorSession(session)) {
            return super.redirectComprobarCompradorVendedorSession(session);
        }

        usuario = (UsuarioDTO) session.getAttribute("usuario");
        setFiltros(filtroTitulo, filtroCategoria, filtroPrecio);
        productos = compradorService.listarProductosFavoritos(this.filtroTitulo,this.filtroCategoria,this.filtroPrecio,usuario.getId());
        setAttributes(precioMaximo);
        mapping = "/favoritos";

        addAttributes(model, session);

        return "compradorProductos";
    }

    @GetMapping("/comprados")
    public String doListarCompradosGET(Model model, HttpSession session){

        if (!super.comprobarCompradorVendedorSession(session)) {
            return super.redirectComprobarCompradorVendedorSession(session);
        }

        usuario = (UsuarioDTO) session.getAttribute("usuario");
        setFiltros("", "", null);
        productos = compradorService.listarProductosComprados(this.filtroTitulo,this.filtroCategoria,this.filtroPrecio,usuario.getId());
        setAttributes(null);
        mapping = "/comprados";

        addAttributes(model, session);

        return "compradorProductos";
    }

    @PostMapping("/comprados")
    public String doListarCompradosPOST(@RequestParam("filtro") String filtroTitulo,
                                  @RequestParam("filtroCategoria") String filtroCategoria,
                                  @RequestParam("precioMaximo") String precioMaximo,
                                  @RequestParam("filtroPrecio") Double filtroPrecio,
                                  Model model, HttpSession session){

        if (!super.comprobarCompradorVendedorSession(session)) {
            return super.redirectComprobarCompradorVendedorSession(session);
        }

        usuario = (UsuarioDTO) session.getAttribute("usuario");
        setFiltros(filtroTitulo, filtroCategoria, filtroPrecio);
        productos = compradorService.listarProductosComprados(this.filtroTitulo,this.filtroCategoria,this.filtroPrecio,usuario.getId());
        setAttributes(precioMaximo);
        mapping = "/comprados";

        addAttributes(model, session);

        return "compradorProductos";
    }

    private void setFiltros(String filtroTitulo, String filtroCategoria, Double filtroPrecio){

        if(filtroTitulo == null || filtroTitulo.isEmpty() || filtroTitulo.trim().length() <= 0 ){
            this.filtroTitulo = "";
        }else{
            this.filtroTitulo = filtroTitulo;
        }

        if(filtroCategoria == null || filtroCategoria.equals("Categoria")){
            this.filtroCategoria = "";
        }else{
            this.filtroCategoria = filtroCategoria;
        }

        if(filtroPrecio == null){
            this.filtroPrecio = Double.MAX_VALUE;
        }else{
            this.filtroPrecio = filtroPrecio;
        }
    }

    public void setAttributes(String precioMaximo){
        this.categorias = categoriaService.listarCategorias();
        this.mayoresPujas = pujaService.buscarMayoresPujas(productos);

        if(precioMaximo == null){
            mayorPrecio = productoService.obtenerMayorPrecio(productos);
        }else{
            mayorPrecio = Double.parseDouble(precioMaximo);
        }
    }

    private void addAttributes(Model model, HttpSession session){
        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categorias);
        model.addAttribute("mayoresPujas", mayoresPujas);
        model.addAttribute("mayorPrecio", mayorPrecio);
        model.addAttribute("precio", filtroPrecio);
        model.addAttribute("selected", filtroCategoria);
        model.addAttribute("servlet", mapping);
        session.setAttribute("servlet", mapping);

        usuario = usuarioService.buscarUsuario(usuario.getId());
        session.setAttribute("usuario", usuario);
    }

}