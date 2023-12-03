package ar.com.eb.dao;

import ar.com.eb.domain.Persona;
import org.springframework.data.repository.CrudRepository;


public interface IPersonaDAO extends CrudRepository<Persona, Long>{
    
}
