package gt.com.jorge.servicio;

import gt.com.jorge.dao.PersonaDao;
import gt.com.jorge.domain.Persona;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


//Anotacion service para inyectar esta clase como una implementacion de la interface
//dentro del controlador de spring

@Service
public class PersonaServiceImpl implements PersonaService{

    //Inyeccion 
    @Autowired
    private PersonaDao personaDao;
    
    @Override
    //uso de la anotacion transactional, ya que unicamente vamos a leer la tabla colocamos
    //readonly
    @Transactional(readOnly = true)
    public List<Persona> listarPersonas() {
        return (List<Persona>) personaDao.findAll();
    }

    @Override
    @Transactional //Si la transacción es exitosa se realiza un commit, de lo contrario un rollback
    public void guardar(Persona persona) {
        personaDao.save(persona);
    }

    @Override
    @Transactional
    public void eliminar(Persona persona) {
        personaDao.delete(persona);
    }

    @Override
    @Transactional(readOnly = true)
    public Persona encontrarPersona(Persona persona) {
        return personaDao.findById(persona.getIdPersona()).orElse(null);
    }
    
}
