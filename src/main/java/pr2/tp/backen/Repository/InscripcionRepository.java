package pr2.tp.backen.Repository;

import java.util.List;
import java.util.Optional;
import pr2.tp.backen.Modelo.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository; 

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    //ptional<Inscripcion> findByAlumnoAndCursoAndAnoLectivo(Alumno alumno, Curso curso, Integer anoLectivo);

   /* @Query(value = "select \n"
            + "	i.id_inscripcion, i.ano_lectivo, i.descuento, i.estado_inscripcion, i.fecha_inscripcion, i.monto_matricula, i.id_alumno, i.id_curso,\n"
            + "	a.nomb_a , a.apellido ,\n"
            + "	c.grado \n"
            + "from inscripcion i inner join alumno a on a.id = i.id_alumno inner join curso c on c.id_curso = i.id_curso \n"
            + " where i.id_inscripcion = :id ", nativeQuery = true)
    Optional<Inscripcion> findVistaByid(@Param("id") Long id);

    @Query(value = "select \n"
            + "	i.id_inscripcion, i.ano_lectivo, i.descuento, i.estado_inscripcion, i.fecha_inscripcion, i.monto_matricula, i.id_alumno, i.id_curso,\n"
            + "	a.nomb_a , a.apellido ,\n"
            + "	c.grado \n"
            + "from inscripcion i inner join alumno a on a.id = i.id_alumno inner join curso c on c.id_curso = i.id_curso \n"
            + " ", nativeQuery = true)
     Optional<List<Inscripcion>> findVistaAll();*/
}
