package br.ifba.edu.mercadinho.infra.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.ifba.edu.mercadinho.infra.repository.CategoriaRepository;
import br.ifba.edu.mercadinho.model.dto.CategoriaDto;
import br.ifba.edu.mercadinho.model.entities.Categoria;
import br.ifba.edu.mercadinho.model.exception.impl.ConflictException;
import br.ifba.edu.mercadinho.model.exception.impl.NotFoundException;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria cadastrar(CategoriaDto dto) {
        Optional<Categoria> categoria = categoriaRepository.findByNome(dto.nome());
        if (categoria.isPresent()) {
            throw new ConflictException("Categoria já cadastrada com nome " + dto.nome());
        }
        return categoriaRepository.save(new Categoria(null, dto.nome()));
    }

    public List<Categoria> obter() {
        return categoriaRepository.findAll();
    }

    public Categoria atualizar(Categoria updated) {
        Categoria categoria = categoriaRepository
                .findById(updated.getId())
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada!"));
        if (updated.getId() != categoria.getId()) {
            throw new ConflictException("Categoria já cadastrada com nome " + updated.getNome());
        }
        categoria.setNome(updated.getNome());
        return categoriaRepository.save(categoria);
    }

    public void deletar(Integer id) {
        Categoria categoria = categoriaRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada!"));
        categoriaRepository.delete(categoria);
    }
}
