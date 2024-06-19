package br.ifba.edu.mercadinho.model.req;

import java.math.BigDecimal;

public record ProdutoReq(
        String nome, String marca,
        Integer categoriaId, BigDecimal preco, Integer cod) {

}
