package es.taw.swishbay.controller;

import es.taw.swishbay.dto.CategoriaDTO;
import es.taw.swishbay.dto.ProductoDTO;
import es.taw.swishbay.dto.PujaDTO;
import es.taw.swishbay.dto.UsuarioDTO;
import es.taw.swishbay.service.CategoriaService;
import es.taw.swishbay.service.ProductoService;
import es.taw.swishbay.service.PujaService;
import es.taw.swishbay.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Este contolador efectua las pujas por parte del comprador
 *
 * @author Miguel Oña Guerrero
 */

@Controller
@RequestMapping("comprador")
public class CompradorPujarController extends SwishBayController{

    private PujaService pujaService;
    private UsuarioService usuarioService;
    private ProductoService productoService;
    private CategoriaService categoriaService;

    @Autowired
    private void setPujaService(PujaService pujaService){
        this.pujaService = pujaService;
    }

    @Autowired
    private void setUsuarioService(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @Autowired
    private void setProductoService(ProductoService productoService){
        this.productoService = productoService;
    }

    @Autowired
    private void setCategoriaService(CategoriaService categoriaService){
        this.categoriaService = categoriaService;
    }

    @PostMapping("/pujar")
    public String doPujar(@ModelAttribute("puja") PujaDTO puja, Model model, HttpSession session){

        if (!super.comprobarCompradorVendedorSession(session)) {
            return super.redirectComprobarCompradorVendedorSession(session);
        }

        String error;
        String goTo = "redirect:/comprador/producto/" + puja.getProducto();

        UsuarioDTO usuario = (UsuarioDTO) session.getAttribute("usuario");
        puja.setComprador(usuario);
        puja.setFecha(new Date());

        error = pujaService.checkPuja(puja);

        if(error == ""){
            Double cantidad = pujaService.realizarPuja(puja);
            usuario = usuarioService.sumarSaldo( - cantidad, usuario.getId());

            session.setAttribute("usuario", usuario);

        }else{
            List<PujaDTO> pujas = pujaService.buscarPujasOrdenadas(puja.getProducto());
            ProductoDTO producto = productoService.buscarProducto(puja.getProducto());
            CategoriaDTO categoria = categoriaService.buscarCategoria(producto.getCategoria());

            model.addAttribute("pujas", pujas);
            model.addAttribute("producto", producto);
            model.addAttribute("categoria", categoria);
            model.addAttribute("puja", puja);
            goTo = "compradorProducto";

        }

        model.addAttribute("error", error);

        return goTo;
    }
}
