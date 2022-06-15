package es.taw.swishbay.service;

import es.taw.swishbay.dao.MensajeRepository;
import es.taw.swishbay.dto.MensajeDTO;
import es.taw.swishbay.entity.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MensajeService {

    private MensajeRepository mensajeRepository;

    @Autowired
    public void setMensajeRepository(MensajeRepository mensajeRepository){
        this.mensajeRepository = mensajeRepository;
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



}
