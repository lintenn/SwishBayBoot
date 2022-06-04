package es.taw.swishbay.controller;

import es.taw.swishbay.dao.ProductoRepository;
import es.taw.swishbay.dto.CategoriaDTO;
import es.taw.swishbay.dto.ProductoDTO;
import es.taw.swishbay.dto.PujaDTO;
import es.taw.swishbay.service.CategoriaService;
import es.taw.swishbay.service.ProductoService;
import es.taw.swishbay.service.PujaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("comprador")
public class CompradorProductoController {

    private ProductoService productoService;
    private CategoriaService categoriaService;
    private PujaService pujaService;

    @Autowired
    private void setProductoService(ProductoService productoService){
        this.productoService = productoService;
    }

    @Autowired
    private void setCategoriaService(CategoriaService categoriaService){
        this.categoriaService = categoriaService;
    }

    @Autowired
    private void setPujaService(PujaService pujaService){
        this.pujaService = pujaService;
    }

    @GetMapping("/producto/{id}")
    public String doVerProducto(@PathVariable("id") Integer id, Model model, HttpSession session){

        ProductoDTO producto = productoService.findByID(id);
        CategoriaDTO categoria = categoriaService.buscarCategoria(producto.getCategoria());

        if(producto.getEnPuja() == 1){
            List<PujaDTO> pujas = pujaService.buscarPujasOrdenadas(producto.getId());
            model.addAttribute("pujas", pujas);
        }

        model.addAttribute("producto", producto);
        model.addAttribute("categoria", categoria);
        session.setAttribute("goTo", "/producto/" + producto.getId());

        return "compradorProducto";
    }
}
