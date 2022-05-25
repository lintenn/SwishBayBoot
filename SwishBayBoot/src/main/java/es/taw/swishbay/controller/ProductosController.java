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
import java.util.List;

@Controller
@RequestMapping("productos")
public class ProductosController {

    private CategoriaService categoriaService;
    private SellerService sellerService;
    private UsuarioService usuarioService;
    private ProductoService productoService;

    @Autowired
    public void setCategoriaService(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @Autowired
    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @Autowired
    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Autowired
    public void setProductoService(ProductoService productoService){
        this.productoService= productoService;
    }

    @GetMapping(value = "/{id}/editar")
    public String doEnviarEditar(@PathVariable("id") int id, Model model) {

        //if (super.comprobarCompradorVendedorSession(request, response)) {

        ProductoDTO p= productoService.buscarProducto(""+id);
        List<CategoriaDTO> categorias = categoriaService.listarCategorias();

        model.addAttribute("categorias",categorias );
        if(p!=null)
            model.addAttribute("producto", p);
        return "producto";
        //}
    }

    @GetMapping(value = "/nuevo")
    public String doEnviarCrear(Model model) {

        //if (super.comprobarCompradorVendedorSession(request, response)) {

        List<CategoriaDTO> categorias = categoriaService.listarCategorias();

        model.addAttribute("categorias",categorias );

        return "producto";
        //}
    }

    @PostMapping("/producto/crear")
    public String doGuardarProducto(Model model, @RequestParam("id") String strId, @RequestParam("nombre") String nombre, @RequestParam("descripcion") String descripcion, @RequestParam("foto") String foto, @RequestParam("precio") String precio, @RequestParam("categoria") String categoria) {

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

    @GetMapping("/{id}/borrar")
    public String borrarProducto(@PathVariable("id") int id) {

        //if (super.comprobarCompradorVendedorSession(request, response)) {
        productoService.borrarProducto(id);

        return "redirect:/misProductos";
        //}
    }



}
