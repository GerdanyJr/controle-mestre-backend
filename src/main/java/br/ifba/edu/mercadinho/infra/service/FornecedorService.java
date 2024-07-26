package br.ifba.edu.mercadinho.infra.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import br.ifba.edu.mercadinho.infra.repository.EnderecoRepository;
import br.ifba.edu.mercadinho.infra.repository.FornecedorRepository;
import br.ifba.edu.mercadinho.infra.util.Mapper;
import br.ifba.edu.mercadinho.model.dto.FornecedorDto;
import br.ifba.edu.mercadinho.model.entities.Endereco;
import br.ifba.edu.mercadinho.model.entities.Fornecedor;
import br.ifba.edu.mercadinho.model.exception.impl.ConflictException;
import br.ifba.edu.mercadinho.model.exception.impl.NotFoundException;

@Service
public class FornecedorService {
    private final FornecedorRepository fornecedorRepository;
    private final EnderecoRepository enderecoRepository;

    public FornecedorService(FornecedorRepository fornecedorRepository, EnderecoRepository enderecoRepository) {
        this.fornecedorRepository = fornecedorRepository;
        this.enderecoRepository = enderecoRepository;
    }

    public void cadastrar(FornecedorDto dto) {
        fornecedorExists(dto.email(), dto.cnpj(), dto.telefone());
        Endereco endereco = null;
        if (dto.endereco().isPresent()) {
            endereco = enderecoRepository.save(dto.endereco().get());
        }
        FornecedorDto fornecedor = new FornecedorDto(
                dto.nome(),
                dto.email(),
                dto.cnpj(),
                Optional.ofNullable(endereco),
                dto.telefone());
        fornecedorRepository.save(Mapper.fromDtoToEntity(fornecedor));
    }

    public List<Fornecedor> obterTodos() {
        return fornecedorRepository.findAll();
    }

    public Fornecedor atualizar(Fornecedor updated) {
        Fornecedor fornecedor = fornecedorRepository.findById(updated.getId())
                .orElseThrow(() -> new NotFoundException("Fornecedor não encontrado com id " + updated.getId()));
        BeanUtils.copyProperties(updated, fornecedor);
        if (updated.getEndereco() != null) {
            Optional<Endereco> foundEndereco = enderecoRepository
                    .findById(updated.getEndereco().getId());
            if (foundEndereco.isPresent()) {
                BeanUtils.copyProperties(updated.getEndereco(), foundEndereco.get());
            }
            fornecedor.setEndereco(enderecoRepository.save(updated.getEndereco()));
        }
        return fornecedorRepository.save(fornecedor);
    }

    public void deletar(Integer id) {
        Fornecedor foundFornecedor = fornecedorRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Fornecedor não encontrado com o id informado!"));
        fornecedorRepository.delete(foundFornecedor);
    }

    private void fornecedorExists(String email, String cnpj, String telefone) {
        Optional<Fornecedor> foundByEmail = fornecedorRepository.findByEmail(email);
        if (foundByEmail.isPresent()) {
            throw new ConflictException("Fornecedor já cadastrado com email " + email);
        }
        Optional<Fornecedor> foundByCnpj = fornecedorRepository.findByCnpj(cnpj);
        if (foundByCnpj.isPresent()) {
            throw new ConflictException("Fornecedor já cadastrado com CNPJ " + cnpj);
        }
        Optional<Fornecedor> foundByTelefone = fornecedorRepository.findByTelefone(telefone);
        if (foundByTelefone.isPresent()) {
            throw new ConflictException("Fornecedor já cadastrado com telefone " + telefone);
        }
    }

}
