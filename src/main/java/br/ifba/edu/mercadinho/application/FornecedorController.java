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

import br.ifba.edu.mercadinho.infra.service.FornecedorService;
import br.ifba.edu.mercadinho.model.dto.FornecedorDto;
import br.ifba.edu.mercadinho.model.entities.Fornecedor;

@RequestMapping("/fornecedor")
@RestController
@CrossOrigin("http://localhost:3000")
public class FornecedorController {

    private final FornecedorService fornecedorService;

    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody FornecedorDto fornecedorDto) {
        fornecedorService.cadastrar(fornecedorDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<List<Fornecedor>> obterTodos() {
        return ResponseEntity.ok(fornecedorService.obterTodos());
    }

    @PatchMapping
    public ResponseEntity<Fornecedor> atualizar(@RequestBody Fornecedor fornecedor) {
        return ResponseEntity.ok(fornecedorService.atualizar(fornecedor));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        fornecedorService.deletar(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
