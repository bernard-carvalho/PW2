package br.edu.ifto.estudante.pw2.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_produto")
public class Produto {
    /*######################################
    * ATRIBUTOS
    #######################################*/

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String nome;

        private Double preco;

    /*######################################
     * CONSTRUTORES
     #######################################*/

     /*######################################
     * GETTER E SETTERS
     #######################################*/

     public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return this.preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    /*######################################
     * METODOS
     #######################################*/
}