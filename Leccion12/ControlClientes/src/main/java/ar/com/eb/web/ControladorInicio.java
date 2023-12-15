package ar.com.eb.web;

import ar.com.eb.servicio.PersonaServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ar.com.eb.domain.Persona;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j //permite acceder al log de la consola, enviando informacion.
public class ControladorInicio {

    @Autowired
    private PersonaServiceImpl personaService;

    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user) {
        var personas = personaService.listarPersonas();
        log.info("Usuario que hizo login: "+user);
    
        model.addAttribute("personas", personas);
        return "index";
    }

    @GetMapping("/agregar")
    public String agregar(Persona persona) {
        return "modificar";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Persona persona, Errors errores) {
        if (errores.hasErrors()){
            return "modificar";
        }
        personaService.guardar(persona);
        return "redirect:/";
    }
    
    @GetMapping("/editar/{idPersona}")
    public String editar(@PathVariable("idPersona") Persona persona, Model model){
        
        persona = personaService.encontrarPersona(persona);
        model.addAttribute("persona", persona);
        
        return "modificar";
    }
    
    @GetMapping("/eliminar")
    public String eliminar(@RequestParam("idPersona") Persona persona){
        personaService.eliminar(persona);
        return "redirect:/";
    }
}
