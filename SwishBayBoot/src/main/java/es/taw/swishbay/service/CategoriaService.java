package es.taw.swishbay.service;

import es.taw.swishbay.dao.CategoriaRepository;
import es.taw.swishbay.dto.CategoriaDTO;
import es.taw.swishbay.entity.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriaService {

    private CategoriaRepository categoriaRepository;

    @Autowired
    public void setCategoriaRepository(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    private List<CategoriaDTO> listaEntityADTO (List<Categoria> lista) { // Luis
        List<CategoriaDTO> listaDTO = null;
        if (lista != null) {
            listaDTO = new ArrayList<>();
            for (Categoria categoria : lista) {
                listaDTO.add(categoria.toDTO());
            }
        }
        return listaDTO;
    }

    public List<CategoriaDTO> listarCategorias () { // Luis
        List<Categoria> lista = this.categoriaRepository.findAll();
        return this.listaEntityADTO(lista);
    }
}
