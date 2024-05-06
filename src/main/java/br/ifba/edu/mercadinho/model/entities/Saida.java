package br.ifba.edu.mercadinho.model.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Saida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private LocalDateTime dataVencimento;
    private BigDecimal preco;
    private Integer numeroSaida;

    public Saida() {
    }

    public Saida(Integer id, String nome, LocalDateTime dataVencimento, BigDecimal preco, Integer numeroSaida) {
        this.id = id;
        this.nome = nome;
        this.dataVencimento = dataVencimento;
        this.preco = preco;
        this.numeroSaida = numeroSaida;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDateTime dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getNumeroSaida() {
        return numeroSaida;
    }

    public void setNumeroSaida(Integer numeroSaida) {
        this.numeroSaida = numeroSaida;
    }

}
