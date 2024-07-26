package br.ifba.edu.mercadinho.infra.util;

import br.ifba.edu.mercadinho.model.dto.ClienteDto;
import br.ifba.edu.mercadinho.model.dto.EnderecoDto;
import br.ifba.edu.mercadinho.model.dto.FornecedorDto;
import br.ifba.edu.mercadinho.model.dto.FuncionarioDto;
import br.ifba.edu.mercadinho.model.dto.ProdutoDto;
import br.ifba.edu.mercadinho.model.entities.Categoria;
import br.ifba.edu.mercadinho.model.entities.Cliente;
import br.ifba.edu.mercadinho.model.entities.Endereco;
import br.ifba.edu.mercadinho.model.entities.Fornecedor;
import br.ifba.edu.mercadinho.model.entities.Funcionario;
import br.ifba.edu.mercadinho.model.entities.Produto;

public class Mapper {
    public static Produto fromDtoToEntity(ProdutoDto produto, Categoria categoria) {
        return new Produto(null,
                produto.nome(),
                produto.marca(),
                categoria,
                produto.preco(),
                produto.cod());
    }

    public static Cliente fromDtoToEntity(ClienteDto cliente) {
        return new Cliente(null,
                cliente.nome(),
                cliente.cpf(),
                cliente.endereco().orElse(null),
                cliente.dataNascimento(),
                cliente.sexo());
    }

    public static Endereco fromDtoToEntity(EnderecoDto endereco) {
        return new Endereco(null,
                endereco.cidade(),
                endereco.cep(),
                endereco.numero(),
                endereco.rua(),
                endereco.bairro(),
                endereco.complemento());
    }

    public static Funcionario fromDtoToEntity(FuncionarioDto funcionario) {
        return new Funcionario(null,
                funcionario.nome(),
                funcionario.apelido(),
                funcionario.cpf(),
                funcionario.email(),
                funcionario.endereco().orElse(null),
                funcionario.telefone(),
                funcionario.cargo());
    }

    public static Fornecedor fromDtoToEntity(FornecedorDto fornecedor) {
        return new Fornecedor(null,
                fornecedor.nome(),
                fornecedor.email(),
                fornecedor.cnpj(),
                fornecedor.endereco().orElse(null),
                fornecedor.telefone());
    }
}
