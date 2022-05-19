package es.taw.swishbay.controller;

import es.taw.swishbay.dto.CategoriaDTO;
import es.taw.swishbay.dto.UsuarioDTO;
import es.taw.swishbay.service.CategoriaService;
import es.taw.swishbay.service.SellerService;
import es.taw.swishbay.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProductosVendidosController {

    private CategoriaService categoriaService;
    private SellerService sellerService;
    private UsuarioService usuarioService;

    @Autowired
    public void setCategoriaService(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @Autowired
    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @Autowired
    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/misProductosVendidos")
    public String listar (Model model, HttpSession session) {

        //if (super.comprobarCompradorVendedorSession(request, response)) {
        //UsuarioDTO user = (UsuarioDTO)session.getAttribute("usuario"); quitar usuarioService despues
        UsuarioDTO user = usuarioService.buscarUsuario(4);

        List<CategoriaDTO> categorias= categoriaService.listarCategorias();
        List<Object[]> productos = sellerService.listarTodosVendidos(user);

        model.addAttribute("usuario", user);
        model.addAttribute("categorias", categorias);
        model.addAttribute("productos", productos);

        return "productosVendidos";
        //}
    }

    @PostMapping("/buscarMisProductosVendidos")
    public String listarBuscados (Model model, HttpSession session, @RequestParam("filtro") String filtroNombre, @RequestParam("filtroCategoria") String filtroCategoria) {

        //if (super.comprobarCompradorVendedorSession(request, response)) {
        //UsuarioDTO user = (UsuarioDTO)sesion.getAttribute("usuario");
        UsuarioDTO user = usuarioService.buscarUsuario(4);

        List<CategoriaDTO> categorias= categoriaService.listarCategorias();

        List<Object[]> productos = sellerService.listarVendidos(user, filtroNombre, filtroCategoria);

        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categorias);
        model.addAttribute("selected", filtroCategoria);
        model.addAttribute("user", user);

        return "productosVendidos";
        //}
    }

}
