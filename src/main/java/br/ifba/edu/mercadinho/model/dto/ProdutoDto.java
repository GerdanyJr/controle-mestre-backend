package br.ifba.edu.mercadinho.model.dto;

import java.math.BigDecimal;

public record ProdutoDto(
        String nome, String marca,
        Integer categoriaId, BigDecimal preco, Integer cod) {

}
