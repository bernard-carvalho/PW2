package br.edu.ifto.estudante.pw2.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifto.estudante.pw2.Entities.Cliente;

public interface ClienteRepository  extends JpaRepository<Cliente, Long>{
    
}
