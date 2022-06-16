package es.taw.swishbay.controller;

import es.taw.swishbay.dto.UsuarioDTO;
import es.taw.swishbay.service.UsuarioCompradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UsuarioCompradorVendedorController extends SwishBayController{

    private UsuarioCompradorService usuarioCompradorService;

    @Autowired
    public void setUsuarioCompradorService(UsuarioCompradorService usuarioCompradorService){
        this.usuarioCompradorService = usuarioCompradorService;
    }

    @GetMapping("/usuariosCompradorVendedor")
    public String lisarUsuariosCompradorVendedor(Model model, HttpSession session){

        if(!super.comprobarMarketingSession(session)){
            return super.redirectComprobarMarketingSession(session);
        }

        List<UsuarioDTO> usuarios = this.usuarioCompradorService.buscarPorCompradorVendedor();

        model.addAttribute("usuarios", usuarios);

        return "usuariosCompradores";

    }
}
