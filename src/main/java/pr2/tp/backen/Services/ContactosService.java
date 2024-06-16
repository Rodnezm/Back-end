package pr2.tp.backen.Services; 
  
import pr2.tp.backen.Repository.ContactosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import pr2.tp.backen.Modelo.Contacto;

@Service
public class ContactosService {

    @Autowired
    private ContactosRepository ContactRepository;

    public List<Contacto> findAll() {
        return ContactRepository.findAll();
    }

    public Optional<Contacto> findById(Long id) {
        return ContactRepository.findById(id);
    }

    public List<Contacto> findByIdAlumno(Long id) {
        return ContactRepository.findByIdAlumno(id);
    }
    
    public List<Contacto> save(List<Contacto> Contact) {
        return ContactRepository.saveAll(Contact);
    }

    public void deleteById(Long id) {
        ContactRepository.deleteById(id);
    }
}
