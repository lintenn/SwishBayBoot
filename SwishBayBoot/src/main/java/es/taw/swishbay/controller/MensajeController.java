package es.taw.swishbay.controller;

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
            super.redirectComprobarCompradorVendedorSession(session);
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

}
