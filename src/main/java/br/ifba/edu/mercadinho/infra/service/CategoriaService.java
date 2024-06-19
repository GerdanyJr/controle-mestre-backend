package br.ifba.edu.mercadinho.infra.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.ifba.edu.mercadinho.infra.repository.CategoriaRepository;
import br.ifba.edu.mercadinho.model.entities.Categoria;
import br.ifba.edu.mercadinho.model.req.CategoriaReq;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria cadastrar(CategoriaReq categoria) {
        return categoriaRepository.save(new Categoria(null, categoria.nome()));
    }

    public List<Categoria> obter() {
        return categoriaRepository.findAll();
    }
}
