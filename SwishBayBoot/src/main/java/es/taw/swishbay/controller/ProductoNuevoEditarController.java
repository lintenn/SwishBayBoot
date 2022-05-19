package es.taw.swishbay.controller;

import es.taw.swishbay.dto.CategoriaDTO;
import es.taw.swishbay.dto.ProductoDTO;
import es.taw.swishbay.entity.Usuario;
import es.taw.swishbay.service.CategoriaService;
import es.taw.swishbay.service.ProductoService;
import es.taw.swishbay.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProductoNuevoEditarController {

    private ProductoService productoService;
    private CategoriaService categoriaService;
    private UsuarioService usuarioService;

    @Autowired
    public void setProductoService(ProductoService productoService) {
        this.productoService = productoService;
    }

    @Autowired
    public void setCategoriaService(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @Autowired
    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping(value = "/producto/editar/{id}")
    public String enviarEditar(@PathVariable("id") int id, Model model) {

        //if (super.comprobarCompradorVendedorSession(request, response)) {

            ProductoDTO p= productoService.buscarProducto(""+id);
            List<CategoriaDTO> categorias = categoriaService.listarCategorias();

            model.addAttribute("categorias",categorias );
            if(p!=null)
                model.addAttribute("producto", p);
            return "producto";
        //}
    }

    @GetMapping(value = "/producto/nuevo")
    public String enviarCrear(Model model) {

        //if (super.comprobarCompradorVendedorSession(request, response)) {

        List<CategoriaDTO> categorias = categoriaService.listarCategorias();

        model.addAttribute("categorias",categorias );

        return "producto";
        //}
    }



}
