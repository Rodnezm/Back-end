package pr2.tp.backen.Services;

import pr2.tp.backen.Repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import pr2.tp.backen.ControlErrores.NivelCicloException;
import pr2.tp.backen.Modelo.Curso;

@Service
public class CursoService {

    @Autowired
    private CursoRepository courseRepository;

    public List<Curso> findAll() {
        return courseRepository.findAll();
    }

    public Optional<Curso> findById(Long id) {
        return courseRepository.findById(id);
    }

    public Curso save(Curso course) {

        return courseRepository.save(course);

    }

     public void validarNivelParaCiclo(String nivel, String ciclo) {
        boolean isValid = switch (ciclo) {
            case "Pre-escolar" -> nivel.equals("Nivel Inicial");
            case "Primer ciclo", "Segundo ciclo", "Tercer ciclo" -> nivel.equals("Educacion Escolar Basica");
            case "Bachillerato" -> nivel.equals("Educacion Media");
            default -> false;
        };

        if (!isValid) {
            throw new NivelCicloException("El ciclo '" + ciclo + "' no concuerda con el nivel '" + nivel + "'.");
        }
    }

    public boolean isGradoValidoParaCiclo(String ciclo, String grado) {
        return switch (ciclo) {
            case "Pre-escolar" ->
                grado.equals("Pre-jardin") || grado.equals("Jardin") || grado.equals("Preescolar");
            case "Primer ciclo" ->
                grado.equals("Primero") || grado.equals("Segundo") || grado.equals("Tercero");
            case "Segundo ciclo" ->
                grado.equals("Cuarto grado") || grado.equals("Quinto grado") || grado.equals("Sexto grado");
            case "Tercer ciclo" ->
                grado.equals("Septimo grado") || grado.equals("Octavo grado") || grado.equals("Noveno grado");
            case "Bachillerato" ->
                grado.equals("Primer bachillerato") || grado.equals("Segundo bachillerato") || grado.equals("Tercer bachillerato");
            default ->
                false;
        };
    }

    public boolean isEspecializacionParaBachillerato(String ciclo, String grado, String especializacion) {
        if (ciclo.equals("Bachillerato")) {
            return switch (grado) {
                case "Primer bachillerato", "Segundo bachillerato", "Tercer bachillerato" ->
                    especializacion.equals("Ciencias Sociales") || especializacion.equals("Ciencias Basicas")
                    || especializacion.equals("BTI") || especializacion.equals("BTA");
                default ->
                    false;
            };
        } else {
            // Si no es ciclo de bachillerato, siempre retorna true
            return true;
        }
    }

    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }
}
