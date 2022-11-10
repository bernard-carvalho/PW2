package br.edu.ifto.estudante.pw2.Entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="tb_cliente_pf")
public class ClientePF extends Cliente implements Serializable{
    
/*######################################
* ATRIBUTOS
#######################################*/

    @NotNull(message="CPF NÃO PODE SER NULO")
    @NotBlank(message="CPF não pode ser vazio")
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
