package es.taw.swishbay.controller;

import es.taw.swishbay.dto.CategoriaDTO;
import es.taw.swishbay.dto.ProductoDTO;
import es.taw.swishbay.dto.UsuarioDTO;
import es.taw.swishbay.entity.Producto;
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
public class ProductosController extends SwishBayController {

    private CategoriaService categoriaService;
    private UsuarioService usuarioService;
    private ProductoService productoService;

    @Autowired
    public void setCategoriaService(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
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
    public String doEnviarEditar(@PathVariable("id") int id, Model model, HttpSession session) {

        if (!super.comprobarCompradorVendedorSession(session)) {
            return super.redirectComprobarCompradorVendedorSession(session);
        }

        ProductoDTO p= productoService.buscarProducto(""+id);
        List<CategoriaDTO> categorias = categoriaService.listarCategorias();

        model.addAttribute("categorias",categorias );
        if(p!=null)
            model.addAttribute("producto", p);
        return "producto";

    }

    @GetMapping(value = "/nuevo")
    public String doEnviarCrear(Model model, HttpSession session) {

        if (!super.comprobarCompradorVendedorSession(session)) {
            return super.redirectComprobarCompradorVendedorSession(session);
        }

        List<CategoriaDTO> categorias = categoriaService.listarCategorias();
        ProductoDTO p = new ProductoDTO();

        model.addAttribute("categorias",categorias );
        model.addAttribute("producto", p);

        return "producto";

    }

    @PostMapping("/crear")
    public String doGuardarProducto(@ModelAttribute("producto") ProductoDTO producto, HttpSession session) {

        if (!super.comprobarCompradorVendedorSession(session)) {
            return super.redirectComprobarCompradorVendedorSession(session);
        }

        UsuarioDTO user = usuarioService.buscarUsuario(4);
        String  status=null;

        java.sql.Date date=new java.sql.Date(System.currentTimeMillis());

        if(producto.getFoto()==null || producto.getFoto().isEmpty()){
            producto.setFoto("https://th.bing.com/th/id/OIP.KeKY2Y3R0HRBkPEmGWU3FwHaHa?pid=ImgDet&rs=1");
        }


        if(producto.getId() == null){
            productoService.crearProducto(producto.getTitulo(), producto.getDescripcion(), producto.getFoto(), date, producto.getCategoria(), producto.getPrecioSalida(), user.getId());
        }else {
            productoService.modificarProducto(producto.getId(), producto.getTitulo(), producto.getDescripcion(), producto.getFoto(), date, producto.getCategoria(), producto.getPrecioSalida());
        }

        return "redirect:/seller/misProductos";

    }

    @GetMapping("/{id}/borrar")
    public String borrarProducto(@PathVariable("id") int id, HttpSession session) {

        if (!super.comprobarCompradorVendedorSession(session)) {
            return super.redirectComprobarCompradorVendedorSession(session);
        }

        productoService.borrarProducto(id);

        return "redirect:/seller/misProductos";

    }



}
