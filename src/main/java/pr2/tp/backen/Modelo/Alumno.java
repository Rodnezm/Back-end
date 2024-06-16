package pr2.tp.backen.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Date;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.Year;
import java.time.ZoneId;
import java.util.List;

@Entity
@Table(name = "Alumno")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Alumno")
    private Long id_Alumno;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Column(name = "Nomb_A", nullable = false)
    private String nomb_A;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Column(name = "Apellido_A", nullable = false)
    private String apellido_A;

    @NotNull(message = "La fecha de nacimiento no puede estar vacía")
    @Column(name = "Fech_Nac", nullable = false)
    private Date fech_Nac;

    @NotBlank(message = "La dirección no puede estar vacía")
    @Column(name = "Direc_A", nullable = false)
    private String direc_A;

    @Pattern(regexp = "Activo|Inactivo", message = "El estado debe ser 'Activo' o 'Inactivo'")
    @Column(name = "Estado_A", nullable = false)
    private String estado_A;

    @NotBlank(message = "La cédula no puede estar vacía")
    @Column(name = "Cedula", nullable = false, unique = true)
    private String cedula;

    

    @OneToMany(mappedBy = "alumno")
    @JsonIgnore
    private List<Inscripcion> inscripciones;

    /*@ManyToMany
    @JoinTable(
            name = "Alumno_Contactos",
            joinColumns = @JoinColumn(name = "Alumno_id_Alumno"),
            inverseJoinColumns = @JoinColumn(name = "Contacto_id_contacto")
    )*/
    @OneToMany(mappedBy = "alumno")
    @JsonIgnore
    private List<Contacto> contactos;

    public Long getId_Alumno() {
        return id_Alumno;
    }

    public void setId_Alumno(Long id_Alumno) {
        this.id_Alumno = id_Alumno;
    }

    public String getNomb_A() {
        return nomb_A;
    }

    public void setNomb_A(String nomb_A) {
        this.nomb_A = nomb_A;
    }

    public String getApellido_A() {
        return apellido_A;
    }

    public void setApellido_A(String apellido_A) {
        this.apellido_A = apellido_A;
    }

    public Date getFech_Nac() {
        return fech_Nac;
    }

    public void setFech_Nac(Date fech_Nac) {
        this.fech_Nac = fech_Nac;
    }

    public String getDirec_A() {
        return direc_A;
    }

    public void setDirec_A(String direc_A) {
        this.direc_A = direc_A;
    }

    public String getEstado_A() {
        return estado_A;
    }

    public void setEstado_A(String estado_A) {
        this.estado_A = estado_A;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    public List<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(List<Contacto> contactos) {
        this.contactos = contactos;
    }

    public boolean tieneInscripcionAnual() {
        // Obtén el año actual
        int añoActual = Year.now().getValue();

        // Recorre todas las inscripciones del alumno
        for (Inscripcion inscripcion : inscripciones) {
            // Asegúrate de que la fecha de inscripción no sea null
            if (inscripcion.getFecha_Inscripcion() != null) {
                // Convertimos la fecha de inscripción a LocalDate y obtenemos el año
                LocalDate fechaInscripcion = inscripcion.getFecha_Inscripcion().toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
                int añoInscripcion = fechaInscripcion.getYear();

                // Si encontramos una inscripción para el año actual, retorna true
                if (añoInscripcion == añoActual) {
                    return true;
                }
            }
        }

        // Si no se encontró ninguna inscripción para el año actual, retorna false
        return false;
    }

}
