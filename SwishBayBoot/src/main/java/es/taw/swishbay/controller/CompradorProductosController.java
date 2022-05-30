package es.taw.swishbay.controller;

import es.taw.swishbay.dto.*;
import es.taw.swishbay.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;

/**
 * Este controller recupera todos los diferentes productos de la tienda.
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

        CompradorDTO comprador = new CompradorDTO();
        comprador.setUsuario((UsuarioDTO) session.getAttribute("usuario"));
        setFiltros(comprador,"", "", null);
        comprador.setProductos(compradorService.listarProductosExistentes(comprador.getFiltroTitulo(), comprador.getFiltroCategoria(),
                                                                          comprador.getFiltroPrecio(), comprador.getUsuario().getId()));
        setAttributes(comprador, null);
        comprador.setMapping("/productos");

        addAttributes(comprador, model, session);

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

        CompradorDTO comprador = new CompradorDTO();
        comprador.setUsuario((UsuarioDTO) session.getAttribute("usuario"));
        setFiltros(comprador, filtroTitulo, filtroCategoria, filtroPrecio);
        comprador.setProductos(compradorService.listarProductosExistentes(comprador.getFiltroTitulo(), comprador.getFiltroCategoria(),
                                                                          comprador.getFiltroPrecio(), comprador.getUsuario().getId()));
        setAttributes(comprador, precioMaximo);
        comprador.setMapping("/productos");

        addAttributes(comprador, model, session);

        return "compradorProductos";
    }

    @GetMapping("/enPuja")
    public String doListarEnPujaGET(Model model, HttpSession session){

        if (!super.comprobarCompradorVendedorSession(session)) {
            return super.redirectComprobarCompradorVendedorSession(session);
        }

        CompradorDTO comprador = new CompradorDTO();
        comprador.setUsuario((UsuarioDTO) session.getAttribute("usuario"));
        setFiltros(comprador, "", "", null);
        comprador.setProductos(compradorService.listarProductosEnPuja(comprador.getFiltroTitulo(), comprador.getFiltroCategoria(),
                                                                      comprador.getFiltroPrecio(), comprador.getUsuario().getId()));
        setAttributes(comprador, null);
        comprador.setMapping("/enPuja");

        addAttributes(comprador, model, session);

        return "compradorProductosEnPuja";
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

        CompradorDTO comprador = new CompradorDTO();
        comprador.setUsuario((UsuarioDTO) session.getAttribute("usuario"));
        setFiltros(comprador, filtroTitulo, filtroCategoria, filtroPrecio);
        comprador.setProductos(compradorService.listarProductosEnPuja(comprador.getFiltroTitulo(), comprador.getFiltroCategoria(),
                                                                      comprador.getFiltroPrecio(), comprador.getUsuario().getId()));
        setAttributes(comprador, precioMaximo);
        comprador.setMapping("/enPuja");

        addAttributes(comprador, model, session);

        return "compradorProductosEnPuja";
    }

    @GetMapping("/favoritos")
    public String doListarFavoritosGET(Model model, HttpSession session){

        if (!super.comprobarCompradorVendedorSession(session)) {
            return super.redirectComprobarCompradorVendedorSession(session);
        }

        CompradorDTO comprador = new CompradorDTO();
        comprador.setUsuario((UsuarioDTO) session.getAttribute("usuario"));
        setFiltros(comprador, "", "", null);
        comprador.setProductos(compradorService.listarProductosFavoritos(comprador.getFiltroTitulo(), comprador.getFiltroCategoria(),
                                                                         comprador.getFiltroPrecio(), comprador.getUsuario().getId()));
        setAttributes(comprador, null);
        comprador.setMapping("/favoritos");

        addAttributes(comprador, model, session);

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

        CompradorDTO comprador = new CompradorDTO();
        comprador.setUsuario((UsuarioDTO) session.getAttribute("usuario"));
        setFiltros(comprador, filtroTitulo, filtroCategoria, filtroPrecio);
        comprador.setProductos(compradorService.listarProductosFavoritos(comprador.getFiltroTitulo(), comprador.getFiltroCategoria(),
                                                                         comprador.getFiltroPrecio(), comprador.getUsuario().getId()));
        setAttributes(comprador, precioMaximo);
        comprador.setMapping("/favoritos");

        addAttributes(comprador, model, session);

        return "compradorProductos";
    }

    @GetMapping("/comprados")
    public String doListarCompradosGET(Model model, HttpSession session){

        if (!super.comprobarCompradorVendedorSession(session)) {
            return super.redirectComprobarCompradorVendedorSession(session);
        }

        CompradorDTO comprador = new CompradorDTO();
        comprador.setUsuario((UsuarioDTO) session.getAttribute("usuario"));
        setFiltros(comprador, "", "", null);
        comprador.setProductos(compradorService.listarProductosComprados(comprador.getFiltroTitulo(), comprador.getFiltroCategoria(),
                                                                         comprador.getFiltroPrecio(), comprador.getUsuario().getId()));
        setAttributes(comprador, null);
        comprador.setMapping("/comprados");

        addAttributes(comprador, model, session);

        return "compradorProductosComprados";
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

        CompradorDTO comprador = new CompradorDTO();
        comprador.setUsuario((UsuarioDTO) session.getAttribute("usuario"));
        setFiltros(comprador, filtroTitulo, filtroCategoria, filtroPrecio);
        comprador.setProductos(compradorService.listarProductosComprados(comprador.getFiltroTitulo(), comprador.getFiltroCategoria(),
                                                                         comprador.getFiltroPrecio(), comprador.getUsuario().getId()));
        setAttributes(comprador, precioMaximo);
        comprador.setMapping("/comprados");

        addAttributes(comprador, model, session);

        return "compradorProductosComprados";
    }

    private void setFiltros(CompradorDTO comprador, String filtroTitulo, String filtroCategoria, Double filtroPrecio){

        if(filtroTitulo == null || filtroTitulo.isEmpty() || filtroTitulo.trim().length() <= 0 ){
            comprador.setFiltroTitulo("");
        }else{
            comprador.setFiltroTitulo(filtroTitulo);
        }

        if(filtroCategoria == null || filtroCategoria.equals("Categoria")){
            comprador.setFiltroCategoria("");
        }else{
            comprador.setFiltroCategoria(filtroCategoria);
        }

        if(filtroPrecio == null){
            comprador.setFiltroPrecio(Double.MAX_VALUE);
        }else{
            comprador.setFiltroPrecio(filtroPrecio);
        }
    }

    public void setAttributes(CompradorDTO comprador, String precioMaximo){
        comprador.setCategorias(categoriaService.listarCategorias());
        comprador.setMayoresPujas(pujaService.buscarMayoresPujas(comprador.getProductos()));

        if(precioMaximo == null){
            comprador.setMayorPrecio(productoService.obtenerMayorPrecio(comprador.getProductos()));
        }else{
            comprador.setMayorPrecio(Double.parseDouble(precioMaximo));
        }
    }

    private void addAttributes(CompradorDTO comprador, Model model, HttpSession session){
        model.addAttribute("productos", comprador.getProductos());
        model.addAttribute("categorias", comprador.getCategorias());
        model.addAttribute("mayoresPujas", comprador.getMayoresPujas());
        model.addAttribute("mayorPrecio", comprador.getMayorPrecio());
        model.addAttribute("filtroTitulo", comprador.getFiltroTitulo());
        model.addAttribute("precio", comprador.getFiltroPrecio());
        model.addAttribute("selected", comprador.getFiltroCategoria());
        model.addAttribute("goTo", comprador.getMapping());
        session.setAttribute("goTo", comprador.getMapping());

        comprador.setUsuario(usuarioService.buscarUsuario(comprador.getUsuario().getId()));
        session.setAttribute("usuario", comprador.getUsuario());
    }

}