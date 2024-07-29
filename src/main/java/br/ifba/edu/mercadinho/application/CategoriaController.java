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
import org.springframework.web.bind.annotation.RestController;

import br.ifba.edu.mercadinho.infra.service.CategoriaService;
import br.ifba.edu.mercadinho.model.dto.CategoriaDto;
import br.ifba.edu.mercadinho.model.entities.Categoria;

@RequestMapping("/categoria")
@RestController
@CrossOrigin("http://localhost:3000")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<Categoria> cadastrar(@RequestBody CategoriaDto categoriaReq) {
        return ResponseEntity.ok(categoriaService.cadastrar(categoriaReq));
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> obter() {
        return ResponseEntity.ok(categoriaService.obter());
    }

    @PatchMapping
    public ResponseEntity<Categoria> atualizar(@RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.atualizar(categoria));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        categoriaService.deletar(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
