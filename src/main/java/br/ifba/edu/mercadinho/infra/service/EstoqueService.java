package br.ifba.edu.mercadinho.infra.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.ifba.edu.mercadinho.infra.repository.EstoqueRepository;
import br.ifba.edu.mercadinho.infra.repository.ProdutoRepository;
import br.ifba.edu.mercadinho.model.dto.EstoqueDto;
import br.ifba.edu.mercadinho.model.entities.Estoque;
import br.ifba.edu.mercadinho.model.entities.Produto;
import br.ifba.edu.mercadinho.model.exception.impl.ConflictException;
import br.ifba.edu.mercadinho.model.exception.impl.NotFoundException;
import br.ifba.edu.mercadinho.model.req.AtualizarEstoqueReq;

@Service
public class EstoqueService {
    private final EstoqueRepository estoqueRepository;
    private final ProdutoRepository produtoRepository;

    public EstoqueService(EstoqueRepository estoqueRepository, ProdutoRepository produtoRepository) {
        this.estoqueRepository = estoqueRepository;
        this.produtoRepository = produtoRepository;
    }

    public void cadastrar(EstoqueDto req) {
        Optional<Estoque> byNameEstoque = estoqueRepository.findByNome(req.nome());
        if (byNameEstoque.isPresent()) {
            throw new ConflictException("Estoque já cadastrado com nome " + req.nome());
        }
        Produto produto = produtoRepository.findById(req.produtoId())
                .orElseThrow(() -> new NotFoundException("Produto não encontrado!"));
        estoqueRepository.save(new Estoque(null, produto, req.nome(), req.quantidade()));
    }

    public List<Estoque> obterTodos() {
        return estoqueRepository.findAll();
    }

    public Estoque atualizar(AtualizarEstoqueReq updated) {
        Optional<Estoque> byNameEstoque = estoqueRepository.findById(updated.id());
        if (byNameEstoque.isPresent()) {
            throw new ConflictException("Estoque já cadastrado com nome " + updated.nome());
        }
        Estoque foundEstoque = estoqueRepository
                .findById(updated.id())
                .orElseThrow(() -> new NotFoundException("Estoque não encontrado com id informado!"));
        Produto produto = produtoRepository.findById(updated.produtoId())
                .orElseThrow(() -> new NotFoundException("Produto não encontrado!"));

        foundEstoque.setNome(updated.nome());
        foundEstoque.setProduto(produto);
        foundEstoque.setQuantidade(updated.quantidade());
        return estoqueRepository.save(foundEstoque);
    }

    public void deletar(Integer id) {
        Estoque foundEstoque = estoqueRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Estoque não encontrado com id informado!"));
        estoqueRepository.delete(foundEstoque);
    }

}
