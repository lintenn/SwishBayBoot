package es.taw.swishbay.controller;

import es.taw.swishbay.dto.GrupoFiltroDTO;
import es.taw.swishbay.dto.MensajeFiltroDTO;
import es.taw.swishbay.dto.UsuarioCompradorDTO;
import es.taw.swishbay.dto.UsuarioDTO;
import es.taw.swishbay.service.GrupoService;
import es.taw.swishbay.service.UsuarioCompradorService;
import es.taw.swishbay.service.UsuarioService;
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
    public String listarUsuariosCompradorVendedor(Model model, HttpSession session){

        if(!super.comprobarMarketingSession(session)){
            return super.redirectComprobarMarketingSession(session);
        }

        List<UsuarioDTO> usuarios = this.usuarioCompradorService.buscarPorCompradorVendedor();

        UsuarioCompradorDTO filtro = new UsuarioCompradorDTO("Nombre");

        List<UsuarioCompradorDTO> usuarioCompradorDTOS = new ArrayList<>();
        usuarioCompradorDTOS.add(new UsuarioCompradorDTO("Nombre"));
        usuarioCompradorDTOS.add(new UsuarioCompradorDTO("Correo"));
        usuarioCompradorDTOS.add(new UsuarioCompradorDTO("Apellidos"));
        usuarioCompradorDTOS.add(new UsuarioCompradorDTO("Ciudad"));
        usuarioCompradorDTOS.add(new UsuarioCompradorDTO("Domicilio"));
        usuarioCompradorDTOS.add(new UsuarioCompradorDTO("Sexo"));

        model.addAttribute("filtro", filtro);
        model.addAttribute("filtros", usuarioCompradorDTOS);
        model.addAttribute("usuarios", usuarios);

        return "usuariosCompradores";

    }

    @PostMapping("/usuariosCompradorVendedor")
    public String listarUsuariosCompradorVendedorFiltrado(@ModelAttribute("filtro") UsuarioCompradorDTO filtro, Model model, HttpSession session){

        if(!super.comprobarMarketingSession(session)){
            return super.redirectComprobarMarketingSession(session);
        }

        List<UsuarioDTO> usuarios = this.usuarioCompradorService.buscarPorCompradorVendedor();

        switch(filtro.getSeleccionado()){
            case "Nombre":
                usuarios = this.usuarioCompradorService.buscarPorCompradorVendedorPorNombre(filtro.getBusqueda());
                break;
            case "Correo":
                usuarios = this.usuarioCompradorService.buscarPorCompradorVendedorPorCorreo(filtro.getBusqueda());
                break;
            case "Apellidos":
                usuarios = this.usuarioCompradorService.buscarPorCompradorVendedorPorApellidos(filtro.getBusqueda());
                break;
            case "Ciudad":
                usuarios = this.usuarioCompradorService.buscarPorCompradorVendedorPorCiudad(filtro.getBusqueda());
                break;
            case "Domicilio":
                usuarios = this.usuarioCompradorService.buscarPorCompradorVendedorPorDomicilio(filtro.getBusqueda());
                break;
            case "Sexo":
                usuarios = this.usuarioCompradorService.buscarPorCompradorVendedorPorSexo(filtro.getBusqueda());
                break;
        }

        if(filtro.getSaldoDesde() != null && usuarios.size() > 0){

            List<Integer> ids = new ArrayList<>();
            for(UsuarioDTO user : usuarios){
                ids.add(user.getId());
            }

            usuarios = this.usuarioCompradorService.buscarPorCompradorVendedorPorSaldoDesde(filtro.getSaldoDesde(), ids);

        }

        if(filtro.getSaldoHasta() != null && usuarios.size() > 0){

            List<Integer> ids = new ArrayList<>();
            for(UsuarioDTO user : usuarios){
                ids.add(user.getId());
            }

            usuarios = this.usuarioCompradorService.buscarPorCompradorVendedorPorSaldoHasta(filtro.getSaldoHasta(), ids);

        }

        List<UsuarioCompradorDTO> usuarioCompradorDTOS = new ArrayList<>();
        usuarioCompradorDTOS.add(new UsuarioCompradorDTO("Nombre"));
        usuarioCompradorDTOS.add(new UsuarioCompradorDTO("Correo"));
        usuarioCompradorDTOS.add(new UsuarioCompradorDTO("Apellidos"));
        usuarioCompradorDTOS.add(new UsuarioCompradorDTO("Ciudad"));
        usuarioCompradorDTOS.add(new UsuarioCompradorDTO("Domicilio"));
        usuarioCompradorDTOS.add(new UsuarioCompradorDTO("Sexo"));

        model.addAttribute("filtro", filtro);
        model.addAttribute("filtros", usuarioCompradorDTOS);
        model.addAttribute("usuarios", usuarios);

        return "usuariosCompradores";

    }

    @GetMapping("/usuariosCompradorVendedorDeUnGrupo/{id}")
    public String listarUsuariosCompradorVendedorDeUnGrupo(@PathVariable("id") Integer id, Model model, HttpSession session){

        if(!super.comprobarMarketingSession(session)){
            return super.redirectComprobarMarketingSession(session);
        }

        List<UsuarioDTO> usuarios = this.grupoService.listarUsuariosDeUnGrupo(id);

        UsuarioCompradorDTO filtro = new UsuarioCompradorDTO("Nombre");

        List<UsuarioCompradorDTO> usuarioCompradorDTOS = new ArrayList<>();
        usuarioCompradorDTOS.add(new UsuarioCompradorDTO("Nombre"));
        usuarioCompradorDTOS.add(new UsuarioCompradorDTO("Correo"));
        usuarioCompradorDTOS.add(new UsuarioCompradorDTO("Apellidos"));
        usuarioCompradorDTOS.add(new UsuarioCompradorDTO("Ciudad"));
        usuarioCompradorDTOS.add(new UsuarioCompradorDTO("Domicilio"));
        usuarioCompradorDTOS.add(new UsuarioCompradorDTO("Sexo"));

        model.addAttribute("filtro", filtro);
        model.addAttribute("filtros", usuarioCompradorDTOS);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("idGrupo", id);

        return "participantesGrupo";

    }

    @PostMapping("/usuariosCompradorVendedorDeUnGrupo/{id}")
    public String listarUsuariosCompradorVendedorDeUnGrupoFiltrado(@PathVariable("id") Integer id, @ModelAttribute("filtro") UsuarioCompradorDTO filtro, Model model, HttpSession session){

        if(!super.comprobarMarketingSession(session)){
            return super.redirectComprobarMarketingSession(session);
        }

        List<UsuarioDTO> usuarios = this.grupoService.listarUsuariosDeUnGrupo(id);
        List<Integer> idsUsuariosGrupo = this.grupoService.listarIdsUsuariosDeUnGrupo(id);

        switch(filtro.getSeleccionado()){
            case "Nombre":
                usuarios = this.usuarioCompradorService.buscarPorCompradorVendedorPorNombreQuePertencenAUnGrupo(filtro.getBusqueda(), idsUsuariosGrupo);
                break;
            case "Correo":
                usuarios = this.usuarioCompradorService.buscarPorCompradorVendedorPorCorreoQuePertencenAUnGrupo(filtro.getBusqueda(), idsUsuariosGrupo);
                break;
            case "Apellidos":
                usuarios = this.usuarioCompradorService.buscarPorCompradorVendedorPorApellidosQuePertencenAUnGrupo(filtro.getBusqueda(), idsUsuariosGrupo);
                break;
            case "Ciudad":
                usuarios = this.usuarioCompradorService.buscarPorCompradorVendedorPorCiudadQuePertencenAUnGrupo(filtro.getBusqueda(), idsUsuariosGrupo);
                break;
            case "Domicilio":
                usuarios = this.usuarioCompradorService.buscarPorCompradorVendedorPorDomicilioQuePertencenAUnGrupo(filtro.getBusqueda(), idsUsuariosGrupo);
                break;
            case "Sexo":
                usuarios = this.usuarioCompradorService.buscarPorCompradorVendedorPorSexoQuePertencenAUnGrupo(filtro.getBusqueda(), idsUsuariosGrupo);
                break;
        }

        if(filtro.getSaldoDesde() != null && usuarios.size() > 0){

            List<Integer> ids = new ArrayList<>();
            for(UsuarioDTO user : usuarios){
                ids.add(user.getId());
            }

            usuarios = this.usuarioCompradorService.buscarPorCompradorVendedorPorSaldoDesdeQuePertencenAUnGrupo(filtro.getSaldoDesde(), ids);

        }

        if(filtro.getSaldoHasta() != null && usuarios.size() > 0){

            List<Integer> ids = new ArrayList<>();
            for(UsuarioDTO user : usuarios){
                ids.add(user.getId());
            }

            usuarios = this.usuarioCompradorService.buscarPorCompradorVendedorPorSaldoHastaQuePertencenAUnGrupo(filtro.getSaldoHasta(), ids);

        }

        List<UsuarioCompradorDTO> usuarioCompradorDTOS = new ArrayList<>();
        usuarioCompradorDTOS.add(new UsuarioCompradorDTO("Nombre"));
        usuarioCompradorDTOS.add(new UsuarioCompradorDTO("Correo"));
        usuarioCompradorDTOS.add(new UsuarioCompradorDTO("Apellidos"));
        usuarioCompradorDTOS.add(new UsuarioCompradorDTO("Ciudad"));
        usuarioCompradorDTOS.add(new UsuarioCompradorDTO("Domicilio"));
        usuarioCompradorDTOS.add(new UsuarioCompradorDTO("Sexo"));

        model.addAttribute("filtro", filtro);
        model.addAttribute("filtros", usuarioCompradorDTOS);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("idGrupo", id);

        return "participantesGrupo";

    }

    @GetMapping("/borrarUsuarioCompradorVendedorDeUnGrupo/{id}/{idUsuario}")
    public String borrarUsuarioCompradorVendedorDeUnGrupo(@PathVariable("id") Integer id, @PathVariable("idUsuario") Integer idUsuario, HttpSession session){

        if(!super.comprobarMarketingSession(session)){
            return super.redirectComprobarMarketingSession(session);
        }

        this.grupoService.eliminarUsuarioAListaUsuariosGrupo(idUsuario, id);
        this.usuarioService.eliminarGrupoAListaGruposUsuario(idUsuario, id);

        return "redirect:/usuariosCompradorVendedorDeUnGrupo/"+id;

    }

    @GetMapping("/usuariosCompradorVendedorQueNoSonDeUnGrupo/{id}")
    public String listarUsuariosCompradorVendedorQueNoSonDeUnGrupo(@PathVariable("id") Integer id, Model model, HttpSession session){

        if(!super.comprobarMarketingSession(session)){
            return super.redirectComprobarMarketingSession(session);
        }

        List<Integer> idsUsuariosGrupo = this.grupoService.listarIdsUsuariosDeUnGrupo(id);
        List<UsuarioDTO> usuarios = this.grupoService.listarUsuariosQueNoPertenecenAUnGrupo(idsUsuariosGrupo);

        UsuarioCompradorDTO filtro = new UsuarioCompradorDTO("Nombre");

        List<UsuarioCompradorDTO> usuarioCompradorDTOS = new ArrayList<>();
        usuarioCompradorDTOS.add(new UsuarioCompradorDTO("Nombre"));
        usuarioCompradorDTOS.add(new UsuarioCompradorDTO("Correo"));
        usuarioCompradorDTOS.add(new UsuarioCompradorDTO("Apellidos"));
        usuarioCompradorDTOS.add(new UsuarioCompradorDTO("Ciudad"));
        usuarioCompradorDTOS.add(new UsuarioCompradorDTO("Domicilio"));
        usuarioCompradorDTOS.add(new UsuarioCompradorDTO("Sexo"));

        model.addAttribute("filtro", filtro);
        model.addAttribute("filtros", usuarioCompradorDTOS);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("idGrupo", id);

        return "participantesAñadirGrupo";

    }

    @PostMapping("/usuariosCompradorVendedorQueNoSonDeUnGrupo/{id}")
    public String listarUsuariosCompradorVendedorQueNoSonDeUnGrupoFiltrado(@PathVariable("id") Integer id, @ModelAttribute("filtro") UsuarioCompradorDTO filtro, Model model, HttpSession session){

        if(!super.comprobarMarketingSession(session)){
            return super.redirectComprobarMarketingSession(session);
        }

        List<Integer> idsUsuariosGrupo = this.grupoService.listarIdsUsuariosDeUnGrupo(id);
        List<UsuarioDTO> usuarios = this.grupoService.listarUsuariosQueNoPertenecenAUnGrupo(idsUsuariosGrupo);

        switch(filtro.getSeleccionado()){
            case "Nombre":
                usuarios = this.usuarioCompradorService.buscarPorCompradorVendedorPorNombreQueNoPertencenAUnGrupo(filtro.getBusqueda(), idsUsuariosGrupo);
                break;
            case "Correo":
                usuarios = this.usuarioCompradorService.buscarPorCompradorVendedorPorCorreoQueNoPertencenAUnGrupo(filtro.getBusqueda(), idsUsuariosGrupo);
                break;
            case "Apellidos":
                usuarios = this.usuarioCompradorService.buscarPorCompradorVendedorPorApellidosQueNoPertencenAUnGrupo(filtro.getBusqueda(), idsUsuariosGrupo);
                break;
            case "Ciudad":
                usuarios = this.usuarioCompradorService.buscarPorCompradorVendedorPorCiudadQueNoPertencenAUnGrupo(filtro.getBusqueda(), idsUsuariosGrupo);
                break;
            case "Domicilio":
                usuarios = this.usuarioCompradorService.buscarPorCompradorVendedorPorDomicilioQueNoPertencenAUnGrupo(filtro.getBusqueda(), idsUsuariosGrupo);
                break;
            case "Sexo":
                usuarios = this.usuarioCompradorService.buscarPorCompradorVendedorPorSexoQueNoPertencenAUnGrupo(filtro.getBusqueda(), idsUsuariosGrupo);
                break;
        }

        if(filtro.getSaldoDesde() != null && usuarios.size() > 0){

            List<Integer> ids = new ArrayList<>();
            for(UsuarioDTO user : usuarios){
                ids.add(user.getId());
            }

            usuarios = this.usuarioCompradorService.buscarPorCompradorVendedorPorSaldoDesdeQueNoPertencenAUnGrupo(filtro.getSaldoDesde(), ids, idsUsuariosGrupo);

        }

        if(filtro.getSaldoHasta() != null && usuarios.size() > 0){

            List<Integer> ids = new ArrayList<>();
            for(UsuarioDTO user : usuarios){
                ids.add(user.getId());
            }

            usuarios = this.usuarioCompradorService.buscarPorCompradorVendedorPorSaldoHastaQueNoPertencenAUnGrupo(filtro.getSaldoHasta(), ids, idsUsuariosGrupo);

        }


        List<UsuarioCompradorDTO> usuarioCompradorDTOS = new ArrayList<>();
        usuarioCompradorDTOS.add(new UsuarioCompradorDTO("Nombre"));
        usuarioCompradorDTOS.add(new UsuarioCompradorDTO("Correo"));
        usuarioCompradorDTOS.add(new UsuarioCompradorDTO("Apellidos"));
        usuarioCompradorDTOS.add(new UsuarioCompradorDTO("Ciudad"));
        usuarioCompradorDTOS.add(new UsuarioCompradorDTO("Domicilio"));
        usuarioCompradorDTOS.add(new UsuarioCompradorDTO("Sexo"));

        model.addAttribute("filtro", filtro);
        model.addAttribute("filtros", usuarioCompradorDTOS);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("idGrupo", id);

        return "participantesAñadirGrupo";

    }

    @GetMapping("añadirParticipanteAGrupo/{idGrupo}/{idUsuario}")
    public String añadirParticipanteAGrupo(@PathVariable("idGrupo") Integer idGrupo, @PathVariable("idUsuario") Integer idUsuario, Model model, HttpSession session){

        if(!super.comprobarMarketingSession(session)){
            return super.redirectComprobarMarketingSession(session);
        }

        this.grupoService.anadirUsuarioAListaUsuariosGrupo(idUsuario, idGrupo);
        this.usuarioService.anadirGrupoAListaGruposUsuario(idUsuario, idGrupo);

        return "redirect:/usuariosCompradorVendedorQueNoSonDeUnGrupo/"+idGrupo;

    }

}
