
package ar.com.eb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j //permite acceder al log de la consola, enviando informacion.
public class ControladorInicio {
    
    @GetMapping("/")
    public String inicio(){
        log.info("Hola mundo");
        return "Hola mundo";
    }
    
    
}
