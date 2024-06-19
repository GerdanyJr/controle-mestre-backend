package br.ifba.edu.mercadinho.application;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ifba.edu.mercadinho.infra.service.CategoriaService;
import br.ifba.edu.mercadinho.model.entities.Categoria;
import br.ifba.edu.mercadinho.model.req.CategoriaReq;

@RequestMapping("/categoria")
@RestController
@CrossOrigin("http://localhost:3000")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<Categoria> cadastrar(@RequestBody CategoriaReq categoriaReq) {
        return ResponseEntity.ok(categoriaService.cadastrar(categoriaReq));
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> obterTodos() {
        return ResponseEntity.ok(categoriaService.obter());
    }
}
