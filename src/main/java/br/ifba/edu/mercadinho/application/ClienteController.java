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

import br.ifba.edu.mercadinho.infra.service.ClienteService;
import br.ifba.edu.mercadinho.model.dto.ClienteDto;
import br.ifba.edu.mercadinho.model.entities.Cliente;

@RequestMapping("/cliente")
@RestController
@CrossOrigin("http://localhost:3000")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody ClienteDto clienteDto) {
        return ResponseEntity.ok(clienteService.cadastrar(clienteDto));
    }

    @PatchMapping
    public ResponseEntity<Cliente> atualizarCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.atualizar(cliente));
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> obterTodos() {
        return ResponseEntity.ok(clienteService.obter());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        clienteService.deletar(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
