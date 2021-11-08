/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gt.com.jorge.web;

import gt.com.jorge.dao.PersonaDao;
import gt.com.jorge.domain.Persona;
import gt.com.jorge.servicio.PersonaService;
import java.util.ArrayList;
import java.util.Arrays;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
//Manejo del log
@Slf4j
public class ControladorInicio {
    
    @Autowired
    private PersonaService personaService;
    
    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user){
        
        var personas = personaService.listarPersonas();
        model.addAttribute("personas",personas);
        //Total de la cantidad de personas
        model.addAttribute("totalClientes", personas.size());
        
        log.info("ejecutando controlador spring mvc");
        log.info("Usuario que hizo login: " +user);
        return "index";
    }
    
    
    @GetMapping("/agregar")
    public String agregar(Persona persona){
        log.info("Ejecutando controlador agregar spring mvc");
        return "modificar";
    }
    
    @PostMapping("/guardar")
    //Validamos el modelo que se llena desde el formulario
    public String guardar(@Valid Persona persona, Errors errores){
        
        if (errores.hasErrors()) {
            return "modificar";
        } 
        
        log.info("Guardando persona");
        personaService.guardar(persona);
        //Redireccionamos a la pagina de inicio -> index
        return "redirect:/";
    }
    
    @GetMapping("/editar/{idPersona}")
    public String editar(Persona persona, Model model){
        persona = personaService.encontrarPersona(persona);
        
        model.addAttribute("persona",persona);
        
        return "modificar";
    }
    
    
    @GetMapping("/eliminar")
    public String eliminar(Persona persona){
        personaService.eliminar(persona);
        return "redirect:/";
    }
}
