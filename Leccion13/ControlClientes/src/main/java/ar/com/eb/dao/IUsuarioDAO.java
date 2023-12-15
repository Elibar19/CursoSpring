
package ar.com.eb.dao;

import ar.com.eb.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IUsuarioDAO extends JpaRepository<Usuario, Long>{
    Usuario findByUsername(String username);
}
