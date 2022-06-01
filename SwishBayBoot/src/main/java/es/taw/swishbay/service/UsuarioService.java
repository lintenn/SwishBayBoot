package es.taw.swishbay.service;

import es.taw.swishbay.dao.*;
import es.taw.swishbay.dto.UsuarioDTO;
import es.taw.swishbay.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;
    private RolUsuarioRepository rolUsuarioRepository;
    private CategoriaRepository categoriaRepository;
    private GrupoRepository grupoRepository;
    private ProductoRepository productoRepository;

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Autowired
    public void setRolUsuarioRepository(RolUsuarioRepository rolUsuarioRepository) {
        this.rolUsuarioRepository = rolUsuarioRepository;
    }

    @Autowired
    public void setCategoriaRepository(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Autowired
    public void setGrupoRepository(GrupoRepository grupoRepository) {
        this.grupoRepository = grupoRepository;
    }

    @Autowired
    public void setProductoRepository(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void delete(Usuario u) {
        usuarioRepository.delete(u);
    }

    private List<UsuarioDTO> listaEntityADTO (List<Usuario> lista) { // Luis
        List<UsuarioDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Usuario usuario : lista) {
                listaDTO.add(usuario.toDTO());
            }
        }
        return listaDTO;
    }

    public List<UsuarioDTO> listarUsuarios (String filtroNombre) { // Luis
        List<Usuario> usuarios = null;

        if (filtroNombre == null || filtroNombre.isEmpty()) {
            usuarios = this.usuarioRepository.findAll();
        } else {
            usuarios = this.usuarioRepository.findByNombre(filtroNombre);
        }

        return this.listaEntityADTO(usuarios);
    }

    public List<UsuarioDTO> listarUsuarios (String filtroNombre, String filtroRol) { // Luis
        List<Usuario> usuarios = null;

        if (filtroNombre == null || filtroNombre.isEmpty()) {
            if (filtroRol == null || filtroRol.equals("Tipo")) {
                usuarios = this.usuarioRepository.findAll();

            } else {
                usuarios = this.usuarioRepository.findAll(filtroRol);

            }
        } else {
            if (filtroRol == null || filtroRol.equals("Tipo")) {
                usuarios = this.usuarioRepository.findByNombre(filtroNombre);

            } else {
                usuarios = this.usuarioRepository.findByNombre(filtroNombre, filtroRol);

            }
        }

        return this.listaEntityADTO(usuarios);
    }

    public UsuarioDTO buscarUsuario (Integer id) { // Luis
        Usuario usuario = this.buscarUsuarioById(id);
        return usuario.toDTO();
    }

    private Usuario buscarUsuarioById (Integer id) { // angel
        Usuario usuario = this.usuarioRepository.getById(id);
        return usuario;
    }

    public void borrarUsuario (Integer id) { // Luis
        Usuario usuario = this.buscarUsuarioById(id);

        this.usuarioRepository.delete(usuario);
    }

    public String comprobarInformacionUsuario (Date fechaNacimiento, String strId, String correo, String strSaldo) { // Luis

        String status;
        Date fechaSistema = new Date();
        int edad = fechaSistema.getYear() - fechaNacimiento.getYear();
        Usuario posibleUser = null;

        boolean esMenor = edad < 18;
        int mes, dia;
        if (edad == 18) {
            mes = fechaSistema.getMonth() - fechaNacimiento.getMonth();
            if (mes == 0) {
                dia = fechaSistema.getDay() - fechaNacimiento.getDay();
                esMenor = dia < 0;
            } else {
                esMenor = mes < 0;
            }
        }

        if (strId == null || strId.isEmpty()) { // si estamos añadiendo
            try {
                posibleUser = usuarioRepository.findByCorreo(correo);
            } catch (Exception e) {
                posibleUser = null;
            }
        }

        if (strSaldo != null && !strSaldo.isEmpty() && !strSaldo.matches("[-+]?\\d*\\.?\\d+")) {
            status= "Formato de saldo incorrecto.";

        } else if (posibleUser != null) {
            status = "El correo introducido ya existe en el sistema";

        } else if (esMenor) {
            status = "No se permiten usuarios menores de edad";

        } else {
            status = null;
        }

        return status;
    }

    private void rellenarUsuario (Usuario usuario, String nombre, String apellidos, String correo,
                                  String password, String domicilio, String ciudad, String sexo,
                                  Date fechaNacimiento, Double saldo, String strTipoUsuario) { // Luis
        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setCorreo(correo);
        usuario.setPassword(password);
        usuario.setDomicilio(domicilio);
        usuario.setCiudad(ciudad);
        usuario.setSexo(sexo);
        usuario.setFechaNacimiento(fechaNacimiento);
        usuario.setSaldo(saldo);

        RolUsuario rol = this.rolUsuarioRepository.findByNombre(strTipoUsuario);
        usuario.setRol(rol);

        // Faltarian las categorias...
    }

    private void actualizarRolUsuario (Usuario newUser) { // Luis
        RolUsuario rol = newUser.getRol();

        rol.getUsuarioList().add(newUser);
        this.rolUsuarioRepository.save(rol);
    }

    private void rellenarCategoriasUsuario (String[] categorias, Usuario newUser) { // Luis
        // Cargamos las categorias...

        // Borramos al usuario de las categorias anteriores
        for (Categoria categoria : newUser.getCategoriaList()) {
            categoria.getUsuarioList().remove(newUser);

            this.categoriaRepository.save(categoria);
        }
        // Borramos las categorias anteriores del usuario
        newUser.getCategoriaList().clear();

        if (categorias != null) {
            for (String categoriaId : categorias) {
                // Añadimos al usuario en las nuevas categorias
                Categoria categoria = categoriaRepository.getById(Integer.parseInt(categoriaId));

                categoria.getUsuarioList().add(newUser);

                this.categoriaRepository.save(categoria);

                // Añadimos las nuevas categorias al usuario
                newUser.getCategoriaList().add(categoria);
            }

            this.usuarioRepository.save(newUser);

        }
    }

    public UsuarioDTO crearUsuario (String nombre, String apellidos, String correo,
                                    String password, String domicilio, String ciudad, String sexo,
                                    Date fechaNacimiento, Double saldo, String strTipoUsuario, String[] categorias) { // Luis
        Usuario usuario = new Usuario();

        this.rellenarUsuario(usuario, nombre, apellidos, correo, password, domicilio, ciudad, sexo, fechaNacimiento, saldo, strTipoUsuario);

        usuario.setCategoriaList(new ArrayList<>());

        this.usuarioRepository.save(usuario);

        this.actualizarRolUsuario(usuario);

        this.rellenarCategoriasUsuario(categorias, usuario);

        return usuario.toDTO();
    }

    public UsuarioDTO modificarUsuario (Integer id, String nombre, String apellidos, String correo,
                                        String password, String domicilio, String ciudad, String sexo,
                                        Date fechaNacimiento, Double saldo, String strTipoUsuario, String[] categorias) { // Luis
        Usuario usuario = this.buscarUsuarioById(id);

        RolUsuario rolAntiguo = usuario.getRol();
        rolAntiguo.getUsuarioList().remove(usuario);
        this.rolUsuarioRepository.save(rolAntiguo);

        this.rellenarUsuario(usuario, nombre, apellidos, correo, password, domicilio, ciudad, sexo, fechaNacimiento, saldo, strTipoUsuario);

        this.usuarioRepository.save(usuario);

        this.actualizarRolUsuario(usuario);

        this.rellenarCategoriasUsuario(categorias, usuario);

        return usuario.toDTO();
    }

    public void modificarListaMensajesUsuario(Integer id, List<Mensaje> mensajes){ // angel

        Usuario usuario = this.buscarUsuarioById(id);

        usuario.setMensajeList(mensajes);

        this.usuarioRepository.save(usuario);

    }

    public void anadirGrupoAListaGruposUsuario(Integer idUsuario, Integer idGrupo){ // angel

        Usuario usuario = this.buscarUsuarioById(idUsuario);
        Grupo grupo = this.grupoRepository.getById(idGrupo);

        List<Grupo> grupoList = usuario.getGrupoList();
        grupoList.add(grupo);
        usuario.setGrupoList(grupoList);

        this.usuarioRepository.save(usuario);

    }

    public void eliminarGrupoAListaGruposUsuario(Integer idUsuario, Integer idGrupo){ // angel

        Usuario usuario = this.buscarUsuarioById(idUsuario);
        Grupo grupo = this.grupoRepository.getById(idGrupo);

        List<Grupo> grupoList = usuario.getGrupoList();
        grupoList.remove(grupo);
        usuario.setGrupoList(grupoList);

        this.usuarioRepository.save(usuario);

    }

    public UsuarioDTO comprobarCredenciales (String correo, String password) { // Luis
        UsuarioDTO userdto = null;

        try {
            Usuario user = usuarioRepository.comprobarUsuario(correo, password);
            if (user != null) userdto = user.toDTO();
        } catch(Exception ex){
            userdto = null;
        }

        return userdto;
    }

    public UsuarioDTO manejoFavoritos(int idProducto, int idUsuario){ //Miguel Oña Guerrero

        Usuario usuario = this.usuarioRepository.findById(idUsuario).orElse(null);
        Producto producto = this.productoRepository.findById(idProducto).orElse(null);

        if(usuario.getProductoList() != null && usuario.getProductoList().contains(producto)){
            usuario.getProductoList().remove(producto);
            producto.getUsuarioList().remove(usuario);

            //this.eliminarUsuarioAGrupoADarleFavoritoAProducto(idProducto, idUsuario); //Marketing
        }else{
            usuario.getProductoList().add(producto);
            producto.getUsuarioList().add(usuario);

            //this.anadirUsuarioAGrupoADarleFavoritoAProducto(idProducto, idUsuario); //Marketing
        }

        this.usuarioRepository.save(usuario);
        this.productoRepository.save(producto);

        return usuario.toDTO();
    }

    public UsuarioDTO sumarSaldo(double cantidad, int idUsuario){ //Miguel Oña Guerrero

        Usuario usuario = this.usuarioRepository.findById(idUsuario).orElse(null);

        if(usuario != null){
            double saldo = usuario.getSaldo();
            saldo += cantidad;
            usuario.setSaldo(saldo);

            usuarioRepository.save(usuario);
        }

        return usuario.toDTO();
    }

    /*public void anadirUsuarioAGrupoADarleFavoritoAProducto(int idProducto, int idUsuario){ // angel

        this.grupoService.comprobarExistenciaGrupoPorNombre("Grupo_"+idProducto);
        //Integer idGrupo = this.grupoService.buscarGruposPorNombre("Grupo_"+idProducto).get(0).getId();
        Integer idGrupo = this.grupoFacade.findGrupoByGrupoNombreExtricto("Grupo_"+idProducto).get(0).getId();

        this.grupoService.anadirUsuarioAListaUsuariosGrupo(idUsuario, idGrupo);
        this.anadirGrupoAListaGruposUsuario(idUsuario, idGrupo);

    }*/

    /*public void eliminarUsuarioAGrupoADarleFavoritoAProducto(int idProducto, int idUsuario){ // angel

        Integer idGrupo = this.grupoFacade.findGrupoByGrupoNombreExtricto("Grupo_"+idProducto).get(0).getId();
        this.grupoService.eliminarUsuarioAListaUsuariosGrupo(idUsuario, idGrupo);
        this.eliminarGrupoAListaGruposUsuario(idUsuario, idGrupo);

    }*/

}

