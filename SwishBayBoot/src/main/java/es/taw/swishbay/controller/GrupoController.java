package es.taw.swishbay.controller;

import es.taw.swishbay.dto.GrupoDTO;
import es.taw.swishbay.dto.GrupoFiltroDTO;
import es.taw.swishbay.dto.MensajeFiltroDTO;
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

/**
 * Controlador de los grupos
 *
 * @author angel
 */

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

        GrupoFiltroDTO filtro = new GrupoFiltroDTO();

        model.addAttribute("filtro", filtro);
        model.addAttribute("grupos", grupos);

        return "grupos";
    }

    @PostMapping("/grupos")
    public String listarGruposFiltrados(@ModelAttribute("filtro") GrupoFiltroDTO filtro, Model model, HttpSession session) {

        if (!super.comprobarMarketingSession(session)) {
            return super.redirectComprobarMarketingSession(session);
        }

        List<GrupoDTO> grupos = this.grupoService.buscarTodosGrupos();

        if(filtro.getNombreGrupo() != null && !filtro.getNombreGrupo().isEmpty() && grupos.size() > 0){

            List<Integer> ids = new ArrayList<>();
            for(GrupoDTO grupo : grupos){
                ids.add(grupo.getId());
            }

            grupos = this.grupoService.buscarGruposPorNombreYGrupos(filtro.getNombreGrupo(), ids);

        }

        if(((filtro.getApellidoCreador() != null && !filtro.getApellidoCreador().isEmpty()) || (filtro.getNombreCreador() != null && !filtro.getNombreCreador().isEmpty())) && grupos.size() > 0){

            List<Integer> ids = new ArrayList<>();
            for(GrupoDTO grupo : grupos){
                ids.add(grupo.getId());
            }

            if((filtro.getNombreCreador() == null || filtro.getNombreCreador().isEmpty()) && (filtro.getApellidoCreador() != null && !filtro.getApellidoCreador().isEmpty())){
                grupos = this.grupoService.buscarGruposPorApellidosCreador(filtro.getApellidoCreador(), ids);
            } else if((filtro.getApellidoCreador() == null || filtro.getApellidoCreador().isEmpty()) && (filtro.getNombreCreador() != null && !filtro.getNombreCreador().isEmpty())){
                grupos = this.grupoService.buscarGruposPorNombreCreador(filtro.getNombreCreador(), ids);
            } else {
                grupos = this.grupoService.buscarGruposPorNombreYApellidosCreador(filtro.getNombreCreador(), filtro.getApellidoCreador(), ids);
            }


        }

        model.addAttribute("filtro", filtro);
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

    @GetMapping("/borrarGrupo/{id}")
    public String borrarGrupo(@PathVariable("id") Integer id, Model model, HttpSession session){

        if (!super.comprobarMarketingSession(session)) {
            return super.redirectComprobarMarketingSession(session);
        }

        this.grupoService.borrarGrupo(id);

        return "redirect:/grupos";
    }

}
