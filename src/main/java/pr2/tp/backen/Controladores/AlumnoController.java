package pr2.tp.backen.Controladores;

import jakarta.validation.Valid;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import pr2.tp.backen.Modelo.Alumno;
import pr2.tp.backen.Services.AlumnoService;

@RestController
@RequestMapping("/alumno")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping
    public List<Alumno> listAlumnos(Model model) {
        return alumnoService.findAll();
    }

    @GetMapping("/{id}")
    public Alumno showAlumnoFormId(@PathVariable Long id) {
        return alumnoService.findById(id).get();
    }

    @PostMapping
    public ResponseEntity<?> saveAlumno(@Valid @RequestBody Alumno alumno, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        Alumno savedAlumno = alumnoService.save(alumno);
        return new ResponseEntity<>(savedAlumno, HttpStatus.CREATED);
    }

    @PutMapping("")
    public Alumno updateAlumno(@RequestBody Alumno alumno) {
        return alumnoService.save(alumno);
    }

    @DeleteMapping("/{id}")
    public Long deleteAlumno(@PathVariable Long id) {
        alumnoService.deleteById(id);
        return id;
    }
}
