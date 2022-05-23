package es.taw.swishbay.service;

import es.taw.swishbay.dao.CategoriaRepository;
import es.taw.swishbay.dto.CategoriaDTO;
import es.taw.swishbay.entity.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luis
 */

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

    public List<CategoriaDTO> listarCategorias (String filtroNombre) { // Luis
        List<Categoria> categorias = null;

        if (filtroNombre == null || filtroNombre.isEmpty()) {
            categorias = this.categoriaRepository.findAll();
        } else {
            categorias = this.categoriaRepository.findByNombre(filtroNombre);
        }

        return this.listaEntityADTO(categorias);
    }

    public CategoriaDTO buscarCategoria (Integer id) { // Luis
        Categoria categoria = this.categoriaRepository.getById(id);
        return categoria.toDTO();
    }

    public void borrarCategoria (Integer id) { // Luis
        Categoria categoria = this.categoriaRepository.getById(id);

        this.categoriaRepository.delete(categoria);
    }

    private void rellenarCategoria(Categoria categoria, String nombre, String descripcion) { // Luis
        categoria.setNombre(nombre);
        categoria.setDescripcion(descripcion);
    }

    public void crearCategoria (String nombre, String descripcion) { // Luis
        Categoria categoria = new Categoria();

        this.rellenarCategoria(categoria, nombre, descripcion);

        this.categoriaRepository.save(categoria);
    }

    public void modificarCategoria (Integer id, String nombre, String descripcion) { // Luis
        Categoria categoria = this.categoriaRepository.getById(id);

        this.rellenarCategoria(categoria, nombre, descripcion);

        this.categoriaRepository.save(categoria);
    }
}
