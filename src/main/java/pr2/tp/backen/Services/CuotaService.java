package pr2.tp.backen.Services;

import java.util.ArrayList;
import pr2.tp.backen.Repository.CuotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import pr2.tp.backen.ControlErrores.CuotaException;
import pr2.tp.backen.Modelo.Cuota;

@Service
public class CuotaService {

    @Autowired
    private CuotaRepository cuotaRepository;

    public List<Cuota> findAll() {
        return cuotaRepository.findAll();
    }

    public Optional<Cuota> findById(Long id) {
        return cuotaRepository.findById(id);
    }

    public Cuota save(Cuota cuota) {
        return cuotaRepository.save(cuota);
    }

    public void deleteById(Long id) {
        cuotaRepository.deleteById(id);
    }

    public int obtenerCuotaPorGrado(String grado, String especializacion, Integer turno) {
        // Lógica para determinar el importe de la cuota en función del grado, especialización y turno
        if (grado != null && turno != null) {
            if ((grado.equals("Pre-jardín") || grado.equals("Jardín") || grado.equals("Pre-escolar")
                    || (grado.equals("Primero") || grado.equals("Segundo") || grado.equals("Tercero")
                    || grado.equals("Cuarto") || grado.equals("Quinto") || grado.equals("Sexto")
                    || grado.equals("Séptimo") || grado.equals("Octavo") || grado.equals("Noveno") && turno == 1))) {
                return 500000;
            } else if ((grado.equals("Primero") || grado.equals("Segundo") || grado.equals("Tercero")
                    || grado.equals("Cuarto") || grado.equals("Quinto") || grado.equals("Sexto")
                    || grado.equals("Séptimo") || grado.equals("Octavo") || grado.equals("Noveno") && turno == 2)) {
                return 400000;
            } else if (grado.equals("Primer Bachillerato") || grado.equals("Segundo Bachillerato") || grado.equals("Tercer Bachillerato")) {
                if (especializacion.equals("Ciencias Sociales") || especializacion.equals("Ciencias Basicas")) {
                    return 600000;
                } else if (especializacion.equals("BTI") || especializacion.equals("BTA")) {
                    return 800000;
                }
            }
        }
        return -1; // Si no se cumple ninguna condición, retornar -1 para indicar un error
    }

    public List<Cuota> generarCuotasParaDiezMeses(String grado, String especializacion, Integer turno, Integer arancelPersonalizado, boolean aplicarATodosLosAlumnos) {
        int arancel = arancelPersonalizado != null ? arancelPersonalizado : obtenerCuotaPorGrado(grado, especializacion, turno);

        if (arancel == -1) {
            throw new CuotaException("Error en la determinación del arancel. Verifique los datos ingresados.");
        }

        if (arancelPersonalizado != null) {
            if (aplicarATodosLosAlumnos) {
                // Lógica para aplicar el arancel personalizado a todos los alumnos
                System.out.println("Se aplicará el arancel personalizado de " + arancel + " a todos los alumnos.");
            } else {
                // Lógica para aplicar el arancel personalizado solo a un alumno específico
                System.out.println("Se aplicará el arancel personalizado de " + arancel + " solo a este alumno.");
            }
        } else {
            System.out.println("Se aplicará el arancel automático de " + arancel);
        }

        List<Cuota> cuotas = new ArrayList<>();
        int[] meses = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11}; // Meses de febrero a noviembre

        for (int mes : meses) {
            Cuota cuota = new Cuota();
            cuota.setImporte(arancel);
            cuotas.add(cuota);
        }

        return cuotas;
    }

}
