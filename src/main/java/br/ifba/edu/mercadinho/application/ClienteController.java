package br.ifba.edu.mercadinho.application;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ifba.edu.mercadinho.infra.service.ClienteService;
import br.ifba.edu.mercadinho.model.dto.ClienteDto;
import br.ifba.edu.mercadinho.model.entities.Cliente;

@RequestMapping("/cliente")
@RestController
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody ClienteDto clienteDto) {
        return ResponseEntity.ok(clienteService.cadastrar(clienteDto));
    }
}
