package br.ifba.edu.mercadinho.infra.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import br.ifba.edu.mercadinho.infra.repository.ClienteRepository;
import br.ifba.edu.mercadinho.infra.repository.EnderecoRepository;
import br.ifba.edu.mercadinho.infra.util.Mapper;
import br.ifba.edu.mercadinho.model.dto.ClienteDto;
import br.ifba.edu.mercadinho.model.entities.Cliente;
import br.ifba.edu.mercadinho.model.entities.Endereco;
import br.ifba.edu.mercadinho.model.exception.impl.ConflictException;
import br.ifba.edu.mercadinho.model.exception.impl.NotFoundException;

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
                req.dataNascimento(),
                req.sexo());
        return clienteRepository.save(Mapper.fromDtoToEntity(dto));
    }

    public Cliente atualizar(Cliente req) {
        Cliente cliente = clienteRepository
                .findById(req.getId())
                .orElseThrow(() -> new NotFoundException("Cliente não encontrada!"));
        Endereco endereco = enderecoRepository
                .findById(req.getEndereco().getId())
                .orElseThrow(() -> new NotFoundException("Endereco inválido!"));

        BeanUtils.copyProperties(req, cliente);
        BeanUtils.copyProperties(req.getEndereco(), endereco);
        cliente.setEndereco(enderecoRepository.save(req.getEndereco()));
        return clienteRepository.save(cliente);

    }

    public List<Cliente> obter() {
        return clienteRepository.findAll();
    }

    public void deletar(Integer id) {
        Cliente clienteEncontrado = clienteRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Cliente já encontrado com CPF " + id));
        clienteRepository.delete(clienteEncontrado);
    }

}