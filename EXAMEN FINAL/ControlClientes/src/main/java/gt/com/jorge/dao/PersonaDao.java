package gt.com.jorge.dao;

import gt.com.jorge.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

//Le pasamos el tipo de objecto y el tipo de su llave primaria id
//Ya no es necesario hacer la PersonaDaoImpl porque springboot se encarga de ello
//Heredamos de la interface CrudRepository
public interface PersonaDao extends JpaRepository<Persona, Long> {
    //Genera de manera automatica los métodos mas comunes
}
