package br.edu.ifto.estudante.pw2.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifto.estudante.pw2.Entities.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long>{
    
}
