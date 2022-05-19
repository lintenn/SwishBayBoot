package es.taw.swishbay.controller;

import es.taw.swishbay.dto.ProductoDTO;
import es.taw.swishbay.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class EnPujaGuardarController {

    private ProductoService productoService;

    @Autowired
    public void setProductoService(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping("/puja/guardar")
    public String guardarEnPuja(Model model, @RequestParam("id") int id, @RequestParam("time") String time, @RequestParam("precio") Double precio) {

        //if (super.comprobarCompradorVendedorSession(request, response)) {

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

                return "redirect:/misProductosEnPuja";
            }else{
                status= "La fecha introducida es anterior a la actual";
                model.addAttribute("status", status);

                return "enPuja";

            }
        //}
    }

}
