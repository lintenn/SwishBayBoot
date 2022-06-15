package es.taw.swishbay.service;

import es.taw.swishbay.dao.GrupoRepository;
import es.taw.swishbay.dao.MensajeRepository;
import es.taw.swishbay.dao.UsuarioRepository;
import es.taw.swishbay.dto.MensajeDTO;
import es.taw.swishbay.entity.Grupo;
import es.taw.swishbay.entity.Mensaje;
import es.taw.swishbay.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author angel
 */

@Service
public class MensajeService {

    private MensajeRepository mensajeRepository;
    private UsuarioRepository usuarioRepository;
    private GrupoRepository grupoRepository;

    @Autowired
    public void setUsuarioRepository(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Autowired
    public void setMensajeRepository(MensajeRepository mensajeRepository){
        this.mensajeRepository = mensajeRepository;
    }

    @Autowired
    public void setGrupoRepository(GrupoRepository grupoRepository){
        this.grupoRepository = grupoRepository;
    }

    private List<MensajeDTO> listaMensajesEntityADTO(List<Mensaje> mensajes){ // angel

        List<MensajeDTO> mensajesDTO = null;

        if(mensajes != null) {
            mensajesDTO = new ArrayList<>();
            for(Mensaje mensaje : mensajes) {
                mensajesDTO.add(mensaje.toDTO());
            }
        }

        return mensajesDTO;

    }

    public List<MensajeDTO> listarMensajesDeUnUsuarioPorAsuntoPorMensajes(String asunto, Integer idUsuario, List<Integer> ids){

        List<Mensaje> mensajes = this.mensajeRepository.findByAsuntoAndIdUserByMessages(idUsuario, asunto, ids);

        return this.listaMensajesEntityADTO(mensajes);

    }

    public List<MensajeDTO> listarMensajesDeUnUsuarioPorContenidoPorMensajes(String contenido, Integer idUsuario, List<Integer> ids){

        List<Mensaje> mensajes = this.mensajeRepository.findByContenidoAndIdUserByMessages(idUsuario, contenido, ids);

        return this.listaMensajesEntityADTO(mensajes);

    }

    public void crearMensaje(Integer idGrupo, Integer idMarketing, String asunto, String contenido){ // angel

        Mensaje mensaje = new Mensaje();
        Grupo grupo = this.grupoRepository.findById(idGrupo).orElse(null);
        Usuario marketing = this.usuarioRepository.findById(idMarketing).orElse(null);
        mensaje.setGrupo(grupo);
        mensaje.setMarketing(marketing);
        mensaje.setFecha(new Date());
        mensaje.setUsuarioList(new ArrayList<Usuario>());
        this.rellenarMensaje(mensaje, asunto, contenido);

        this.mensajeRepository.save(mensaje);

        for(Usuario usuario : grupo.getUsuarioList()){
            List<Mensaje> mensajeList = usuario.getMensajeList();
            mensajeList.add(mensaje);
            this.modificarListaMensajesUsuario(usuario.getId(), mensajeList);
        };
    }

    public void modificarListaMensajesUsuario(Integer id, List<Mensaje> mensajes){ // angel

        Usuario usuario = this.usuarioRepository.findById(id).orElse(null);

        usuario.setMensajeList(mensajes);

        this.usuarioRepository.save(usuario);

    }

    private void rellenarMensaje(Mensaje mensaje, String asunto, String contenido){ // angel

        mensaje.setAsunto(asunto);
        mensaje.setContenido(contenido);

    }

}
