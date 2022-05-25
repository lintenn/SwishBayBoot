package es.taw.swishbay.controller;

import es.taw.swishbay.dto.UsuarioDTO;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

/**
 * @author Luis
 */

@Controller
public abstract class SwishBayController {

    protected boolean comprobarSession(HttpSession session) { // Luis
        UsuarioDTO user = (UsuarioDTO)session.getAttribute("usuario");

        return user != null;
    }

    protected String redirectComprobarSession(HttpSession session) { // Luis
        UsuarioDTO user = (UsuarioDTO)session.getAttribute("usuario");

        if (user == null) {
            return "redirect:/";
        } else {
            return "";
        }
    }

    protected boolean comprobarAdminSession(HttpSession session) { // Luis
        UsuarioDTO user = (UsuarioDTO)session.getAttribute("usuario");

        return user != null && user.getRol().getNombre().equals("administrador");
    }

    protected boolean comprobarCompradorVendedorSession(HttpSession session) { // Galo
        UsuarioDTO user = (UsuarioDTO)session.getAttribute("usuario");

        return user != null && user.getRol().getNombre().equals("compradorvendedor");
    }

    protected String redirectComprobarAdminSession(HttpSession session) { // Luis
        UsuarioDTO user = (UsuarioDTO)session.getAttribute("usuario");

        if (user == null) {
            return "redirect:/";
        } else if (user.getRol().getNombre().equals("compradorvendedor")) {
            return "redirect:/";
        } else if (user.getRol().getNombre().equals("marketing")) {
            return "redirect:/";
        } else {
            return "";
        }
    }

}
