package es.taw.swishbay.controller;

import es.taw.swishbay.dto.UsuarioDTO;
import es.taw.swishbay.service.GrupoService;
import es.taw.swishbay.service.UsuarioCompradorService;
import es.taw.swishbay.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UsuarioCompradorVendedorController extends SwishBayController{

    private UsuarioCompradorService usuarioCompradorService;
    private GrupoService grupoService;
    private UsuarioService usuarioService;

    @Autowired
    public void setUsuarioCompradorService(UsuarioCompradorService usuarioCompradorService){
        this.usuarioCompradorService = usuarioCompradorService;
    }

    @Autowired
    public void setGrupoService(GrupoService grupoService){
        this.grupoService = grupoService;
    }

    @Autowired
    public void setUsuarioService(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
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

    @GetMapping("/usuariosCompradorVendedorDeUnGrupo/{id}")
    public String lisarUsuariosCompradorVendedorDeUngrupo(@PathVariable("id") Integer id, Model model, HttpSession session){

        if(!super.comprobarMarketingSession(session)){
            return super.redirectComprobarMarketingSession(session);
        }

        List<UsuarioDTO> usuarios = this.grupoService.listarUsuariosDeUnGrupo(id);

        model.addAttribute("usuarios", usuarios);
        model.addAttribute("idGrupo", id);

        return "participantesGrupo";

    }

    @GetMapping("/borrarUsuarioCompradorVendedorDeUnGrupo/{id}/{idUsuario}")
    public String borrarUsuarioCompradorVendedorDeUngrupo(@PathVariable("id") Integer id, @PathVariable("idUsuario") Integer idUsuario, HttpSession session){

        if(!super.comprobarMarketingSession(session)){
            return super.redirectComprobarMarketingSession(session);
        }

        this.grupoService.eliminarUsuarioAListaUsuariosGrupo(idUsuario, id);
        this.usuarioService.eliminarGrupoAListaGruposUsuario(idUsuario, id);

        return "redirect:/usuariosCompradorVendedorDeUnGrupo/"+id;

    }

}
