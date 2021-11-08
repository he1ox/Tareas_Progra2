
package gt.com.jorge.domain;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data //Genera getters/setters y toString
@Table(name="usuario")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Ya que en la bd es autoincrement
    private Long idUsuario;

    @NotEmpty
    private String username;
    
        @NotEmpty
    private String password;
    
    //Usuario puede tener multiples roles
    @OneToMany
    //Columna que hace la relacion entre las tablas, en este caso en la tabla rol es id_usuario
    @JoinColumn(name="id_usuario") 
    private List<Rol> roles;
    
    
}
