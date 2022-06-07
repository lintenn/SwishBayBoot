package es.taw.swishbay.controller;

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

/**
 * Este controlador muestra la información y las pujas de un producto
 *
 * @author Miguel Oña Guerrero
 */

@Controller
@RequestMapping("comprador")
public class CompradorProductoController extends SwishBayController{

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

        if (!super.comprobarCompradorVendedorSession(session)) {
            return super.redirectComprobarCompradorVendedorSession(session);
        }

        ProductoDTO producto = productoService.buscarProducto(id);
        CategoriaDTO categoria = categoriaService.buscarCategoria(producto.getCategoria());
        PujaDTO puja = new PujaDTO();

        if(producto.getEnPuja() == 1){
            List<PujaDTO> pujas = pujaService.buscarPujasOrdenadas(producto.getId());
            model.addAttribute("pujas", pujas);
        }

        if(producto.getComprador() != null){
            PujaDTO pujaGanadora = pujaService.buscarMayorPuja(producto.getId());
            model.addAttribute("pujaGanadora", pujaGanadora);
        }

        model.addAttribute("producto", producto);
        model.addAttribute("categoria", categoria);
        model.addAttribute("puja", puja);
        session.setAttribute("goToProducto", "/comprador/producto/" + producto.getId());

        return "compradorProducto";
    }
}
