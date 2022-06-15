package es.taw.swishbay.controller;

import es.taw.swishbay.dto.UsuarioDTO;
import es.taw.swishbay.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;

/**
 * Este controller añade y elimina productos favoritos de un comprador.
 *
 * @author Miguel Oña Guerrero
 */

@Controller
@RequestMapping("comprador")
public class CompradorFavoritosController extends SwishBayController{

    UsuarioService usuarioService;

    @Autowired
    private void setUsuarioService(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping("/favorito/{id}/{redirect}")
    public String doFavorito(@PathVariable("id")Integer idProducto, @PathVariable("redirect")Boolean redirect, HttpSession session){

        if (!super.comprobarCompradorVendedorSession(session)) {
            return super.redirectComprobarCompradorVendedorSession(session);
        }

        UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");

        usuario = usuarioService.manejoFavoritos(idProducto, usuario.getId());

        session.setAttribute("usuario", usuario);

        String goTo;
        if(redirect){
            goTo = (String) session.getAttribute("goTo");
        }else{
            goTo = (String) session.getAttribute("goToProducto");
        }

        if(goTo == null){
            goTo = "/comprador/productos";
        }

        return "redirect:" +  goTo;
    }
}