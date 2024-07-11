package br.ifba.edu.mercadinho.infra.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.ifba.edu.mercadinho.infra.repository.CategoriaRepository;
import br.ifba.edu.mercadinho.infra.repository.ProdutoRepository;
import br.ifba.edu.mercadinho.model.dto.ProdutoDto;
import br.ifba.edu.mercadinho.model.entities.Categoria;
import br.ifba.edu.mercadinho.model.entities.Produto;
import br.ifba.edu.mercadinho.model.exception.impl.ConflictException;
import br.ifba.edu.mercadinho.model.exception.impl.NotFoundException;
import br.ifba.edu.mercadinho.model.req.AtualizarProdutoReq;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public Produto cadastrar(ProdutoDto produto) {
        Categoria categoria = categoriaRepository.findById(produto.categoriaId())
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada!"));
        Optional<Produto> foundProduto = produtoRepository.findByCodigo(produto.cod());
        if (foundProduto.isEmpty()) {
            return produtoRepository
                    .save(new Produto(null,
                            produto.nome(),
                            produto.marca(),
                            categoria,
                            produto.preco(),
                            produto.cod()));
        } else {
            throw new ConflictException("Produto já cadastrado com o código " + produto.cod());
        }
    }

    public List<Produto> obterProdutos(String search) {
        return produtoRepository.findAllByNomeIgnoreCaseStartingWith(search);
    }

    public Produto atualizarProduto(AtualizarProdutoReq req) {
        Produto produto = produtoRepository
                .findById(req.id())
                .orElseThrow(() -> new NotFoundException("Produto não encontrada!"));

        Optional<Produto> foundProduto = produtoRepository
                .findByCodigo(req.codigo());

        if (foundProduto.isPresent() && req.id() != foundProduto.get().getId()) {
            throw new ConflictException("Produto já cadastrado com o código " + req.codigo());
        }

        Categoria categoria = categoriaRepository
                .findById(req.categoriaId())
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada!"));

        produto.setCategoria(categoria);
        produto.setPreco(req.preco());
        produto.setCodigo(req.codigo());
        produto.setMarca(req.marca());
        produto.setNome(req.nome());
        return produtoRepository.save(produto);
    }

    public void deletarProduto(Integer id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Produto não encontrada!"));
        produtoRepository.delete(produto);
    }
}
