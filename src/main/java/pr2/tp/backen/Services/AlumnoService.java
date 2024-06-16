package pr2.tp.backen.Services; 
  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import pr2.tp.backen.Modelo.Alumno;
import pr2.tp.backen.Repository.AlumnoRepository;

@Service
public class AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    public List<Alumno> findAll() {
        return alumnoRepository.findAll();
    }

    public Optional<Alumno> findById(Long id) {
        return alumnoRepository.findById(id);
    }

    public Alumno save(Alumno alumno) {
        return alumnoRepository.save(alumno);        
    }

    public void deleteById(Long id) {
        alumnoRepository.deleteById(id);
    }
}
