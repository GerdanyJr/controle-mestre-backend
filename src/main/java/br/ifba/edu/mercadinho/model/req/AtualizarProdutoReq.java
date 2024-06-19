package br.ifba.edu.mercadinho.model.req;

import java.math.BigDecimal;

public record AtualizarProdutoReq(
        Integer id,
        String nome,
        String marca,
        Integer categoriaId,
        BigDecimal preco,
        Integer codigo) {

}
