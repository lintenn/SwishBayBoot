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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    /*@GetMapping("/SellerController")
    public String inicio (Model model, HttpSession sesion) {

        List<Usuario> lista = this.usuarioService.findAll();
        model.addAttribute("usuarios", lista);


        return "usuarios";
    }*/

    @PostMapping("/SellerController")
    public String inicio (Model model, HttpSession sesion, String filtroNombre, String filtroCategoria, String desde, String hasta) {

        UsuarioDTO user = (UsuarioDTO)sesion.getAttribute("usuario");

        List<CategoriaDTO> categorias = categoriaService.listarCategorias();

        if(desde!=null && (Double.parseDouble(desde)> Double.parseDouble(hasta)))
            desde="0";

        List<ProductoDTO> productos = sellerService.listarProductos(user, filtroNombre, filtroCategoria, desde, hasta);

        Collections.reverse(productos);
        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categorias);
        model.addAttribute("filtroCategoria", filtroCategoria);
        model.addAttribute("desdeSelected", desde);
        model.addAttribute("hastaSelected", hasta);
        model.addAttribute("usuario", user);

        return "seller";
    }

}
