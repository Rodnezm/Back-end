package pr2.tp.backen.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Inscripcion", uniqueConstraints = @UniqueConstraint(columnNames = {"id_Alumno", "id_Curso"}))
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idInscripcion")
    private Long id_inscripcion;

    @ManyToOne
    @JoinColumn(name = "idCurso", nullable = false)
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "id_Alumno", nullable = false)
    private Alumno alumno;

    @Column(name = "Ano_Lectivo")
    private Integer anoLectivo;

    @Pattern(regexp = "Pendiente de pago|Cancelado|Inscripto", message = "El estado debe ser 'Pendiente de pago', 'Cancelado' o 'Inscripto'")
    @Column(name = "Estado_Inscripcion", nullable = false)
    private String estado_Inscripcion;

    @NotNull(message = "La fecha de inscripción no puede estar vacía")
    @Column(name = "Fecha_Inscripcion", nullable = false)
    private Date fecha_Inscripcion;

    @NotNull(message = "El monto de matrícula no puede estar vacío")
    @Min(value = 0, message = "El monto de matrícula debe ser mayor o igual a 0")
    @Column(name = "Monto_Matricula", nullable = false)
    private Integer monto_Matricula;

    @Min(value = 0, message = "El descuento debe ser mayor o igual a 0")
    @Column(name = "Descuento")
    private Integer descuento;

    @OneToMany(mappedBy = "inscripcion")
    @JsonIgnore
    private List<Cuota> cuotas;

    public Long getIdinscripcion() {
        return id_inscripcion;
    }

    public void setIdinscripcion(Long id_inscripcion) {
        this.id_inscripcion = id_inscripcion;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Integer getAnoLectivo() {
        return anoLectivo;
    }

    public void setAnoLectivo(Integer anoLectivo) {
        this.anoLectivo = anoLectivo;
    }

    public String getestado_Inscripcion() {
        return estado_Inscripcion;
    }

    public void setestado_Inscripcion(String estado_Inscripcion) {
        this.estado_Inscripcion = estado_Inscripcion;
    }

    public Date getFecha_Inscripcion() {
        return fecha_Inscripcion;
    }

    public void setFecha_Inscripcion(Date fecha_Inscripcion) {
        this.fecha_Inscripcion = fecha_Inscripcion;
    }

    public Integer getMonto_Matricula() {
        return monto_Matricula;
    }

    public void setMonto_Matricula(Integer monto_Matricula) {
        this.monto_Matricula = monto_Matricula;
    }

    public Integer getDescuento() {
        return descuento;
    }

    public void setDescuento(Integer descuento) {
        this.descuento = descuento;
    }

    public List<Cuota> getCuotas() {
        return cuotas;
    }

    // Getters y Setters
    public void setCuotas(List<Cuota> cuotas) {
        this.cuotas = cuotas;
    }

    public void setid_inscripcion(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
