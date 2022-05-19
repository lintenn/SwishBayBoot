package es.taw.swishbay.controller;

import es.taw.swishbay.dto.UsuarioDTO;
import es.taw.swishbay.service.ProductoService;
import es.taw.swishbay.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductoGuardarController {

    private ProductoService productoService;
    private UsuarioService usuarioService;

    @Autowired
    public void setProductoService(ProductoService productoService) {
        this.productoService = productoService;
    }

    @Autowired
    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/producto/crear")
    public String guardarProducto(Model model, @RequestParam("id") String strId, @RequestParam("nombre") String nombre, @RequestParam("descripcion") String descripcion, @RequestParam("foto") String foto, @RequestParam("precio") String precio, @RequestParam("categoria") String categoria) {

        //if (super.comprobarCompradorVendedorSession(request, response)) {
        //UsuarioDTO user = (UsuarioDTO)sesion.getAttribute("usuario"); quitar luego
        UsuarioDTO user = usuarioService.buscarUsuario(4);
        String  status=null;

        java.sql.Date date=new java.sql.Date(System.currentTimeMillis());

        if(foto==null || foto.isEmpty()){
            foto= "https://th.bing.com/th/id/OIP.KeKY2Y3R0HRBkPEmGWU3FwHaHa?pid=ImgDet&rs=1";
        }

        if(!precio.matches("[-+]?\\d*\\.?\\d+")){
            status= "Formato de precio incorrecto.";
            model.addAttribute("status", status);

            return "/producto/crear";

        }
        if(strId == null || strId.isEmpty()){

            productoService.crearProducto(nombre, descripcion, foto, date, categoria, precio, user.getId());
        }else {
            productoService.modificarProducto(strId, nombre, descripcion, foto, date, categoria, precio);
        }

        return "redirect:/misProductos";
        //}
    }


}
