 
package pr2.tp.backen.Repository;

import pr2.tp.backen.Modelo.Cuota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CuotaRepository extends JpaRepository<Cuota, Long> {
}
