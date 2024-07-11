package br.ifba.edu.mercadinho.infra.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.ifba.edu.mercadinho.infra.repository.ClienteRepository;
import br.ifba.edu.mercadinho.infra.repository.EnderecoRepository;
import br.ifba.edu.mercadinho.infra.util.Mapper;
import br.ifba.edu.mercadinho.model.dto.ClienteDto;
import br.ifba.edu.mercadinho.model.entities.Cliente;
import br.ifba.edu.mercadinho.model.exception.impl.ConflictException;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;

    public ClienteService(ClienteRepository clienteRepository, EnderecoRepository enderecoRepository) {
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
    }

    public Cliente cadastrar(ClienteDto req) {
        Optional<Cliente> clienteEncontrado = clienteRepository.findByCpf(req.cpf());
        if (clienteEncontrado.isPresent()) {
            throw new ConflictException("Cliente já encontrado com CPF " + req.cpf());
        }
        ClienteDto dto = new ClienteDto(req.nome(),
                req.cpf(),
                enderecoRepository.save(req.endereco()),
                req.dataDeNascimento(),
                req.sexo());
        return clienteRepository.save(Mapper.fromDtoToEntity(dto));
    }

}