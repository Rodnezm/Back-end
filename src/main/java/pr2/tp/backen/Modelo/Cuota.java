package pr2.tp.backen.Modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Cuotas")
public class Cuota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Nro_cuota")
    private Long nroCuota;

    @ManyToOne
    @JoinColumn(name = "Inscripción_idInscripción", nullable = false)
    private Inscripcion inscripcion;

    @Column(name = "importe")
    private Integer importe;

    // Getters y Setters
    public Long getNroCuota() {
        return nroCuota;
    }

    public void setNroCuota(Long nroCuota) {
        this.nroCuota = nroCuota;
    }

    public Inscripcion getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(Inscripcion inscripcion) {
        this.inscripcion = inscripcion;
    }

    public Integer getImporte() {
        return importe;
    }

    public void setImporte(Integer importe) {
        this.importe = importe;
    }

}
