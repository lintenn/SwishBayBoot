package es.taw.swishbay.controller;

import es.taw.swishbay.dto.CategoriaDTO;
import es.taw.swishbay.dto.ProductoDTO;
import es.taw.swishbay.dto.UsuarioDTO;
import es.taw.swishbay.service.CategoriaService;
import es.taw.swishbay.service.ProductoService;
import es.taw.swishbay.service.SellerService;
import es.taw.swishbay.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Controlador del administrador
 * @author Galo
 */

@Controller
@RequestMapping("pujas")
public class PujasController extends SwishBayController {

    private ProductoService productoService;

    @Autowired
    public void setProductoService(ProductoService productoService){
        this.productoService= productoService;
    }


    @GetMapping("/{id}/editar")
    public String doPonerEnPuja(@PathVariable("id") int id, Model model, HttpSession session) {

        if (!super.comprobarCompradorVendedorSession(session)) {
            return super.redirectComprobarCompradorVendedorSession(session);
        }

        ProductoDTO p = productoService.buscarProducto(""+id);
        Double precio = 0.0;
        if(p.getEnPuja()==1) {
            precio = productoService.precioMax("" + id);
            if(precio==null)
                precio=p.getPrecioSalida();
        }else
            precio = p.getPrecioSalida();

        model.addAttribute("producto", p);
        model.addAttribute("precio", precio);

        return "enPuja";
        //}
    }

    @PostMapping("/guardar")
    public String doGuardarEnPuja(Model model, HttpSession session, @RequestParam("id") int id, @RequestParam("time") String time, @RequestParam("precio") Double precio) {

        if (!super.comprobarCompradorVendedorSession(session)) {
            return super.redirectComprobarCompradorVendedorSession(session);
        }

        ProductoDTO p;

        String str, status= null;


        p = productoService.buscarProducto(""+id);


        SimpleDateFormat dateParser = new SimpleDateFormat("yy-MM-dd");
        Date d=new Date();

        try {
            d = dateParser.parse(time);
        } catch (ParseException ex) {
            System.err.println(ex.getLocalizedMessage());
        }

        Date actual = new Date();
        if(actual.before(d)){

            if(p.getEnPuja()==0){

                productoService.modificarPuja(id,precio,d);
            }else{
                productoService.modificarPuja(id, d);
            }

            return "redirect:/seller/misProductosEnPuja";
        }else{
            status= "La fecha introducida es anterior a la actual";
            model.addAttribute("status", status);

            return "enPuja";

        }

    }

    @GetMapping("/{id}/borrar")
    public String doBorrarEnPuja(HttpSession session, @PathVariable("id") int id) {

        if (!super.comprobarCompradorVendedorSession(session)) {
            return super.redirectComprobarCompradorVendedorSession(session);
        }
        productoService.quitarPuja(""+id);

        return "redirect:/seller/misProductosEnPuja";

    }

    @GetMapping("/{id}/finalizar")
    public String doFinalizarPuja(HttpSession session, @PathVariable("id") int id) {

        if (!super.comprobarCompradorVendedorSession(session)) {
            return super.redirectComprobarCompradorVendedorSession(session);
        }
        productoService.finalizarPuja(id);

        return "redirect:/seller/misProductosEnPuja";

    }




}
