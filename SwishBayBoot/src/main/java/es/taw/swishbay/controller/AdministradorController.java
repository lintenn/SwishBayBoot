package es.taw.swishbay.controller;

import es.taw.swishbay.dto.RolUsuarioDTO;
import es.taw.swishbay.dto.UsuarioDTO;
import es.taw.swishbay.service.RolUsuarioService;
import es.taw.swishbay.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdministradorController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolUsuarioService rolUsuarioService;

    @RequestMapping(value = "/usuarios", method = { RequestMethod.GET, RequestMethod.POST })
    public String listarUsuarios(Model model, @RequestParam(value = "filtro", required = false) String filtroNombre, @RequestParam(value = "filtroRol", required = false) String filtroRol) {

        List<RolUsuarioDTO> roles = this.rolUsuarioService.listarRoles();

        List<UsuarioDTO> usuarios = this.usuarioService.listarUsuarios(filtroNombre, filtroRol);

        model.addAttribute("usuarios", usuarios);
        model.addAttribute("roles", roles);
        model.addAttribute("selected", filtroRol);

        return "usuarios";
    }

}
