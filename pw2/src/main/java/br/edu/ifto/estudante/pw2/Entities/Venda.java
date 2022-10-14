package br.edu.ifto.estudante.pw2.Entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;



@Entity //notação que identifica a classe como entidade, isto é, uma tabela deve ser criada no banco para representá-la
@Table(name="tb_venda") //notação opcional que informa o nome da tabela a ser criada
@Scope(value=WebApplicationContext.SCOPE_SESSION) // notação que informa ao spring que essa classe, quando instanciada através da notação @autowired, deve ser persistida na sessão do usuário.
//@Component // notação que informa ao spring que a classe usará injeção de dependência (@autowired)
public class Venda implements Serializable {

    /*######################################
     * ATRIBUTOS
     #######################################*/
     
        @Id // notação que identifica a proxima variavel como campo PK da entidade
        @GeneratedValue(strategy = GenerationType.IDENTITY) // notação que informa ao JPA que o campo é do tipo auto-increment
        private Long id;

        @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)//notação que informa a formatação a ser gravada no banco
        private LocalDate data = LocalDate.now();

        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)//notação que informa a multiplicidade do proximo atributo
        @JoinColumn(name="cod_venda") //nome da coluna
        private List<ItemVenda> itensVenda = new ArrayList<>();

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

        public LocalDate getData() {
            return this.data;
        }

        public void setData(LocalDate data) {
            this.data = data;
        }

        public List<ItemVenda> getItensVenda() {
            return this.itensVenda;
        }

        public void setItensVenda(List<ItemVenda> itensVenda) {
            this.itensVenda = itensVenda;
        }

    /*######################################
     * METODOS
     #######################################*/

        public Double getValor(){
            Double valorTotalDaVenda = 0.0;
            int tamanho_venda = itensVenda.size();
                for(int i=0; i<tamanho_venda; i++){
                    ItemVenda item = itensVenda.get(i);
                    Double precoItemVenda = item.getPrecoUnitario() * item.getQuantidade();
                    valorTotalDaVenda += precoItemVenda;
                }
            return valorTotalDaVenda;
        }

}
