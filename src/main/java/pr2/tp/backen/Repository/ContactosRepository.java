package pr2.tp.backen.Repository;

import java.util.List;
import pr2.tp.backen.Modelo.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactosRepository extends JpaRepository<Contacto, Long> {

    @Query(value ="SELECT * FROM contacto WHERE id_alumno = :value",  nativeQuery = true) 
    List<Contacto> findByIdAlumno(@Param("value") Long value);

}
