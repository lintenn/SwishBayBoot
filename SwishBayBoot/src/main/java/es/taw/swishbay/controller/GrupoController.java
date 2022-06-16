package es.taw.swishbay.controller;

import es.taw.swishbay.dto.GrupoDTO;
import es.taw.swishbay.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
