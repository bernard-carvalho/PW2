package br.edu.ifto.estudante.pw2.Entities;

public class ItemVenda {
    /*######################################
    * ATRIBUTOS
    #######################################*/
        
        private Double precoUnitario;
        private Integer quantidade;

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

    /*######################################
     * METODOS
     #######################################*/
    
}
