package br.ifba.edu.mercadinho.infra.util;

import br.ifba.edu.mercadinho.model.dto.ClienteDto;
import br.ifba.edu.mercadinho.model.dto.EnderecoDto;
import br.ifba.edu.mercadinho.model.dto.ProdutoDto;
import br.ifba.edu.mercadinho.model.entities.Categoria;
import br.ifba.edu.mercadinho.model.entities.Cliente;
import br.ifba.edu.mercadinho.model.entities.Endereco;
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
                cliente.endereco(),
                cliente.dataDeNascimento(),
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
}
