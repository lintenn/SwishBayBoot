package es.taw.swishbay.controller;

import es.taw.swishbay.dao.UsuarioRepository;
import es.taw.swishbay.entity.Usuario;
import es.taw.swishbay.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PruebaController {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/Usuarios")
    public String inicio (Model model, HttpSession sesion) {

        List<Usuario> lista = this.usuarioRepository.findAll();
        model.addAttribute("usuarios", lista);


        return "usuarios";
    }
}
