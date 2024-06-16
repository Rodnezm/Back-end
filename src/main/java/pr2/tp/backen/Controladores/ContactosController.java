package pr2.tp.backen.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import pr2.tp.backen.Modelo.Contacto;
import pr2.tp.backen.Services.ContactosService;

@RestController
@RequestMapping("/Contact")
public class ContactosController {

    @Autowired
    private ContactosService ContactService;

    @GetMapping
    public List<Contacto> listContactos(Model model) { 
        return ContactService.findAll();
    }

    @GetMapping("/{id}")
    public List<Contacto> showContactosFormId(@PathVariable Long id) {
        return ContactService.findByIdAlumno(id); 
    }

    @PostMapping("")
    public List<Contacto> saveContactos(@RequestBody List<Contacto> Contact) { 
        return ContactService.save(Contact);
    }

    @PutMapping("")
    public List<Contacto> updateContactos(@RequestBody List<Contacto> Contact) {
        return ContactService.save(Contact);
    }

    @DeleteMapping("/{id}")
    public Long deleteContactos(@PathVariable Long id) {
        ContactService.deleteById(id);
        return id;
    }
}
