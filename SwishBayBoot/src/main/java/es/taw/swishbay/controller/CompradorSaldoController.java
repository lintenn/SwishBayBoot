package es.taw.swishbay.controller;

import es.taw.swishbay.dto.UsuarioDTO;
import es.taw.swishbay.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;

/**
 * Este controller añade fondos al saldo actual.
 *
 * @author Miguel Oña Guerrero
 */

@Controller
@RequestMapping("comprador")
public class CompradorSaldoController extends SwishBayController{

    UsuarioService usuarioService;

    @Autowired
    private void setUsuarioService(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @PostMapping("/saldo")
    public String doSumarSaldo(@RequestParam("saldo") Double cantidad, HttpSession session){

        if (!super.comprobarCompradorVendedorSession(session)) {
            return super.redirectComprobarCompradorVendedorSession(session);
        }

        UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");

        usuario = usuarioService.sumarSaldo(cantidad, usuario.getId());

        session.setAttribute("usuario", usuario);

        String goTo = (String) session.getAttribute("goTo");
        if(goTo == null){
            goTo = "/productos";
        }

        return "redirect:/comprador" + goTo;
    }
}