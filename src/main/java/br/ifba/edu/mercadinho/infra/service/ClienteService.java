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
        Endereco endereco = null;
        if (req.endereco().isPresent()) {
            endereco = enderecoRepository.save(req.endereco().get());
        }
        ClienteDto dto = new ClienteDto(req.nome(),
                req.cpf(),
                Optional.ofNullable(endereco),
                req.dataNascimento(),
                req.sexo());
        return clienteRepository.save(Mapper.fromDtoToEntity(dto));
    }

    public Cliente atualizar(Cliente req) {
        Cliente cliente = clienteRepository
                .findById(req.getId())
                .orElseThrow(() -> new NotFoundException("Cliente não encontrada!"));
        BeanUtils.copyProperties(req, cliente);
        if (req.getEndereco() != null) {
            Optional<Endereco> foundEndereco = enderecoRepository
                    .findById(req.getEndereco().getId());
            if (foundEndereco.isPresent()) {
                BeanUtils.copyProperties(req.getEndereco(), foundEndereco.get());
            }
            cliente.setEndereco(enderecoRepository.save(req.getEndereco()));
        }

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