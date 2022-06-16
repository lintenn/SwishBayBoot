package es.taw.swishbay.service;

import es.taw.swishbay.dao.GrupoRepository;
import es.taw.swishbay.dao.MensajeRepository;
import es.taw.swishbay.dao.ProductoRepository;
import es.taw.swishbay.dao.UsuarioRepository;
import es.taw.swishbay.dto.GrupoDTO;
import es.taw.swishbay.dto.ProductoDTO;
import es.taw.swishbay.dto.PujaDTO;
import es.taw.swishbay.dto.UsuarioDTO;
import es.taw.swishbay.entity.Grupo;
import es.taw.swishbay.entity.Puja;
import es.taw.swishbay.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author angel
 */

@Service
public class GrupoService {

    private MensajeRepository mensajeRepository;
    private GrupoRepository grupoRepository;
    private MensajeService mensajeService;
    private UsuarioCompradorService usuarioCompradorService;
    private UsuarioRepository usuarioRepository;
    private ProductoRepository productoRepository;

    @Autowired
    public void setUsuarioCompradorService(UsuarioCompradorService usuarioCompradorService){
        this.usuarioCompradorService = usuarioCompradorService;
    }

    @Autowired
    public void setMensajeRepository(MensajeRepository mensajeRepository){
        this.mensajeRepository = mensajeRepository;
    }

    @Autowired
    public void setMensajeService(MensajeService mensajeService){
        this.mensajeService = mensajeService;
    }

    @Autowired
    public void setGrupoRepository(GrupoRepository grupoRepository){
        this.grupoRepository = grupoRepository;
    }

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Autowired
    public void setProductoRepository(ProductoRepository productoRepository){
        this.productoRepository = productoRepository;
    }

    private List<GrupoDTO> listaGruposEntityADTO(List<Grupo> grupos){ // angel

        List<GrupoDTO> gruposDTO = null;

        if(grupos != null) {
            gruposDTO = new ArrayList<>();
            for(Grupo grupo : grupos) {
                gruposDTO.add(grupo.toDTO());
            }
        }

        return gruposDTO;

    }

    public void notificarComienzoPuja(String nombre, ProductoDTO producto){ // angel

        this.comprobarExistenciaGrupoPorNombre(nombre);

        GrupoDTO grupo = this.buscarGruposPorNombre(nombre).get(0);

        this.mensajeService.crearMensaje(grupo.getId(), grupo.getMarketing().getId(), "Inicio de puja de " + producto.getTitulo(), "Se ha iniciado una nueva puja del producto " + producto.getTitulo() + ".");

    }

    public void comprobarExistenciaGrupoPorNombre(String nombre){ // angel

        List<GrupoDTO> grupos = this.listaGruposEntityADTO(this.grupoRepository.findGrupoByGrupoNombreExtricto(nombre));

        if(grupos == null || grupos.isEmpty()){

            this.crearGrupo(nombre, this.usuarioCompradorService.buscarUsuarioMarketing());

        }

    }

    public void crearGrupo(String nombre, Integer idMarketing){ // angel

        Grupo grupo = new Grupo();

        Usuario marketing = this.usuarioRepository.findById(idMarketing).orElse(null);

        this.rellenarGrupo(grupo, nombre, marketing);

        this.grupoRepository.save(grupo);

    }

    private void rellenarGrupo(Grupo grupo, String nombre, Usuario marketing){ // angel

        grupo.setNombre(nombre);
        grupo.setMarketing(marketing);

    }

    public List<GrupoDTO> buscarGruposPorNombre(String nombre){ // angel

        List<Grupo> grupos = this.grupoRepository.findGrupoByGrupoNombre(nombre);

        return this.listaGruposEntityADTO(grupos);

    }

    public void anadirUsuarioAListaUsuariosGrupo(Integer idUsuario, Integer idGrupo){ // angel

        Usuario usuario = this.usuarioRepository.findById(idUsuario).orElse(null);
        Grupo grupo = this.buscarGrupo(idGrupo);

        List<Usuario> usuarioList = grupo.getUsuarioList();
        if(usuarioList == null){
            usuarioList = new ArrayList<>();
        }
        usuarioList.add(usuario);
        grupo.setUsuarioList(usuarioList);

        this.grupoRepository.save(grupo);

    }

    public void eliminarUsuarioAListaUsuariosGrupo(Integer idUsuario, Integer idGrupo){ // angel

        Usuario usuario = this.usuarioRepository.findById(idUsuario).orElse(null);
        Grupo grupo = this.buscarGrupo(idGrupo);

        List<Usuario> usuarioList = grupo.getUsuarioList();
        usuarioList.remove(usuario);
        grupo.setUsuarioList(usuarioList);

        this.grupoRepository.save(grupo);

    }

    private Grupo buscarGrupo(Integer id){ // angel

        Grupo grupo = this.grupoRepository.findById(id).orElse(null);

        return grupo;

    }

    public void notificarFinPuja(String nombre, ProductoDTO producto){ // angel

        GrupoDTO grupo = this.buscarGruposPorNombre(nombre).get(0);

        List<Integer> usuariosGrupo = new ArrayList<>();

        for(UsuarioDTO usuario : grupo.getUsuarioList()){
            usuariosGrupo.add(usuario.getId());
        }

        List<PujaDTO> usuariosPujaNoGrupo = null;

        if(usuariosGrupo == null || usuariosGrupo.isEmpty()){
            usuariosPujaNoGrupo = producto.getPujaList();
        } else {
            usuariosPujaNoGrupo = this.listaPujasEntityADTO(this.productoRepository.findUsersPujaNoGrupo(producto.getId(), usuariosGrupo));
        }

        for(PujaDTO puja : usuariosPujaNoGrupo){

            this.anadirUsuarioAGrupoADarleFavoritoAProducto(producto.getId(), puja.getComprador().getId());

        }

        if(producto.getPujaList() == null || producto.getPujaList().isEmpty()){

            this.mensajeService.crearMensaje(grupo.getId(), grupo.getMarketing().getId(), "Fin de puja de " + producto.getTitulo(), "Se ha terminado la puja del producto " + producto.getTitulo() + ". Nadie ha ganado la puja.");

        } else {

            UsuarioDTO ganadorPuja = producto.getPujaList().get(producto.getPujaList().size() - 1).getComprador();
            this.mensajeService.crearMensaje(grupo.getId(), grupo.getMarketing().getId(), "Fin de puja de " + producto.getTitulo(), "Se ha terminado la puja del producto " + producto.getTitulo() + ". " + ganadorPuja.getNombre() + " " + ganadorPuja.getApellidos() + " ha sido el ganador de la puja.");

        }

        for(PujaDTO puja : usuariosPujaNoGrupo){

            this.eliminarUsuarioAGrupoADarleFavoritoAProducto(producto.getId(), puja.getComprador().getId());

        }

    }

    public void anadirUsuarioAGrupoADarleFavoritoAProducto(int idProducto, int idUsuario){ // angel

        this.comprobarExistenciaGrupoPorNombre("Grupo_"+idProducto);

        Integer idGrupo = this.grupoRepository.findGrupoByGrupoNombreExtricto("Grupo_"+idProducto).get(0).getId();

        this.anadirUsuarioAListaUsuariosGrupo(idUsuario, idGrupo);
        this.anadirGrupoAListaGruposUsuario(idUsuario, idGrupo);

    }

    public void eliminarUsuarioAGrupoADarleFavoritoAProducto(int idProducto, int idUsuario){ // angel

        Integer idGrupo = this.grupoRepository.findGrupoByGrupoNombreExtricto("Grupo_"+idProducto).get(0).getId();
        this.eliminarUsuarioAListaUsuariosGrupo(idUsuario, idGrupo);
        this.eliminarGrupoAListaGruposUsuario(idUsuario, idGrupo);

    }

    public void anadirGrupoAListaGruposUsuario(Integer idUsuario, Integer idGrupo){ // angel

        Usuario usuario = this.usuarioRepository.findById(idUsuario).orElse(null);
        Grupo grupo = this.grupoRepository.getById(idGrupo);

        List<Grupo> grupoList = usuario.getGrupoList();
        grupoList.add(grupo);
        usuario.setGrupoList(grupoList);

        this.usuarioRepository.save(usuario);

    }

    public void eliminarGrupoAListaGruposUsuario(Integer idUsuario, Integer idGrupo){ // angel

        Usuario usuario = this.usuarioRepository.findById(idUsuario).orElse(null);
        Grupo grupo = this.grupoRepository.getById(idGrupo);

        List<Grupo> grupoList = usuario.getGrupoList();
        grupoList.remove(grupo);
        usuario.setGrupoList(grupoList);

        this.usuarioRepository.save(usuario);

    }


    private List<PujaDTO> listaPujasEntityADTO(List<Puja> pujas){ // angel

        List<PujaDTO> pujasDTO = null;

        if(pujas != null) {
            pujasDTO = new ArrayList<>();
            for(Puja puja : pujas) {
                pujasDTO.add(puja.toDTO());
            }
        }

        return pujasDTO;

    }

    public List<GrupoDTO> buscarTodosGrupos(){ // angel

        List<Grupo> grupos = this.grupoRepository.findAll();

        return this.listaGruposEntityADTO(grupos);

    }

    public GrupoDTO buscarGrupoDTO(Integer id){ // angel

        GrupoDTO grupoDTO = this.buscarGrupo(id).toDTO();

        return grupoDTO;

    }

    public void editarGrupo(Integer id, String nombre, Integer idMarketing){ // angel

        Grupo grupo = this.buscarGrupo(id);

        Usuario marketing = this.usuarioRepository.findById(idMarketing).orElse(null);

        this.rellenarGrupo(grupo, nombre, marketing);

        this.grupoRepository.save(grupo);

    }

}
