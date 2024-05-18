package uniandes.edu.co.proyecto.Controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import uniandes.edu.co.proyecto.Modelos.Operacion;
import uniandes.edu.co.proyecto.Repositorio.OperacionRepository;
import org.springframework.stereotype.Controller;

@Controller
public class OperacionController {

    @Autowired
    private OperacionRepository operacionRepository;

    public String generarNuevoId() {
        int i = 1;
        String nuevoId;
        
        while (true) {
            nuevoId = String.valueOf(i);
            Operacion oficina = operacionRepository.findOperacionById(nuevoId);
            if (oficina == null) {
                break;
            }
            i++;
        }        
        return nuevoId;
    }

    @GetMapping("/operaciones")
    public String operaciones(Model model) {
        model.addAttribute("operaciones", operacionRepository.findAllOperacions());
        return "Operacion";
    }
    
    @GetMapping("/operaciones/new")
    public String operacionform(Model model) {
        model.addAttribute("operacion", new Operacion());
        return "OperacionNuevo";
    }

    @PostMapping("/operaciones/new/save")
    public String operacionGuardar(@ModelAttribute Operacion operacion) throws ParseException {
        String nuevoId = generarNuevoId();
        operacion.setId(nuevoId);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date parsedDate = dateFormat.parse(operacion.getFechaYHora());
        java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
        String fechaFormateada = dateFormat.format(sqlDate);
        operacion.setFechaYHora(fechaFormateada);
        operacionRepository.insertOperacion(operacion);
        return "redirect:/operaciones";
    }

    @GetMapping("/operaciones/{id}/edit")
    public String operacionEditarForm(@PathVariable String id, Model model) {
        Operacion operacion = operacionRepository.findOperacionById(id);
        if(operacion != null){
            model.addAttribute("operacion", operacion);
            return "OperacionEditar";
        } else {
            return "redirect:/operaciones";
        }
    }

    @PostMapping("/operaciones/{id}/edit/save")
    public String operacionEditarGuardar(@PathVariable int id, @ModelAttribute Operacion operacion)
            throws ParseException {
        SimpleDateFormat dateFormat;
        java.util.Date parsedDate;
        if (operacion.getFechaYHora().matches("\\d{2}/\\d{2}/\\d{4}")) {
            dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            parsedDate = dateFormat.parse(operacion.getFechaYHora());
        } else {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            parsedDate = dateFormat.parse(operacion.getFechaYHora());
        }
        java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
        String fechaFormateada = dateFormat.format(sqlDate);
        operacion.setFechaYHora(fechaFormateada);
        operacionRepository.updateOperacion(operacion);
        return "redirect:/operaciones";
    }

    
    @GetMapping("/operaciones/{id}/delete")
    public String operacionEliminar(@PathVariable String id) {
        operacionRepository.deleteOperacion(id);
        return "redirect:/operaciones";
    }
}