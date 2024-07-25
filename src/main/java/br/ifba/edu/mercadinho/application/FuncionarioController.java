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

import br.ifba.edu.mercadinho.infra.service.FuncionarioService;
import br.ifba.edu.mercadinho.model.dto.FuncionarioDto;
import br.ifba.edu.mercadinho.model.entities.Funcionario;

@RequestMapping("funcionario")
@RestController
@CrossOrigin("http://localhost:3000")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping
    public ResponseEntity<List<Funcionario>> obterTodos() {
        return ResponseEntity.ok(funcionarioService.obterTodos());
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody FuncionarioDto dto) {
        funcionarioService.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping
    public ResponseEntity<Funcionario> atualizar(@RequestBody Funcionario funcionario) {
        return ResponseEntity.ok(funcionarioService.atualizar(funcionario));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        funcionarioService.deletar(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
