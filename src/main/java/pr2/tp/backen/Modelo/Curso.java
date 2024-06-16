package pr2.tp.backen.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "Curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCurso")
    private Long idCurso;

    @Column(name = "nivel")
    private String nivel;

    @NotBlank(message = "El ciclo no puede estar vacío")
    @Pattern(regexp = "Pre-escolar|Primer ciclo|Segundo ciclo|Tercer ciclo|Bachillerato", message = "Ciclo debe ser uno de los siguientes: Pre-escolar, Primer ciclo, Segundo ciclo, Tercer ciclo, Bachillerato")
    @Column(name = "Ciclo")
    private String ciclo;

    
    @NotBlank(message = "El grado no puede estar vacío")
    @Column(name = "Grado")
    private String grado;
    
    @Column(name = "Especializacion")
    private String especializacion;

    @NotBlank(message = "El estado no puede estar vacío")
    @Pattern(regexp = "Inactivo|Activo", message = "El estado debe ser 'Inactivo' o 'Activo'")
    @Column(name = "Estado")
    private String estado;
    
   
    @Column(name = "Arancel")
    private String arancel;
    
    private Integer turno;

    @OneToMany(mappedBy = "curso")
    @JsonIgnore
    private List<Inscripcion> inscripciones;

    // Getters y Setters
    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public String getEspecializacion() {
        return especializacion;
    }

    public void setEspecializacion(String especializacion) {
        this.especializacion = especializacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getArancel() {
        return arancel;
    }

    public void setArancel(String arancel) {
        this.arancel = arancel;
    }


    public Integer getTurno() {
        return turno;
    }

    public void setTurno(Integer turno) {
        this.turno = turno;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

}
