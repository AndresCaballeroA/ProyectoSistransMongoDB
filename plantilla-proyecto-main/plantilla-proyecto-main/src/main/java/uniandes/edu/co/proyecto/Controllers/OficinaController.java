package uniandes.edu.co.proyecto.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.Modelos.Empleado;
import uniandes.edu.co.proyecto.Modelos.Oficina;
import uniandes.edu.co.proyecto.Repositorio.EmpleadoRepository;
import uniandes.edu.co.proyecto.Repositorio.OficinaRepository;
import org.springframework.stereotype.Controller;

@Controller
public class OficinaController {

    @Autowired
    private OficinaRepository oficinaRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public String generarNuevoId() {
        int i = 1;
        String nuevoId;
        
        while (true) {
            nuevoId = String.valueOf(i);
            Oficina oficina = oficinaRepository.findOficinaById(nuevoId);
            if (oficina == null) {
                break;
            }
            i++;
        }        
        return nuevoId;
    }
    

    @GetMapping("/oficinas")
    public String oficinas(Model model) {
        model.addAttribute("oficinas", oficinaRepository.findAllOficinas());
        return "Oficina";
    }
    
    @GetMapping("/oficinas/new")
    public String oficinaform(Model model) {
        Oficina oficina = new Oficina();
        oficina.setGerente(new Empleado());
        model.addAttribute("oficina", oficina);
        return "oficinaNuevo";
    }

    @PostMapping("/oficinas/new/save")
    public String oficinaGuardar(@ModelAttribute Oficina oficina) {
        String nuevoId = generarNuevoId();
        System.out.println(nuevoId);
        oficina.setId(nuevoId);
        if (empleadoRepository.findEmpleadoById(oficina.getGerente().getId()) != null) {
            oficina.setGerente(empleadoRepository.findEmpleadoById(oficina.getGerente().getId()));
            oficinaRepository.insertOficina(oficina);
        }
            
        return "redirect:/oficinas";
    }

    
    @GetMapping("/oficinas/{id}/edit")
    public String oficinaEditarForm(@PathVariable String id, Model model) {
        System.out.println(oficinaRepository.findOficinaById(id).getGerente().getId());
        Oficina oficina = oficinaRepository.findOficinaById(id);
        if(oficina != null){
            model.addAttribute("oficina", oficina);
            return "OficinaEditar";
        } else {
            return "redirect:/oficinas";
        }
    }
    
    @PostMapping("/oficinas/{id}/edit/save")
    public String oficinaEditarGuardar(@PathVariable String id, @ModelAttribute Oficina oficina) {
        oficina.setGerente(empleadoRepository.findEmpleadoById(oficina.getGerente().getId()));
        oficinaRepository.updateOficina(oficina);
        return "redirect:/oficinas";
    }
    
    @GetMapping("/oficinas/{id}/delete")
    public String oficinaEliminar(@PathVariable String id) {
        oficinaRepository.deleteOficina(id);
        return "redirect:/oficinas";
    }
}
