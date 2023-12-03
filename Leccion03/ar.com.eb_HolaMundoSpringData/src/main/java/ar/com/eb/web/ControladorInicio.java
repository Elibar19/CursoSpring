
package ar.com.eb.web;

import ar.com.eb.dao.IPersonaDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j //permite acceder al log de la consola, enviando informacion.
public class ControladorInicio {
    
    @Autowired
    private IPersonaDAO personaDAO;
    
    @GetMapping("/")
    public String inicio(Model model){
        
        var personas = personaDAO.findAll();
        log.info("Hola mundo con Spring MVC");
        model.addAttribute("personas", personas);
        return "index";
    }
}
