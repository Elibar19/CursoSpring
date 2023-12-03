
package ar.com.eb;

import ar.com.eb.domain.Persona;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j //permite acceder al log de la consola, enviando informacion.
public class ControladorInicio {
    
    @Value("${index.saludo}")
    private String saludo;
    
    @GetMapping("/")
    public String inicio(Model model){
        var mensaje = "Mensaje con Thymeleaf";
        
        Persona persona = new Persona();
        persona.setNombre("Juan");
        persona.setApellido("Perez");
        persona.setEmail("juanperez@mail.com");
        persona.setTelefono("123456");
        
        Persona persona2 = new Persona();
        persona2.setNombre("Carla");
        persona2.setApellido("Gomez");
        persona2.setEmail("carla@mail.com");
        persona2.setTelefono("654321");
        
        //List<Persona> personas = new ArrayList();
//        var personas = new ArrayList();
//        personas.add(persona);
//        personas.add(persona2);

        //var personas = Arrays.asList(persona, persona2);
        
        log.info("Hola mundo con Spring MVC");
        
        model.addAttribute("mensaje", mensaje);
        model.addAttribute("saludo", saludo);
        //model.addAttribute("persona", persona);
        //model.addAttribute("personas", personas);
        
        return "index";
    }
}
