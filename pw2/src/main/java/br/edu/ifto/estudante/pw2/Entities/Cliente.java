package br.edu.ifto.estudante.pw2.Entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.hibernate.annotations.GenericGenerator;

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

        private String email;
    
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
