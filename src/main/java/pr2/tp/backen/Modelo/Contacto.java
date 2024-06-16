package pr2.tp.backen.Modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Contacto")
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contacto")
    private Long id_contacto;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Column(name = "Nomb_Contacto", nullable = false)
    private String nomb_Contacto;

    @NotBlank(message = "El apellido no puede estar vacío")
    @Column(name = "Apellido_Contacto", nullable = false)
    private String apellido_Contacto;

    @NotBlank(message = "El Tipo de relacion no puede estar vacío")
    @Column(name = "Tipo_Relación", nullable = false)
    private String tipoRelacion;

    @NotBlank(message = "El Numero de telefono no puede estar vacío")
    @Column(name = "Telef_Contacto", nullable = false)
    private String telef_contacto;

    @NotBlank(message = "La direccion no puede estar vacío")
    @Column(name = "Direc_Contacto", nullable = false)
    private String direccion;
 
    @Column(name = "Emergencia", nullable = false)
    private boolean emergencia;
    
    @ManyToOne
    @JoinColumn(name = "id_Alumno", nullable = false)
    private Alumno alumno;
    
/*
    @ManyToMany(mappedBy = "contactos") 
    private List<Alumno> alumnos;*/

    // Getters y Setters
    public Long getId_contacto() {
        return id_contacto;
    }

    public void setId_contacto(Long id_contacto) {
        this.id_contacto = id_contacto;
    }

    public String getNomb_Contacto() {
        return nomb_Contacto;
    }

    public void setNomb_Contacto(String nomb_Contacto) {
        this.nomb_Contacto = nomb_Contacto;
    }

    public String getApellido_Contacto() {
        return apellido_Contacto;
    }

    public void setApellido_Contacto(String apellido_Contacto) {
        this.apellido_Contacto = apellido_Contacto;
    }

    public String getTipoRelacion() {
        return tipoRelacion;
    }

    public void setTipoRelacion(String tipoRelacion) {
        this.tipoRelacion = tipoRelacion;
    }

    public String getTelef_contacto() {
        return telef_contacto;
    }

    public void setTelef_contacto(String telef_contacto) {
        this.telef_contacto = telef_contacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public boolean isEmergencia() {
        return emergencia;
    }

    public void setEmergencia(boolean emergencia) {
        this.emergencia = emergencia;
    }
 
    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public boolean validarEmergenci() {
        if (emergencia) {
            return true;
        } else if (!emergencia) {
            return false;
        } else {
            System.out.println("No se permite guardar el dato si no se especifica si es un contacto para en caso de emergencia.");
            return false;
        }
    }

}
