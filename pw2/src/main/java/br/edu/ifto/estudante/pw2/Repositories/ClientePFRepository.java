package br.edu.ifto.estudante.pw2.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifto.estudante.pw2.Entities.ClientePF;

@Repository
public interface ClientePFRepository extends JpaRepository<ClientePF, Long>{
    
}
