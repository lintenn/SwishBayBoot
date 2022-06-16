package es.taw.swishbay.controller;

import es.taw.swishbay.dto.GrupoDTO;
import es.taw.swishbay.dto.MensajeDTO;
import es.taw.swishbay.dto.MensajeFiltroDTO;
import es.taw.swishbay.dto.UsuarioDTO;
import es.taw.swishbay.service.MensajeService;
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
public class MensajeController extends SwishBayController {

    private MensajeService mensajeService;

    @Autowired
    public void setMensajeService(MensajeService mensajeService){
        this.mensajeService = mensajeService;
    }

    @GetMapping("/NotificacionesVerServlet/{id}")
    public String verNotificaciones(@PathVariable("id") int id, @ModelAttribute("filtro") MensajeFiltroDTO filtro, Model model, HttpSession session){

        if(!super.comprobarCompradorVendedorSession(session)){
            return super.redirectComprobarCompradorVendedorSession(session);
        }

        UsuarioDTO usuarioDTO = (UsuarioDTO) session.getAttribute("usuario");
        List<MensajeDTO> mensajeDTOList = usuarioDTO.getMensajeList();

        if(filtro.getFiltroTitulo() != null && !filtro.getFiltroTitulo().isEmpty() && mensajeDTOList != null) {

            List<Integer> ids = new ArrayList<>();
            for(MensajeDTO mensaje : mensajeDTOList){
                ids.add(mensaje.getId());
            }

            switch(filtro.getFiltroMensaje()){
                case "Asunto":
                    mensajeDTOList = this.mensajeService.listarMensajesDeUnUsuarioPorAsuntoPorMensajes(filtro.getFiltroTitulo(), usuarioDTO.getId(), ids);
                    break;
                case "Cuerpo del mensaje":
                    mensajeDTOList = this.mensajeService.listarMensajesDeUnUsuarioPorContenidoPorMensajes(filtro.getFiltroTitulo(), usuarioDTO.getId(), ids);
                    break;
            }

        }

        model.addAttribute("tipoFiltro", filtro.getFiltroMensaje());
        model.addAttribute("filtro", filtro.getFiltroTitulo());
        model.addAttribute("filtroMensaje", filtro);
        model.addAttribute("mensajes", mensajeDTOList);

        return "notificaciones";

    }

    @GetMapping("/verMensajes/{id}")
    public String listarMensajesPorGrupo(@PathVariable("id") Integer id, Model model, HttpSession session){

        if (!super.comprobarMarketingSession(session)) {
            return super.redirectComprobarMarketingSession(session);
        }

        List<MensajeDTO> mensajes = this.mensajeService.buscarMensajesPorIdGrupo(id);

        model.addAttribute("mensajes", mensajes);
        model.addAttribute("idGrupo", id);

        return "mensajesGrupo";

    }

    @GetMapping("/borrarMensajes/{id}/{idGrupo}")
    public String borrarMensaje(@PathVariable("id") Integer id, @PathVariable("idGrupo") Integer idGrupo, Model model, HttpSession session){

        if (!super.comprobarMarketingSession(session)) {
            return super.redirectComprobarMarketingSession(session);
        }

        this.mensajeService.borrarMensaje(id);

        return "redirect:/verMensajes/"+idGrupo;

    }

    @GetMapping("/crearEditarMensaje/{id}/{idGrupo}")
    public String editarMensaje(@PathVariable("id") Integer id, @PathVariable("idGrupo") Integer idGrupo, Model model, HttpSession session) {

        if (!super.comprobarMarketingSession(session)) {
            return super.redirectComprobarMarketingSession(session);
        }

        MensajeDTO mensaje = this.mensajeService.buscarMensajeDTO(id);

        model.addAttribute("mensaje", mensaje);
        model.addAttribute("idGrupo", idGrupo);

        return "crearEditarMensaje";
    }

    @GetMapping("/crearEditarMensaje/{idGrupo}")
    public String crearMensaje(@PathVariable("idGrupo") Integer idGrupo, Model model, HttpSession session) {

        if (!super.comprobarMarketingSession(session)) {
            return super.redirectComprobarMarketingSession(session);
        }

        MensajeDTO mensaje = new MensajeDTO();
        mensaje.setId(0);

        model.addAttribute("mensaje", mensaje);
        model.addAttribute("idGrupo", idGrupo);

        return "crearEditarMensaje";
    }

    @PostMapping("/guardarMensaje/{id}/{idGrupo}")
    public String guardarMensaje(@PathVariable("id") Integer id, @PathVariable("idGrupo") Integer idGrupo, @ModelAttribute("mensaje") MensajeDTO mensaje, Model model, HttpSession session) {

        if (!super.comprobarMarketingSession(session)) {
            return super.redirectComprobarMarketingSession(session);
        }
        UsuarioDTO user = (UsuarioDTO)session.getAttribute("usuario");

        if(id == 0){
            this.mensajeService.crearMensaje(idGrupo, user.getId(), mensaje.getAsunto(), mensaje.getContenido());
        } else {
            this.mensajeService.modificarMensaje(id, mensaje.getAsunto(), mensaje.getContenido());
        }

        return "redirect:/verMensajes/"+idGrupo;
    }

}
