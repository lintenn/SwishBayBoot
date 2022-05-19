package es.taw.swishbay.controller;

import es.taw.swishbay.dao.UsuarioRepository;
import es.taw.swishbay.dto.CategoriaDTO;
import es.taw.swishbay.dto.ProductoDTO;
import es.taw.swishbay.dto.UsuarioDTO;
import es.taw.swishbay.entity.Usuario;
import es.taw.swishbay.service.CategoriaService;
import es.taw.swishbay.service.SellerService;
import es.taw.swishbay.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@Controller
public class SellerController {

    private UsuarioService usuarioService;
    private CategoriaService categoriaService;
    private SellerService sellerService;

    @Autowired
    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Autowired
    public void setCategoriaService(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @Autowired
    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping("/misProductos")
    public String listar (Model model, HttpSession session) {

        //if (super.comprobarCompradorVendedorSession(request, response)) {
        //UsuarioDTO user = (UsuarioDTO)session.getAttribute("usuario");
        UsuarioDTO user = usuarioService.buscarUsuario(4);

        List<CategoriaDTO> categorias = categoriaService.listarCategorias();
        List<ProductoDTO> productos = sellerService.listarTodos(user);

        model.addAttribute("usuario", user);
        model.addAttribute("categorias", categorias);
        model.addAttribute("productos", productos);


        return "seller";
        //}
    }

    @PostMapping("/buscarMisProductos")
    public String listarBuscados (Model model, HttpSession session, @RequestParam("filtroNombre") String filtroNombre, @RequestParam("filtroCategoria") String filtroCategoria, @RequestParam("desde") String desde, @RequestParam("hasta") String hasta) {

        //if (super.comprobarCompradorVendedorSession(request, response)) {
        //UsuarioDTO user = (UsuarioDTO)sesion.getAttribute("usuario");
        UsuarioDTO user = usuarioService.buscarUsuario(4);

        List<CategoriaDTO> categorias = categoriaService.listarCategorias();

        if (desde != null && (Double.parseDouble(desde) > Double.parseDouble(hasta)))
            desde = "0";

        List<ProductoDTO> productos = sellerService.listarProductos(user, filtroNombre, filtroCategoria, desde, hasta);

        Collections.reverse(productos);
        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categorias);
        model.addAttribute("selected", filtroCategoria);
        model.addAttribute("desdeSelected", desde);
        model.addAttribute("hastaSelected", hasta);
        model.addAttribute("usuario", user);

        return "seller";
        //}
    }

}
