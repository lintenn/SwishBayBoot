package es.taw.swishbay.service;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import es.taw.swishbay.dao.CategoriaRepository;
import es.taw.swishbay.dao.RolUsuarioRepository;
import es.taw.swishbay.dao.UsuarioRepository;
import es.taw.swishbay.dto.UsuarioDTO;
import es.taw.swishbay.entity.Categoria;
import es.taw.swishbay.entity.RolUsuario;
import es.taw.swishbay.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author angel 95%, Luis 5%
 */
@Service
public class UsuarioCompradorService {

    private UsuarioRepository usuarioRepository;
    private RolUsuarioRepository rolUsuarioRepository;
    private CategoriaRepository categoriaRepository;
    
    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository){
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

    public List<UsuarioDTO> buscarPorCompradorVendedor(){ // angel

        List<Usuario> usuarios = this.usuarioRepository.findByCompradorVendedor();

        return this.listaEntityADTO(usuarios);

    }

    public List<UsuarioDTO> buscarPorCompradorVendedorPorNombre(String nombre){ // angel

        List<Usuario> usuarios = this.usuarioRepository.findByCompradorVendedorByName(nombre);

        return this.listaEntityADTO(usuarios);

    }

    public Integer buscarUsuarioMarketing(){ // angel

        Usuario usuario = this.usuarioRepository.findByMarketing().get(0);

        if(usuario == null){
            this.crearUsuario("Usuario marketing", "Numero 1", "usuarioMarketingNumero1@gmail.com", "123456", "", "", "masc", new Date(10/1/2000), 0.0, 3, null);
        }

        return this.usuarioRepository.findByMarketing().get(0).getId();
    }

    public List<UsuarioDTO> buscarPorCompradorVendedorPorCorreo(String correo){// angel

        List<Usuario> usuarios = this.usuarioRepository.findByCompradorVendedorByCorreo(correo);

        return this.listaEntityADTO(usuarios);

    }

    public List<UsuarioDTO> buscarPorCompradorVendedorPorApellidos(String apellidos){// angel

        List<Usuario> usuarios = this.usuarioRepository.findByCompradorVendedorByApellidos(apellidos);

        return this.listaEntityADTO(usuarios);

    }

    public List<UsuarioDTO> buscarPorCompradorVendedorPorCiudad(String ciudad){// angel

        List<Usuario> usuarios = this.usuarioRepository.findByCompradorVendedorByCiudad(ciudad);

        return this.listaEntityADTO(usuarios);

    }

    public List<UsuarioDTO> buscarPorCompradorVendedorPorDomicilio(String domicilio){// angel

        List<Usuario> usuarios = this.usuarioRepository.findByCompradorVendedorByDomicilio(domicilio);

        return this.listaEntityADTO(usuarios);

    }

    public List<UsuarioDTO> buscarPorCompradorVendedorPorSexo(String sexo){// angel

        List<Usuario> usuarios = this.usuarioRepository.findByCompradorVendedorBySexo(sexo);

        return this.listaEntityADTO(usuarios);

    }

    public List<UsuarioDTO> buscarPorCompradorVendedorPorSaldoDesde(Integer desde, List<Integer> ids){// angel

        List<Usuario> usuarios = this.usuarioRepository.findByCompradorVendedorBySaldoDesde(desde, ids);

        return this.listaEntityADTO(usuarios);

    }

    public List<UsuarioDTO> buscarPorCompradorVendedorPorSaldoHasta(Integer desde, List<Integer> ids){// angel

        List<Usuario> usuarios = this.usuarioRepository.findByCompradorVendedorBySaldoHasta(desde, ids);

        return this.listaEntityADTO(usuarios);

    }

    public List<UsuarioDTO> buscarPorCompradorVendedorPorNombreQueNoPertencenAUnGrupo(String nombre, List<Integer> ids){ // angel

        List<Usuario> usuarios = this.usuarioRepository.findByCompradorVendedorByNameQueNoPertencenAUnGrupo(nombre, ids);

        return this.listaEntityADTO(usuarios);

    }

    public List<UsuarioDTO> buscarPorCompradorVendedorPorCorreoQueNoPertencenAUnGrupo(String correo, List<Integer> ids){// angel

        List<Usuario> usuarios = this.usuarioRepository.findByCompradorVendedorByCorreoQueNoPertencenAUnGrupo(correo, ids);

        return this.listaEntityADTO(usuarios);

    }

    public List<UsuarioDTO> buscarPorCompradorVendedorPorApellidosQueNoPertencenAUnGrupo(String apellidos, List<Integer> ids){// angel

        List<Usuario> usuarios = this.usuarioRepository.findByCompradorVendedorByApellidosQueNoPertencenAUnGrupo(apellidos, ids);

        return this.listaEntityADTO(usuarios);

    }

    public List<UsuarioDTO> buscarPorCompradorVendedorPorCiudadQueNoPertencenAUnGrupo(String ciudad, List<Integer> ids){// angel

        List<Usuario> usuarios = this.usuarioRepository.findByCompradorVendedorByCiudadQueNoPertencenAUnGrupo(ciudad, ids);

        return this.listaEntityADTO(usuarios);

    }

    public List<UsuarioDTO> buscarPorCompradorVendedorPorDomicilioQueNoPertencenAUnGrupo(String domicilio, List<Integer> ids){// angel

        List<Usuario> usuarios = this.usuarioRepository.findByCompradorVendedorByDomicilioQueNoPertencenAUnGrupo(domicilio, ids);

        return this.listaEntityADTO(usuarios);

    }

    public List<UsuarioDTO> buscarPorCompradorVendedorPorSexoQueNoPertencenAUnGrupo(String sexo, List<Integer> ids){// angel

        List<Usuario> usuarios = this.usuarioRepository.findByCompradorVendedorBySexoQueNoPertencenAUnGrupo(sexo, ids);

        return this.listaEntityADTO(usuarios);

    }

    public List<UsuarioDTO> buscarPorCompradorVendedorPorSaldoDesdeQueNoPertencenAUnGrupo(Integer desde, List<Integer> ids, List<Integer> idsGrupo){// angel

        List<Usuario> usuarios = this.usuarioRepository.findByCompradorVendedorBySaldoDesdeQueNoPertencenAUnGrupo(desde, ids, idsGrupo);

        return this.listaEntityADTO(usuarios);

    }

    public List<UsuarioDTO> buscarPorCompradorVendedorPorSaldoHastaQueNoPertencenAUnGrupo(Integer desde, List<Integer> ids, List<Integer> idsGrupo){// angel

        List<Usuario> usuarios = this.usuarioRepository.findByCompradorVendedorBySaldoHastaQueNoPertencenAUnGrupo(desde, ids, idsGrupo);

        return this.listaEntityADTO(usuarios);

    }

    public List<UsuarioDTO> buscarPorCompradorVendedorPorNombreQuePertencenAUnGrupo(String nombre, List<Integer> ids){ // angel

        List<Usuario> usuarios = this.usuarioRepository.findByCompradorVendedorByNameQuePertencenAUnGrupo(nombre, ids);

        return this.listaEntityADTO(usuarios);

    }

    public List<UsuarioDTO> buscarPorCompradorVendedorPorCorreoQuePertencenAUnGrupo(String correo, List<Integer> ids){// angel

        List<Usuario> usuarios = this.usuarioRepository.findByCompradorVendedorByCorreoQuePertencenAUnGrupo(correo, ids);

        return this.listaEntityADTO(usuarios);

    }

    public List<UsuarioDTO> buscarPorCompradorVendedorPorApellidosQuePertencenAUnGrupo(String apellidos, List<Integer> ids){// angel

        List<Usuario> usuarios = this.usuarioRepository.findByCompradorVendedorByApellidosQuePertencenAUnGrupo(apellidos, ids);

        return this.listaEntityADTO(usuarios);

    }

    public List<UsuarioDTO> buscarPorCompradorVendedorPorCiudadQuePertencenAUnGrupo(String ciudad, List<Integer> ids){// angel

        List<Usuario> usuarios = this.usuarioRepository.findByCompradorVendedorByCiudadQuePertencenAUnGrupo(ciudad, ids);

        return this.listaEntityADTO(usuarios);

    }

    public List<UsuarioDTO> buscarPorCompradorVendedorPorDomicilioQuePertencenAUnGrupo(String domicilio, List<Integer> ids){// angel

        List<Usuario> usuarios = this.usuarioRepository.findByCompradorVendedorByDomicilioQuePertencenAUnGrupo(domicilio, ids);

        return this.listaEntityADTO(usuarios);

    }

    public List<UsuarioDTO> buscarPorCompradorVendedorPorSexoQuePertencenAUnGrupo(String sexo, List<Integer> ids){// angel

        List<Usuario> usuarios = this.usuarioRepository.findByCompradorVendedorBySexoQuePertencenAUnGrupo(sexo, ids);

        return this.listaEntityADTO(usuarios);

    }

    public List<UsuarioDTO> buscarPorCompradorVendedorPorSaldoDesdeQuePertencenAUnGrupo(Integer desde, List<Integer> ids){// angel

        List<Usuario> usuarios = this.usuarioRepository.findByCompradorVendedorBySaldoDesdeQuePertencenAUnGrupo(desde, ids);

        return this.listaEntityADTO(usuarios);

    }

    public List<UsuarioDTO> buscarPorCompradorVendedorPorSaldoHastaQuePertencenAUnGrupo(Integer desde, List<Integer> ids){// angel

        List<Usuario> usuarios = this.usuarioRepository.findByCompradorVendedorBySaldoHastaQuePertencenAUnGrupo(desde, ids);

        return this.listaEntityADTO(usuarios);

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

    public UsuarioDTO crearUsuario (String nombre, String apellidos, String correo,
                                    String password, String domicilio, String ciudad, String sexo,
                                    Date fechaNacimiento, Double saldo, Integer idTipoUsuario, List<Integer> categorias) { // Luis
        Usuario usuario = new Usuario();

        this.rellenarUsuario(usuario, nombre, apellidos, correo, password, domicilio, ciudad, sexo, fechaNacimiento, saldo, idTipoUsuario);

        usuario.setCategoriaList(new ArrayList<>());

        this.usuarioRepository.save(usuario);

        this.usuarioRepository.flush();

        this.actualizarRolUsuario(usuario);

        this.rellenarCategoriasUsuario(categorias, usuario);

        return usuario.toDTO();
    }

    private void rellenarUsuario (Usuario usuario, String nombre, String apellidos, String correo,
                                  String password, String domicilio, String ciudad, String sexo,
                                  Date fechaNacimiento, Double saldo, Integer idTipoUsuario) { // Luis
        usuario.setNombre(nombre);
        usuario.setApellidos(apellidos);
        usuario.setCorreo(correo);
        usuario.setPassword(password);
        usuario.setDomicilio(domicilio);
        usuario.setCiudad(ciudad);
        usuario.setSexo(sexo);
        usuario.setFechaNacimiento(fechaNacimiento);
        usuario.setSaldo(saldo);

        RolUsuario rol = this.rolUsuarioRepository.getById(idTipoUsuario);
        usuario.setRol(rol);

        // Faltarian las categorias...
    }

    private void actualizarRolUsuario (Usuario newUser) { // Luis
        RolUsuario rol = newUser.getRol();

        if ((!rol.getUsuarioList().contains(newUser))) {
            rol.getUsuarioList().add(newUser);
        } else {
            rol.getUsuarioList().set(rol.getUsuarioList().indexOf(newUser), newUser);
        }
        this.rolUsuarioRepository.save(rol);
    }

    private void rellenarCategoriasUsuario (List<Integer> categorias, Usuario newUser) { // Luis
        // Cargamos las categorias...

        // Borramos al usuario de las categorias anteriores
        for (Categoria categoria : newUser.getCategoriaList()) {
            categoria.getUsuarioList().remove(newUser);
            /*List<Usuario> usuarios = new ArrayList<>();
            for(Usuario user : categoria.getUsuarioList()){
                if(user != newUser){
                    usuarios.add(user);
                }
            }
            categoria.setUsuarioList(usuarios);*/

            this.categoriaRepository.save(categoria);
        }
        // Borramos las categorias anteriores del usuario
        newUser.getCategoriaList().clear();

        if (categorias != null) {
            for (Integer categoriaId : categorias) {
                // Añadimos al usuario en las nuevas categorias
                Categoria categoria = categoriaRepository.getById(categoriaId);

                categoria.getUsuarioList().add(newUser);

                this.categoriaRepository.save(categoria);

                // Añadimos las nuevas categorias al usuario
                newUser.getCategoriaList().add(categoria);
            }

            this.usuarioRepository.save(newUser);

        }
    }

}

