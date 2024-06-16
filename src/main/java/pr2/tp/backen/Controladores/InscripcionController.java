package pr2.tp.backen.Controladores;

import pr2.tp.backen.ControlErrores.ErrorResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pr2.tp.backen.Modelo.Alumno;
import pr2.tp.backen.Modelo.Inscripcion;
import pr2.tp.backen.Repository.AlumnoRepository;
import pr2.tp.backen.Services.InscripcionService;

@RestController
@RequestMapping("/Inscrip")
public class InscripcionController {

    @Autowired
    private InscripcionService InscripService;

    @Autowired
    private AlumnoRepository alumnoRepository;

    @GetMapping
    public ResponseEntity<List<Inscripcion>> listInscripcions() {
        List<Inscripcion> inscripciones = InscripService.findAll();
        return new ResponseEntity<>(inscripciones, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Inscripcion> showInscripcionFormId(@PathVariable Long id) {
        Optional<Inscripcion> inscripcionOpt = InscripService.findById(id);
        return inscripcionOpt.map(inscripcion -> new ResponseEntity<>(inscripcion, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<?> createInscripcion(@Valid @RequestBody Inscripcion inscripcionRequest) {
        Alumno alumno = inscripcionRequest.getAlumno(); 
        if (inscripcionRequest.getAlumno() == null) {
            return ResponseEntity.badRequest().body("El ID del alumno es obligatorio.");
        } 
        Optional<Alumno> optionalAlumno = alumnoRepository.findById(alumno.getId_Alumno());
        if (!optionalAlumno.isPresent()) {
            return ResponseEntity.badRequest().body("El alumno no existe.");
        } 
        // Crea una nueva inscripción y asocia el alumno encontrado
        Inscripcion inscripcion = new Inscripcion();
      //  Alumno alumno = optionalAlumno.get();
        inscripcion.setAlumno(alumno);
        inscripcion.setCurso(inscripcionRequest.getCurso());  // Asume que el curso también está en la solicitud
        inscripcion.setAnoLectivo(inscripcionRequest.getAnoLectivo());
        inscripcion.setestado_Inscripcion(inscripcionRequest.getestado_Inscripcion());
        inscripcion.setFecha_Inscripcion(inscripcionRequest.getFecha_Inscripcion());
        inscripcion.setMonto_Matricula(inscripcionRequest.getMonto_Matricula());
        inscripcion.setDescuento(inscripcionRequest.getDescuento());
        // Verifica el estado
        /* if (!inscrip.getestado_Inscripcion().matches("Pendiente de pago|Cancelado|Inscripto")) {
            return new ResponseEntity<>(new ErrorResponse("El estado debe ser 'Pendiente de pago', 'Cancelado' o 'Inscripto'"), HttpStatus.BAD_REQUEST);
        }*/

        Inscripcion savedInscripcion = InscripService.save(inscripcion);
        return new ResponseEntity<>(savedInscripcion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateInscripcion(@PathVariable Long id, @Valid @RequestBody Inscripcion inscrip) {
        if (!InscripService.existsById(id)) {
            return new ResponseEntity<>(new ErrorResponse("Inscripción no encontrada"), HttpStatus.NOT_FOUND);
        }

        inscrip.setid_inscripcion(id);
        Inscripcion updatedInscripcion = InscripService.save(inscrip);
        return new ResponseEntity<>(updatedInscripcion, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInscripcion(@PathVariable Long id) {
        if (!InscripService.existsById(id)) {
            return new ResponseEntity<>("Inscripción no encontrada", HttpStatus.NOT_FOUND);
        }
        InscripService.deleteById(id);
        return new ResponseEntity<>("Inscripción eliminada con éxito", HttpStatus.OK);
    }

    @GetMapping("contrato/{id}")
    public ResponseEntity<Resource> contrato(@PathVariable Long id) {
        return this.InscripService.contratoInscripcion(id);
    }

}
