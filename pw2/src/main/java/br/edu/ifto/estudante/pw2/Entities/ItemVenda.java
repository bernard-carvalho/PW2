package br.edu.ifto.estudante.pw2.Entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_item_venda")
public class ItemVenda implements Serializable {
    /*######################################
    * ATRIBUTOS
    #######################################*/
       
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private Double precoUnitario;

        private Integer quantidade;

        private Produto produto;

    /*######################################
     * CONSTRUTORES
     #######################################*/

     /*######################################
     * GETTER E SETTERS
     #######################################*/

        public Double getPrecoUnitario() {
            return this.precoUnitario;
        }

        public void setPrecoUnitario(Double precoUnitario) {
            this.precoUnitario = precoUnitario;
        }

        public Integer getQuantidade() {
            return this.quantidade;
        }

        public void setQuantidade(Integer quantidade) {
            this.quantidade = quantidade;
        }

        public Produto getProduto() {
            return this.produto;
        }

        public void setProduto(Produto produto) {
            this.produto = produto;
        }

    /*######################################
     * METODOS
     #######################################*/
        public void updatePrecoUnitario(){
            this.precoUnitario = produto.getPreco();
        }
    
}
