package br.edu.ifto.estudante.pw2.Entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="tb_produto") //notação opcional que informa o nome da tabela a ser criada
public class Produto implements Serializable {
    /*######################################
    * ATRIBUTOS
    #######################################*/

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank(message = "nome não pode ser nulo")
        private String nome;

        @Min(value=0, message="preco deve ser superior a {1}")
        private Double preco;

    /*######################################
     * CONSTRUTORES
     #######################################*/

     public Produto(){

     }

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
