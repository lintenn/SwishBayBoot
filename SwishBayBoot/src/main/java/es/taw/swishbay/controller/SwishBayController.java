package es.taw.swishbay.controller;

import es.taw.swishbay.dto.UsuarioDTO;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

/**
 * @author Luis 60%, Galo 20%, angel 20%
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

    protected boolean comprobarMarketingSession(HttpSession session) { // angel
        UsuarioDTO user = (UsuarioDTO)session.getAttribute("usuario");

        return user != null && user.getRol().getNombre().equals("marketing");
    }

    protected String redirectComprobarCompradorVendedorSession(HttpSession session) { // Galo
        UsuarioDTO user = (UsuarioDTO)session.getAttribute("usuario");

        if (user == null) {
            return "redirect:/";
        } else if (user.getRol().getNombre().equals("administrador")) {
            return "redirect:/admin/usuarios";
        } else if (user.getRol().getNombre().equals("marketing")) {
            return "redirect:/usuariosCompradorVendedor";
        } else {
            return "";
        }
    }

    protected String redirectComprobarAdminSession(HttpSession session) { // Luis
        UsuarioDTO user = (UsuarioDTO)session.getAttribute("usuario");

        if (user == null) {
            return "redirect:/";
        } else if (user.getRol().getNombre().equals("compradorvendedor")) {
            return "redirect:/comprador/productos";
        } else if (user.getRol().getNombre().equals("marketing")) {
            return "redirect:/usuariosCompradorVendedor";
        } else {
            return "";
        }
    }

    protected String redirectComprobarMarketingSession(HttpSession session) { // angel
        UsuarioDTO user = (UsuarioDTO)session.getAttribute("usuario");

        if (user == null) {
            return "redirect:/";
        } else if (user.getRol().getNombre().equals("compradorvendedor")) {
            return "redirect:/comprador/productos";
        } else if (user.getRol().getNombre().equals("administrador")) {
            return "redirect:/admin/usuarios";
        } else {
            return "";
        }
    }

}
