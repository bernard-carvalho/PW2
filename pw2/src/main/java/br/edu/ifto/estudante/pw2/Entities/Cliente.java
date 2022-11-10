package br.edu.ifto.estudante.pw2.Entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Cliente {

    /*######################################
    * ATRIBUTOS
    #######################################*/

        @Id
        @GeneratedValue(generator = "inc")
        @GenericGenerator(name = "inc", strategy = "increment")
        private Long id;

        @NotBlank(message="Email nao pode estar em branco")
        private String email;

        
        @OneToMany(mappedBy = "cliente")
        private List<Venda> vendas;

        
    
    /*######################################
     * CONSTRUTORES
     #######################################*/

     public Cliente(){

     }



     /*######################################
     * GETTER E SETTERS
     #######################################*/

        public List<Venda> getVendas() {
            return this.vendas;
        }

        public void setVendas(List<Venda> vendas) {
            this.vendas = vendas;
        }   

        public Long getId() {
            return this.id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getEmail() {
            return this.email;
        }

        public void setEmail(String contato) {
            this.email = contato;
        }

    /*######################################
     * METODOS
     #######################################*/

     
    
}
