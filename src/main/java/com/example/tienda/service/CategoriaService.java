package com.example.tienda.service;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.tienda.dto.CategoriaRequest;
import com.example.tienda.dto.CategoriaResponse;
import com.example.tienda.exceotion.CategoriaException;
import com.example.tienda.model.Categoria;
import com.example.tienda.repository.CategoriaRepository;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    private CategoriaResponse convertirCategoria(Categoria categoria) {
        return new CategoriaResponse(categoria.getId(), categoria.getNombre(), categoria.getDescripcion());
    }

    private Categoria convertCategoria(CategoriaRequest categoria) {
        Categoria cat = new Categoria();
        cat.setNombre(categoria.getNombre());
        cat.setDescripcion(categoria.getDescripcion());

        return cat;
    }

    public CategoriaResponse guardarCategoria(CategoriaRequest categoria) {

        try {
            return convertirCategoria(this.categoriaRepository.save(convertCategoria(categoria)));
        } catch (DataAccessException e) {
            throw new CategoriaException("morite");
        }
    }

    public List<CategoriaResponse> getAll() {
        List<Categoria> categorias = this.categoriaRepository.findAll();
        return categorias.stream().map(x -> new CategoriaResponse(x.getId(), x.getNombre(), x.getDescripcion()))
                .toList();
    }

    public CategoriaResponse getById(Long id) {
        Categoria cat = this.categoriaRepository.findById(id).orElseThrow(() -> new CategoriaException("error con id"));
        return convertirCategoria(cat);
    }

    public CategoriaResponse updateCategoria(Long id, CategoriaRequest categoria) {

        Categoria updateCat = this.categoriaRepository.findById(id)
                .orElseThrow(() -> new CategoriaException("la categoria con id" + id + " no existe"));
        updateCat.setNombre(categoria.getNombre());
        updateCat.setDescripcion(categoria.getDescripcion());

        return convertirCategoria(this.categoriaRepository.save(updateCat));
    }

    public void deleteCategoria(Long id) {
        if (this.categoriaRepository.existsById(id)) {
            this.categoriaRepository.deleteById(id);
            throw new CategoriaException("la categoria se elimino correctamente");
        }
        throw new CategoriaException("la categoria no existe ");
    }
}
