package es.taw.swishbay.controller;

import es.taw.swishbay.dto.UsuarioDTO;
import es.taw.swishbay.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Controlador del login.
 * @author Luis
 */

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String doInit() {
        return "index";
    }

    @GetMapping("/login")
    public String doLogin() {
        return "login";
    }

    @PostMapping("/autentica")
    public String doAutentica(Model model, @RequestParam("correo") String correo, @RequestParam("password") String password, HttpSession session) {
        String status = null, goTo = "compradorProductosServlet";

        UsuarioDTO user = this.usuarioService.comprobarCredenciales(correo, password);

        if (user == null) {
            status = "El correo o la clave son incorrectos";
            model.addAttribute("status", status);
            return "login";
        } else {
            session.setAttribute("usuario", user);

            if (user.getRol().getNombre().equals("administrador")) {
                goTo = "usuarios";
            } else if (user.getRol().getNombre().equals("compradorvendedor")) {
                goTo = "compradorProductosServlet";
            } else if (user.getRol().getNombre().equals("marketing")) {
                goTo = "usuarioCompradorServlet";
            }

            return "redirect:/" + goTo;
        }
    }

    @GetMapping("/logout")
    public String doLogout(HttpSession session) {
        session.removeAttribute("usuario");
        return "redirect:/";
    }

}
