package es.taw.swishbay.controller;

import es.taw.swishbay.dto.*;
import es.taw.swishbay.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Este controller recupera todos productos de la tienda, aplicando diferentes filtros.
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

    @GetMapping("/productos")
    public String doListarDisponibles(Model model, HttpSession session){

        if (!super.comprobarCompradorVendedorSession(session)) {
            return super.redirectComprobarCompradorVendedorSession(session);
        }

        UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
        List<ProductoDTO> productos = compradorService.listarProductosExistentes(usuario.getId());
        CompradorFiltroDTO filtro = new CompradorFiltroDTO(productoService.obtenerMayorPrecio(productos), "/comprador/productos");

        model.addAttribute("usuario", usuario);
        model.addAttribute("productos", productos);
        model.addAttribute("filtro", filtro);

        addAttributesCategoriasAndMayoresPujas(model, productos);
        setGoTo(filtro, session);

        return "compradorProductos";
    }

    @PostMapping("/productos")
    public String doListarDisponiblesByFiltro(@ModelAttribute("filtro") CompradorFiltroDTO filtro, Model model, HttpSession session){

        if (!super.comprobarCompradorVendedorSession(session)) {
            return super.redirectComprobarCompradorVendedorSession(session);
        }

        UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
        checkFiltroTitulo(filtro);
        List<ProductoDTO> productos = compradorService.listarProductosExistentes(filtro.getFiltroTitulo(),
                filtro.getFiltroCategoria(), filtro.getFiltroPrecio(), usuario.getId());

        model.addAttribute("usuario", usuario);
        model.addAttribute("productos", productos);
        model.addAttribute("filtro", filtro);

        addAttributesCategoriasAndMayoresPujas(model, productos);
        setGoTo(filtro, session);

        return "compradorProductos";
    }

    @GetMapping("/enPuja")
    public String doListarEnPuja(Model model, HttpSession session){

        if (!super.comprobarCompradorVendedorSession(session)) {
            return super.redirectComprobarCompradorVendedorSession(session);
        }

        UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
        List<ProductoDTO> productos = compradorService.listarProductosEnPuja(usuario.getId());
        CompradorFiltroDTO filtro = new CompradorFiltroDTO(productoService.obtenerMayorPrecio(productos), "/comprador/enPuja");

        model.addAttribute("usuario", usuario);
        model.addAttribute("productos", productos);
        model.addAttribute("filtro", filtro);

        addAttributesCategoriasAndMayoresPujas(model, productos);
        setGoTo(filtro, session);

        return "compradorProductosEnPuja";
    }

    @PostMapping("/enPuja")
    public String doListarEnPujaByFiltro(@ModelAttribute("filtro") CompradorFiltroDTO filtro, Model model, HttpSession session){

        if (!super.comprobarCompradorVendedorSession(session)) {
            return super.redirectComprobarCompradorVendedorSession(session);
        }

        UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
        checkFiltroTitulo(filtro);
        List<ProductoDTO> productos = compradorService.listarProductosEnPuja(filtro.getFiltroTitulo(),
                filtro.getFiltroCategoria(), filtro.getFiltroPrecio(), usuario.getId());

        model.addAttribute("usuario", usuario);
        model.addAttribute("productos", productos);
        model.addAttribute("filtro", filtro);

        addAttributesCategoriasAndMayoresPujas(model, productos);
        setGoTo(filtro, session);

        return "compradorProductosEnPuja";
    }

    @GetMapping("/favoritos")
    public String doListarFavoritos(Model model, HttpSession session){

        if (!super.comprobarCompradorVendedorSession(session)) {
            return super.redirectComprobarCompradorVendedorSession(session);
        }

        UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
        List<ProductoDTO> productos = compradorService.listarProductosFavoritos(usuario.getId());
        CompradorFiltroDTO filtro = new CompradorFiltroDTO(productoService.obtenerMayorPrecio(productos), "/comprador/favoritos");

        model.addAttribute("usuario", usuario);
        model.addAttribute("productos", productos);
        model.addAttribute("filtro", filtro);

        addAttributesCategoriasAndMayoresPujas(model, productos);
        setGoTo(filtro, session);

        return "compradorProductos";
    }

    @PostMapping("/favoritos")
    public String listarFavoritosByFiltro(@ModelAttribute("filtro") CompradorFiltroDTO filtro, Model model, HttpSession session){

        if (!super.comprobarCompradorVendedorSession(session)) {
            return super.redirectComprobarCompradorVendedorSession(session);
        }

        UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
        checkFiltroTitulo(filtro);
        List<ProductoDTO> productos = compradorService.listarProductosFavoritos(filtro.getFiltroTitulo(),
                filtro.getFiltroCategoria(), filtro.getFiltroPrecio(), usuario.getId());

        model.addAttribute("usuario", usuario);
        model.addAttribute("productos", productos);
        model.addAttribute("filtro", filtro);

        addAttributesCategoriasAndMayoresPujas(model, productos);
        setGoTo(filtro, session);

        return "compradorProductos";
    }

    @GetMapping("/comprados")
    public String doListarComprados(Model model, HttpSession session){

        if (!super.comprobarCompradorVendedorSession(session)) {
            return super.redirectComprobarCompradorVendedorSession(session);
        }

        UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
        List<ProductoDTO> productos = compradorService.listarProductosComprados(usuario.getId());
        CompradorFiltroDTO filtro = new CompradorFiltroDTO(productoService.obtenerMayorPrecio(productos), "/comprador/comprados");

        model.addAttribute("usuario", usuario);
        model.addAttribute("productos", productos);
        model.addAttribute("filtro", filtro);

        addAttributesCategoriasAndMayoresPujas(model, productos);
        setGoTo(filtro, session);

        return "compradorProductosComprados";
    }

    @PostMapping("/comprados")
    public String doListarCompradosByFiltro(@ModelAttribute("filtro") CompradorFiltroDTO filtro, Model model, HttpSession session){

        if (!super.comprobarCompradorVendedorSession(session)) {
            return super.redirectComprobarCompradorVendedorSession(session);
        }

        UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
        checkFiltroTitulo(filtro);
        List<ProductoDTO> productos = compradorService.listarProductosComprados(filtro.getFiltroTitulo(),
                filtro.getFiltroCategoria(), filtro.getFiltroPrecio(), usuario.getId());

        model.addAttribute("usuario", usuario);
        model.addAttribute("productos", productos);
        model.addAttribute("filtro", filtro);

        addAttributesCategoriasAndMayoresPujas(model, productos);
        setGoTo(filtro, session);

        return "compradorProductosComprados";
    }

    private void addAttributesCategoriasAndMayoresPujas(Model model, List<ProductoDTO> productos){
        List<PujaDTO> mayoresPujas = pujaService.buscarMayoresPujas(productos);
        List<CategoriaDTO> categorias = categoriaService.listarCategorias();

        model.addAttribute("mayoresPujas", mayoresPujas);
        model.addAttribute("categorias", categorias);
    }

    private void checkFiltroTitulo(CompradorFiltroDTO filtro){
        if(filtro.getFiltroTitulo().trim().length() <= 0 ){
            filtro.setFiltroTitulo("");
        }
    }

    private void setGoTo(CompradorFiltroDTO filtro, HttpSession session){
        session.setAttribute("goTo", filtro.getGoTo());
    }
}