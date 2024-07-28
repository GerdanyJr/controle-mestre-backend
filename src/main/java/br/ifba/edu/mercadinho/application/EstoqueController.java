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

import br.ifba.edu.mercadinho.infra.service.EstoqueService;
import br.ifba.edu.mercadinho.model.dto.EstoqueDto;
import br.ifba.edu.mercadinho.model.entities.Estoque;
import br.ifba.edu.mercadinho.model.req.AtualizarEstoqueReq;

@RequestMapping("/estoque")
@RestController
@CrossOrigin("http://localhost:3000")
public class EstoqueController {
    private EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody EstoqueDto estoqueDto) {
        estoqueService.cadastrar(estoqueDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<List<Estoque>> obterTodos() {
        return ResponseEntity.ok(estoqueService.obterTodos());
    }

    @PatchMapping
    public ResponseEntity<Estoque> atualizar(@RequestBody AtualizarEstoqueReq atualizarEstoqueReq) {
        return ResponseEntity.ok(estoqueService.atualizar(atualizarEstoqueReq));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Integer id) {
        System.out.println(id);
        estoqueService.deletar(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
