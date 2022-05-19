package es.taw.swishbay.controller;

import es.taw.swishbay.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class FinalizarPujaController {

    private ProductoService productoService;

    @Autowired
    public void setProductoService(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/puja/finalizar/{id}")
    public String borrarEnPuja(@PathVariable("id") int id) {

        //if (super.comprobarCompradorVendedorSession(request, response)) {

        productoService.finalizarPuja(""+id);

        return "redirect:/misProductosEnPuja";

        //}
    }

}
