package es.taw.swishbay.controller;

import es.taw.swishbay.dto.ProductoDTO;
import es.taw.swishbay.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class EnPujaNuevoController {

    private ProductoService productoService;

    @Autowired
    public void setProductoService(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/puja/editar/{id}")
    public String ponerEnPuja(@PathVariable("id") int id, Model model) {

        //if (super.comprobarCompradorVendedorSession(request, response)) {


        ProductoDTO p = productoService.buscarProducto(""+id);
        Double precio = 0.0;
        if(p.getEnPuja()==1)
            precio = productoService.precioMax(""+id);
        else
            precio = p.getPrecioSalida();

        model.addAttribute("producto", p);
        model.addAttribute("precio", precio);

        return "enPuja";
        //}
    }


}
