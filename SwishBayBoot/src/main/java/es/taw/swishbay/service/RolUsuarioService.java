package es.taw.swishbay.service;

import es.taw.swishbay.dao.RolUsuarioRepository;
import es.taw.swishbay.dto.RolUsuarioDTO;
import es.taw.swishbay.entity.RolUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class RolUsuarioService.
 * @author Luis
 */

@Service
public class RolUsuarioService {

    @Autowired
    private RolUsuarioRepository rolUsuarioRepository;

    private List<RolUsuarioDTO> listaEntityADTO (List<RolUsuario> lista) { // Luis
        List<RolUsuarioDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (RolUsuario rol : lista) {
                listaDTO.add(rol.toDTO());
            }
        }
        return listaDTO;
    }


    public List<RolUsuarioDTO> listarRoles () { // Luis
        List<RolUsuario> lista = this.rolUsuarioRepository.findAll();
        return this.listaEntityADTO(lista);
    }

}
