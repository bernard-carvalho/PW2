package br.edu.ifto.estudante.pw2.Entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tb_cliente_pf")
public class ClientePF extends Cliente {
    
/*######################################
* ATRIBUTOS
#######################################*/

    private String cpf;

/*######################################
 * CONSTRUTORES
 #######################################*/



 /*######################################
 * GETTER E SETTERS
 #######################################*/

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

/*######################################
 * METODOS
 #######################################*/

}
