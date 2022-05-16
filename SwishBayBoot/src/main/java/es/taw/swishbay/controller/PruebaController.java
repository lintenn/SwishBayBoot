package es.taw.swishbay.controller;

import es.taw.swishbay.dao.UsuarioRepository;
import es.taw.swishbay.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PruebaController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/Usuarios")
    public String inicio (Model model, HttpSession sesion) {

        List<Usuario> lista = this.usuarioRepository.findAll();
        model.addAttribute("usuarios", lista);


        return "usuarios";
    }
}
