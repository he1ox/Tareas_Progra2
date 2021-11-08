package gt.com.jorge.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;


//Con loombok ya no es necesario hacer manualmente los setters and getters 

@Data
@Entity
@Table(name="persona")
public class Persona implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long idPersona;
    
    //El campo nombre no puede ser nulo
    @NotEmpty
    private String nombre;
    
    @NotEmpty
    private String apellido;
    
    @NotEmpty
    @Email
    private String email;
    
    private String telefono;
    
    @NotNull
    private Double longitud;
    
    @NotNull
    private Double latitud;
    
}
