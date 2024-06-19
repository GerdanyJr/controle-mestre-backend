package br.ifba.edu.mercadinho.application;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ifba.edu.mercadinho.infra.service.ProdutoService;
import br.ifba.edu.mercadinho.model.entities.Produto;
import br.ifba.edu.mercadinho.model.req.AtualizarProdutoReq;
import br.ifba.edu.mercadinho.model.req.ProdutoReq;

@RequestMapping("/produtos")
@CrossOrigin("http://localhost:3000")
@RestController
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Produto> cadastrar(@RequestBody ProdutoReq produtoReq) {
        return ResponseEntity.ok(produtoService.cadastrar(produtoReq));
    }

    @GetMapping
    public ResponseEntity<List<Produto>> obterProdutos(
            @RequestParam(required = false, defaultValue = "") String search) {
        return ResponseEntity.ok(produtoService.obterProdutos(search));
    }

    @PatchMapping
    public ResponseEntity<Produto> atualizarProduto(@RequestBody AtualizarProdutoReq atualizarProdutoReq) {
        return ResponseEntity.ok(produtoService.atualizarProduto(atualizarProdutoReq));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable("id") Integer id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
