package pr2.tp.backen.Services;

import jakarta.transaction.Transactional;
import java.io.FileNotFoundException;
import java.util.HashMap;
import pr2.tp.backen.Repository.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import net.sf.jasperreports.engine.JRException;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import pr2.tp.backen.Controladores.Ireport;
import pr2.tp.backen.Modelo.Alumno;
import pr2.tp.backen.Modelo.Contacto;
import pr2.tp.backen.Modelo.Inscripcion; 
import pr2.tp.backen.Repository.AlumnoRepository;
import pr2.tp.backen.Repository.InscripcionRepository;

@Service
public class InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;    

   /* public Inscripcion save(Inscripcion inscripcion) {
        if (inscripcion.getAlumno() == null || inscripcion.getAlumno().getId_Alumno() == null) {
            throw new IllegalStateException("La inscripción debe estar asociada a un alumno con un ID válido.");
        }*/
     public Inscripcion save(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);        
    }
        // Busca al alumno en la base de datos usando el ID
      /*  Long Id_Alumno = inscripcion.getAlumno().getId_Alumno();
        Optional<Alumno> optionalAlumno = alumnoRepository.findById(Id_Alumno);

       /* if (!optionalAlumno.isPresent()) {
            throw new IllegalStateException("El alumno no existe.");
        }

        // Asocia el alumno encontrado con la inscripción
        Alumno alumno = optionalAlumno.get();
        inscripcion.setAlumno(alumno);

        // Verifica si el alumno ya tiene una inscripción anual
        if (alumno.tieneInscripcionAnual()) {
            throw new IllegalStateException("El alumno ya tiene una inscripción para este año.");
        }

        return inscripcionRepository.save(inscripcion);
    }*/

    public List<Inscripcion> findAll() {
        return inscripcionRepository.findAll();
    }

    public Optional<Inscripcion> findById(Long id) {
        return inscripcionRepository.findById(id);
    }

    public void deleteById(Long id) {
        inscripcionRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return inscripcionRepository.existsById(id);
    }

    @Transactional
    public void aplicarDescuentoPorCantidadDeAlumnos(Inscripcion inscripcion) {
        Alumno alumno = inscripcion.getAlumno();
       List<Contacto> contactos = alumno.getContactos();

       /* for (Contacto contacto : contactos) {
            int cantidadAlumnos = contacto.getAlumnos().size();
            int montoMatricula = inscripcion.getMonto_Matricula();

            if (cantidadAlumnos > 1) {
                int descuentoTotal = 0;
                for (int i = 1; i < cantidadAlumnos; i++) {
                    descuentoTotal += montoMatricula * 0.10;
                    montoMatricula -= montoMatricula * 0.10;
                }

                inscripcion.setDescuento(descuentoTotal);
                int montoFinal = inscripcion.getMonto_Matricula() - descuentoTotal;
                inscripcion.setMonto_Matricula(montoFinal);

            }
        }*/

        // Guardar los cambios en la base de datos
        inscripcionRepository.save(inscripcion);
    }

    public ResponseEntity<Resource> contratoInscripcion(Long idInscripcion) {
        Optional<Inscripcion> optInscripcion = this.inscripcionRepository.findById(idInscripcion);
        if (optInscripcion.isPresent()) {
            final Inscripcion inscripcion = optInscripcion.get();
            try {
                HashMap<String, Object> parametros = new HashMap();
                parametros.put("Institucion", "Escueltia");
                parametros.put("Alumno", inscripcion.getAlumno().getApellido_A());
                parametros.put("Fecha", "");
                parametros.put("FechaFin", "");
                parametros.put("Doc_alumno", "");
                parametros.put("Nivel", "");
                parametros.put("FechaIni", "");
                parametros.put("NroInscripcion", "");
                /*  parametros.put("Fecha", inscripcion.getFecha_Inscripcion());
                parametros.put("FechaFin", inscripcion.getFecha_Inscripcion());
                parametros.put("Doc_alumno", inscripcion.getAlumno().getCedula());
                parametros.put("Nivel", inscripcion.getCurso().getNivel());
                parametros.put("FechaIni", inscripcion.getFecha_Inscripcion());
                parametros.put("NroInscripcion", inscripcion.getIdinscripcion());*/
                return new Ireport().Contrato(parametros);
            } catch (FileNotFoundException | JRException e) {
                //  e.printStackTrace();
                System.err.println(e.getMessage());
            }
        } else {
            return ResponseEntity.noContent().build(); //No se encontro el reporte
        }
        return null;
    }
}
