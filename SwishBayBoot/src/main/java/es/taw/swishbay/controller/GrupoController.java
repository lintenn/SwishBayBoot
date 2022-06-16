package es.taw.swishbay.controller;

import es.taw.swishbay.dto.GrupoDTO;
import es.taw.swishbay.dto.UsuarioDTO;
import es.taw.swishbay.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GrupoController extends SwishBayController{

    private GrupoService grupoService;

    @Autowired
    public void setGrupoService(GrupoService grupoService){
        this.grupoService = grupoService;
    }

    @GetMapping("/grupos")
    public String listarGrupos(Model model, HttpSession session) {

        if (!super.comprobarMarketingSession(session)) {
            return super.redirectComprobarMarketingSession(session);
        }

        List<GrupoDTO> grupos = this.grupoService.buscarTodosGrupos();

        model.addAttribute("grupos", grupos);

        return "grupos";
    }

    @GetMapping("/crearEditarGrupo/{id}")
    public String editarGrupo(@PathVariable("id") Integer id, Model model, HttpSession session) {

        if (!super.comprobarMarketingSession(session)) {
            return super.redirectComprobarMarketingSession(session);
        }

        GrupoDTO grupo = this.grupoService.buscarGrupoDTO(id);

        model.addAttribute("grupo", grupo);

        return "crearEditarGrupo";
    }

    @GetMapping("/crearEditarGrupo")
    public String crearGrupo(Model model, HttpSession session) {

        if (!super.comprobarMarketingSession(session)) {
            return super.redirectComprobarMarketingSession(session);
        }

        GrupoDTO grupo = new GrupoDTO();

        model.addAttribute("grupo", grupo);

        return "crearEditarGrupo";
    }

    @PostMapping("/guardarGrupo/{id}")
    public String guardarGrupo(@PathVariable("id") Integer id, @ModelAttribute("grupo") GrupoDTO grupo, Model model, HttpSession session) {

        if (!super.comprobarMarketingSession(session)) {
            return super.redirectComprobarMarketingSession(session);
        }
        UsuarioDTO user = (UsuarioDTO)session.getAttribute("usuario");

        if(id == 0){
            grupoService.crearGrupo(grupo.getNombre(), user.getId());
        } else {
            grupoService.editarGrupo(id, grupo.getNombre(), user.getId());
        }

        return "redirect:/grupos";
    }

}
