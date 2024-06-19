package br.ifba.edu.mercadinho.infra.service;

import java.util.List;
import org.springframework.stereotype.Service;

import br.ifba.edu.mercadinho.infra.repository.CategoriaRepository;
import br.ifba.edu.mercadinho.infra.repository.ProdutoRepository;
import br.ifba.edu.mercadinho.model.entities.Categoria;
import br.ifba.edu.mercadinho.model.entities.Produto;
import br.ifba.edu.mercadinho.model.req.AtualizarProdutoReq;
import br.ifba.edu.mercadinho.model.req.ProdutoReq;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public Produto cadastrar(ProdutoReq produto) {
        Categoria categoria = categoriaRepository.findById(produto.categoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));
        return produtoRepository
                .save(new Produto(null, produto.nome(), produto.marca(), categoria, produto.preco(), produto.cod()));
    }

    public List<Produto> obterProdutos(String search) {
        return produtoRepository.findAllByNomeStartingWith(search);
    }

    public Produto atualizarProduto(AtualizarProdutoReq req) {
        Produto produto = produtoRepository.findById(req.id())
                .orElseThrow(() -> new RuntimeException("Produto não encontrada!"));
        Categoria categoria = categoriaRepository.findById(req.categoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada!"));
        produto.setCategoria(categoria);
        produto.setPreco(req.preco());
        produto.setCodigo(req.codigo());
        produto.setMarca(req.marca());
        produto.setNome(req.nome());
        return produtoRepository.save(produto);
    }

    public void deletarProduto(Integer id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrada!"));
        produtoRepository.delete(produto);
    }
}
