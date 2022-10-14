package br.edu.ifto.estudante.pw2.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity //notação que identifica a classe como entidade, isto é, uma tabela deve ser criada no banco para representá-la
public class Venda  {

    /*######################################
     * ATRIBUTOS
     #######################################*/
    @Id // notação que identifica a proxima variavel como campo PK da entidade
    @GeneratedValue(strategy = GenerationType.IDENTITY) // notação que informa ao JPA que o campo é do tipo auto-increment
    private Long id;


    /*######################################
     * CONSTRUTORES
     #######################################*/
    public Venda() {
    }


    /*######################################
     * GETTER E SETTERS
     #######################################*/
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    /*######################################
     * METODOS
     #######################################*/
}
