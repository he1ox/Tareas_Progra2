
package gt.com.jorge.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data //Genera getters/setters y toString
@Table(name="rol")
public class Rol implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Ya que en la bd es autoincrement
    private Long idRol;
    
    @NotEmpty
    private String nombre;
    
    
}
