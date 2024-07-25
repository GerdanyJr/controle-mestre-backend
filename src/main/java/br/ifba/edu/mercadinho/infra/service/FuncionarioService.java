package br.ifba.edu.mercadinho.infra.service;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import br.ifba.edu.mercadinho.infra.repository.EnderecoRepository;
import br.ifba.edu.mercadinho.infra.repository.FuncionarioRepository;
import br.ifba.edu.mercadinho.infra.util.Mapper;
import br.ifba.edu.mercadinho.model.dto.FuncionarioDto;
import br.ifba.edu.mercadinho.model.entities.Endereco;
import br.ifba.edu.mercadinho.model.entities.Funcionario;
import br.ifba.edu.mercadinho.model.exception.impl.ConflictException;
import br.ifba.edu.mercadinho.model.exception.impl.NotFoundException;

@Service
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;
    private final EnderecoRepository enderecoRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, EnderecoRepository enderecoRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.enderecoRepository = enderecoRepository;
    }

    public List<Funcionario> obterTodos() {
        return funcionarioRepository.findAll();
    }

    public void cadastrar(FuncionarioDto dto) {
        funcionarioExists(dto.email(), dto.cpf(), dto.telefone());
        Endereco endereco = null;
        if (dto.endereco().isPresent()) {
            endereco = enderecoRepository.save(dto.endereco().get());
        }
        FuncionarioDto funcionarioDto = new FuncionarioDto(dto.nome(),
                dto.apelido(),
                dto.cpf(),
                dto.email(),
                Optional.ofNullable(endereco),
                dto.telefone(),
                dto.cargo());
        funcionarioRepository.save(Mapper.fromDtoToEntity(funcionarioDto));
    }

    public Funcionario atualizar(Funcionario updated) {
        Funcionario funcionario = funcionarioRepository.findById(updated.getId())
                .orElseThrow(() -> new NotFoundException("Funcionário não encontrado com id " + updated.getId()));
        BeanUtils.copyProperties(updated, funcionario);
        if (updated.getEndereco() != null) {
            Optional<Endereco> foundEndereco = enderecoRepository
                    .findById(updated.getEndereco().getId());
            if (foundEndereco.isPresent()) {
                BeanUtils.copyProperties(updated.getEndereco(), foundEndereco.get());
            }
            funcionario.setEndereco(enderecoRepository.save(updated.getEndereco()));
        }
        return funcionarioRepository.save(funcionario);
    }

    public void deletar(Integer id) {
        Funcionario foundFuncionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Funcionário não encontrado com id " + id));
        funcionarioRepository.delete(foundFuncionario);
    }

    private void funcionarioExists(String email, String cpf, String telefone) {
        Optional<Funcionario> funcionarioByEmail = funcionarioRepository.findByEmail(email);
        if (funcionarioByEmail.isPresent()) {
            throw new ConflictException("Funcionário já cadastrado com o email " + email);
        }
        Optional<Funcionario> funcionarioByCpf = funcionarioRepository.findByCpf(cpf);
        if (funcionarioByCpf.isPresent()) {
            throw new ConflictException("Funcionário já cadastrado com o cpf " + cpf);
        }
        Optional<Funcionario> funcionarioByTelefone = funcionarioRepository.findByTelefone(telefone);
        if (funcionarioByTelefone.isPresent()) {
            throw new ConflictException("Funcionário já cadastrado com o telefone " + telefone);
        }
    }

}
