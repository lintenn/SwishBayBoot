package es.taw.swishbay.service;

import es.taw.swishbay.dao.PujaRepository;
import es.taw.swishbay.dto.ProductoDTO;
import es.taw.swishbay.dto.PujaDTO;
import es.taw.swishbay.entity.Puja;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Miguel Oña Guerrero
 */

@Service
public class PujaService {

    private PujaRepository pujaRepository;

    @Autowired
    public void setPujaRepository(PujaRepository pujaRepository){
        this.pujaRepository = pujaRepository;
    }

    private List<PujaDTO> listaEntityADTO (List<Puja> lista) { // Miguel Oña Guerrero
        List<PujaDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Puja puja : lista) {
                listaDTO.add(puja.toDTO());
            }
        }
        return listaDTO;
    }

    public PujaDTO buscarMayorPuja(Integer idProducto){ //Miguel Oña Guerrero
        Puja puja = pujaRepository.findMayor(idProducto);

        return (puja == null) ? null : puja.toDTO();
    }

    public List<PujaDTO> buscarMayoresPujas(List<ProductoDTO> productos){ //Miguel Oña Guerrero
        List<PujaDTO> pujas = new ArrayList();

        for(ProductoDTO producto : productos){
            pujas.add(this.buscarMayorPuja(producto.getId()));
        }

        return pujas;
    }
}
