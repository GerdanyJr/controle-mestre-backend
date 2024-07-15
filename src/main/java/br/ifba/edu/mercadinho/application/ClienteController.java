package br.ifba.edu.mercadinho.application;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
    public ResponseEntity<Cliente> atualizarCliente(@RequestBody ClienteDto clienteDto) {
        return ResponseEntity.ok(clienteService.atualizar(clienteDto));
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> obterTodos() {
        return ResponseEntity.ok(clienteService.obter());
    }
}
