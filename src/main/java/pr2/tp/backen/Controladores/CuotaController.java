package pr2.tp.backen.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import pr2.tp.backen.Modelo.Cuota;
import pr2.tp.backen.Services.CuotaService;

@RestController
@RequestMapping("/cuota")
public class CuotaController {

    @Autowired
    private CuotaService cuotaService;

    @GetMapping
    public List<Cuota> listCuotas(Model model) { 
        return cuotaService.findAll();
    }

    @GetMapping("/{id}")
    public Cuota showCuotaFormId(@PathVariable Long id) {
        return cuotaService.findById(id).get(); 
    }

    @PostMapping("")
    public Cuota saveCuota(@RequestBody Cuota cuota) { 
        return cuotaService.save(cuota);
    }

    @PutMapping("")
    public Cuota updateCuota(@RequestBody Cuota cuota) {
        return cuotaService.save(cuota);
    }

    @DeleteMapping("/{id}")
    public Long deleteCuota(@PathVariable Long id) {
        cuotaService.deleteById(id);
        return id;
    }
}
