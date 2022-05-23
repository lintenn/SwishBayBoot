package es.taw.swishbay.controller;

import es.taw.swishbay.dto.CategoriaDTO;
import es.taw.swishbay.dto.ProductoDTO;
import es.taw.swishbay.dto.RolUsuarioDTO;
import es.taw.swishbay.dto.UsuarioDTO;
import es.taw.swishbay.service.CategoriaService;
import es.taw.swishbay.service.ProductoService;
import es.taw.swishbay.service.RolUsuarioService;
import es.taw.swishbay.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Controlador del administrador
 * @author Luis
 */

@Controller
public class AdministradorController extends SwishBayController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolUsuarioService rolUsuarioService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ProductoService productoService;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////                                      USUARIOS                                           /////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping(value = "/usuarios", method = { RequestMethod.GET, RequestMethod.POST })
    public String listarUsuarios(Model model, HttpSession session, @RequestParam(value = "filtro", required = false) String filtroNombre, @RequestParam(value = "filtroRol", required = false) String filtroRol) {

        if (!super.comprobarAdminSession(session)) {
            return super.redirectComprobarAdminSession(session);
        }

        List<RolUsuarioDTO> roles = this.rolUsuarioService.listarRoles();

        List<UsuarioDTO> usuarios = this.usuarioService.listarUsuarios(filtroNombre, filtroRol);

        model.addAttribute("usuarios", usuarios);
        model.addAttribute("roles", roles);
        model.addAttribute("selected", filtroRol);

        return "usuarios";
    }

    @GetMapping("/usuarioNuevoEditar")
    public String usuarioNuevoEditar(Model model, HttpSession session, @RequestParam(value = "id", required = false) String id) {

        if (!super.comprobarAdminSession(session)) {
            return super.redirectComprobarAdminSession(session);
        }

        List<CategoriaDTO> categorias = this.categoriaService.listarCategorias();
        List<RolUsuarioDTO> roles = this.rolUsuarioService.listarRoles();

        model.addAttribute("categorias", categorias);
        model.addAttribute("roles", roles);

        if (id != null && !id.isEmpty()) {
            UsuarioDTO usuario = this.usuarioService.buscarUsuario(Integer.parseInt(id));
            model.addAttribute("usuario", usuario);
        }

        return "usuario";
    }

    @PostMapping("/usuarioGuardar")
    public String usuarioGuardar(Model model, HttpSession session, @RequestParam(value = "id", required = false) String id, @RequestParam("nombre") String nombre, @RequestParam("apellidos") String apellidos, @RequestParam("correo") String correo, @RequestParam("password") String password, @RequestParam("domicilio") String domicilio, @RequestParam("ciudad") String ciudad, @RequestParam("fechaNacimiento") String strFechaNacimiento, @RequestParam(value = "saldo",defaultValue = "0") String strSaldo, @RequestParam("sexo") String sexo, @RequestParam(value = "tipo", defaultValue = "compradorvendedor") String strTipoUsuario, @RequestParam("categoria") String[] categorias) {

        UsuarioDTO user = (UsuarioDTO) session.getAttribute("usuario");

        if (user == null || user.getRol().getNombre().equals("administrador")) {

            String status = null;
            double saldo = 0;
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fechaNacimiento = null;

            try {
                fechaNacimiento = formato.parse(strFechaNacimiento);
                status = this.usuarioService.comprobarInformacionUsuario(fechaNacimiento, id, correo, strSaldo);
            } catch (ParseException ex) {
                status = "Formato de fecha de nacimiento incorrecto.";
                System.out.println(ex);
            }

            if (status != null) {

                model.addAttribute("status", status);

                if (user == null) {
                    return "register";
                } else {
                    return "usuarioNuevoEditar";
                }

            } else {

                String goTo = "redirect:/compradorProductos";

                if (user == null) {    // Registro de compradorvendedor

                    //strTipoUsuario = "compradorvendedor";
                    UsuarioDTO usuario = this.usuarioService.crearUsuario(nombre, apellidos, correo, password, domicilio, ciudad, sexo, fechaNacimiento, saldo, strTipoUsuario, categorias);

                    session.setAttribute("usuario", usuario);
                    goTo = "redirect:/compradorProductos";

                } else if (id == null || id.isEmpty()) {    // Creación desde administrador

                    saldo = Double.parseDouble(strSaldo);
                    this.usuarioService.crearUsuario(nombre, apellidos, correo, password, domicilio, ciudad, sexo, fechaNacimiento, saldo, strTipoUsuario, categorias);

                    goTo = "redirect:/usuarios";
                } else {    // Modificación desde administrador

                    saldo = Double.parseDouble(strSaldo);
                    int idi = Integer.parseInt(id);
                    UsuarioDTO usuario = this.usuarioService.modificarUsuario(idi, nombre, apellidos, correo, password, domicilio, ciudad, sexo, fechaNacimiento, saldo, strTipoUsuario, categorias);

                    if (user.getId() == idi) {    // si se modifica al propio administrador, hay que actualizar el usuario de la sesión
                        session.setAttribute("usuario", usuario);
                    }

                    goTo = "redirect:/usuarios";

                }

                return goTo;

            }

        } else {

            String redirectTo = "redirect:/compradorProductos";
            if (user.getRol().getNombre().equals("compradorvendedor")) {
                redirectTo = "redirect:/compradorProductos";
            } else if (user.getRol().getNombre().equals("marketing")) {
                redirectTo = "redirect:/usuarioComprador";
            }
            return redirectTo;

        }
    }

    @GetMapping("/usuarioBorrar")
    public String usuarioBorrar(@RequestParam("id") Integer id, HttpSession session) {
        if (!super.comprobarAdminSession(session)) {
            return super.redirectComprobarAdminSession(session);
        }

        this.usuarioService.borrarUsuario(id);

        return "redirect:/usuarios";
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////                                      PRODUCTOS                                          /////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping(value = "/productosAdmin", method = { RequestMethod.GET, RequestMethod.POST })
    public String productosAdmin(Model model, HttpSession session, @RequestParam(value = "filtro", required = false) String filtroNombre, @RequestParam(value = "filtroCategoria", required = false) String filtroCategoria, @RequestParam(value = "desde", required = false) String filtroDesde, @RequestParam(value = "hasta", required = false) String filtroHasta) {
        if (!super.comprobarAdminSession(session)) {
            return super.redirectComprobarAdminSession(session);
        }

        List<CategoriaDTO> categorias = this.categoriaService.listarCategorias();

        if(filtroDesde!=null && (Double.parseDouble(filtroDesde)> Double.parseDouble(filtroHasta)))
            filtroDesde="0";

        List<ProductoDTO> productos = this.productoService.listarProductos(filtroNombre, filtroCategoria, filtroDesde, filtroHasta);

        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categorias);
        model.addAttribute("selected", filtroCategoria);
        model.addAttribute("desdeSelected", filtroDesde);
        model.addAttribute("hastaSelected", filtroHasta);

        return "productosAdmin";
    }

    @GetMapping("/productoAdminEditar")
    public String productoEditar(@RequestParam("id") String id, Model model, HttpSession session) {
        if (!super.comprobarAdminSession(session)) {
            return super.redirectComprobarAdminSession(session);
        }

        ProductoDTO producto = this.productoService.buscarProducto(id);
        List<CategoriaDTO> categorias = this.categoriaService.listarCategorias();


        model.addAttribute("categorias", categorias);
        model.addAttribute("producto", producto);

        return "productoAdmin";
    }

    // Falta /productoAdminGuardar

    @GetMapping("/productoAdminBorrar")
    public String productoBorrar(@RequestParam("id") Integer id, HttpSession session) {
        if (!super.comprobarAdminSession(session)) {
            return super.redirectComprobarAdminSession(session);
        }

        this.productoService.borrarProducto(id);

        return "redirect:/productosAdmin";
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////                                      CATEGORIAS                                         /////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping(value = "/categorias", method = { RequestMethod.GET, RequestMethod.POST })
    public String categorias(Model model, HttpSession session, @RequestParam(value = "filtro", required = false) String filtroNombre) {
        if (!super.comprobarAdminSession(session)) {
            return super.redirectComprobarAdminSession(session);
        }

        List<CategoriaDTO> categorias = this.categoriaService.listarCategorias(filtroNombre);

        model.addAttribute("categorias", categorias);

        return "categorias";
    }

    @GetMapping("/categoriaNuevoEditar")
    public String categoriaNuevoEditar(@RequestParam(value = "id", required = false) String id, Model model, HttpSession session) {
        if (!super.comprobarAdminSession(session)) {
            return super.redirectComprobarAdminSession(session);
        }

        if (id != null && !id.isEmpty()) {
            CategoriaDTO categoria = this.categoriaService.buscarCategoria(Integer.parseInt(id));
            model.addAttribute("categoria", categoria);
        }

        return "categoria";
    }

    // Falta /categoriaGuardar

    @GetMapping("/categoriaBorrar")
    public String categoriaBorrar(@RequestParam("id") Integer id, HttpSession session) {
        if (!super.comprobarAdminSession(session)) {
            return super.redirectComprobarAdminSession(session);
        }

        this.categoriaService.borrarCategoria(id);

        return "redirect:/categorias";
    }

}
