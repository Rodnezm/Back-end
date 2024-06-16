package pr2.tp.backen.Controladores;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import pr2.tp.backen.Modelo.Curso;
import pr2.tp.backen.Services.CursoService;

@RestController
@RequestMapping("/course")
public class CursoController {

    @Autowired
    private CursoService courseService;

    @GetMapping
    public List<Curso> listCursos(Model model) {
        return courseService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Curso> listCursosId(@PathVariable Long id) {
        return courseService.findById(id);
    }
    
    @PostMapping
    public ResponseEntity<?> saveCurso(@Valid @RequestBody Curso course, BindingResult bindingResult) {

        if (!courseService.isGradoValidoParaCiclo(course.getCiclo(), course.getGrado())) {
            return new ResponseEntity<>("El grado no corresponde al ciclo seleccionado", HttpStatus.BAD_REQUEST);
        }
        Curso savedCurso = courseService.save(course);
        return new ResponseEntity<>(savedCurso, HttpStatus.CREATED);
    }

    @PutMapping("")
    public Curso updateCurso(@RequestBody Curso course) {
        return courseService.save(course);

    }

    @DeleteMapping("/{id}")
    public Long deleteCurso(@PathVariable Long id) {
        courseService.deleteById(id);
        return id;
    }
}
