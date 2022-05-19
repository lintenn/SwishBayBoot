package es.taw.swishbay.controller;

import es.taw.swishbay.dto.CategoriaDTO;
import es.taw.swishbay.dto.ProductoDTO;
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
import java.util.Collections;
import java.util.List;

@Controller
public class PujasController {

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

    @GetMapping("/misProductosEnPuja")
    public String listar (Model model, HttpSession session) {

        //if (super.comprobarCompradorVendedorSession(request, response)) {
        //UsuarioDTO user = (UsuarioDTO)session.getAttribute("usuario"); quitar usuarioService despues
        UsuarioDTO user = usuarioService.buscarUsuario(4);

        List<CategoriaDTO> categorias= categoriaService.listarCategorias();
        List<Object[]> productos = sellerService.listarTodosEnPuja(user);

        model.addAttribute("usuario", user);
        model.addAttribute("categorias", categorias);
        model.addAttribute("productos", productos);

        return "pujas";
        //}
    }

    @PostMapping("/buscarMisProductosEnPuja")
    public String listarBuscados (Model model, HttpSession session, @RequestParam("filtro") String filtroNombre, @RequestParam("filtroCategoria") String filtroCategoria) {

        //if (super.comprobarCompradorVendedorSession(request, response)) {
        //UsuarioDTO user = (UsuarioDTO)sesion.getAttribute("usuario");
        UsuarioDTO user = usuarioService.buscarUsuario(4);

        List<CategoriaDTO> categorias= categoriaService.listarCategorias();

        List<Object[]> productos = sellerService.listarEnPuja(user, filtroNombre, filtroCategoria);

        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categorias);
        model.addAttribute("selected", filtroCategoria);
        model.addAttribute("user", user);

        return "pujas";
        //}
    }

}
