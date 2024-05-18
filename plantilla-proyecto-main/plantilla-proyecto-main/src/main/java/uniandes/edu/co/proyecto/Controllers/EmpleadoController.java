package uniandes.edu.co.proyecto.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import uniandes.edu.co.proyecto.Modelos.Usuario;
import uniandes.edu.co.proyecto.Modelos.Empleado;
import uniandes.edu.co.proyecto.Repositorio.EmpleadoRepository;
import uniandes.edu.co.proyecto.Repositorio.UsuarioRepository;

@Controller
public class EmpleadoController {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String generarNuevoId() {
        int i = 1;
        String nuevoId;
        
        while (true) {
            nuevoId = String.valueOf(i);
            Empleado oficina = empleadoRepository.findEmpleadoById(nuevoId);
            if (oficina == null) {
                break;
            }
            i++;
        }        
        return nuevoId;
    }

    @GetMapping("/empleados")
    public String empleados(Model model) {
        model.addAttribute("empleados", empleadoRepository.findAllEmpleados());
        return "Empleado";
    }

    @GetMapping("/empleados/new")
    public String empleadoform(Model model) {
        Empleado empleado = new Empleado();
        empleado.setId_usuario(new Usuario()); 
        model.addAttribute("empleado", empleado);
        model.addAttribute("titulo", "Nuevo empleado");
        return "EmpleadoNuevo";
    }


    @PostMapping("/empleados/new/save")
    public String empleadoGuardar(@ModelAttribute Empleado empleado) {
        String nuevoId = generarNuevoId();
        empleado.setId(nuevoId);
        if (usuarioRepository.findUsuarioById(empleado.getId_usuario().getId()) != null) {
            empleado.setId_usuario(usuarioRepository.findUsuarioById(empleado.getId_usuario().getId()));
            empleadoRepository.insertEmpleado(empleado);
        }
    return "redirect:/empleados";
}

    @GetMapping("/empleados/{id}/edit")
    public String empleadoEditarForm(@PathVariable String id, Model model) {
        Empleado empleado = empleadoRepository.findEmpleadoById(id);
        if (empleado != null) {
            model.addAttribute("empleado", empleado);
            return "empleadoEditar";
        } else {
            return "redirect:/empleados";
        }
    }

    @PostMapping("/empleados/{id}/edit/save")
    public String empleadoEditarGuardar(@PathVariable String id, @ModelAttribute Empleado empleado) {
        empleado.setId_usuario(usuarioRepository.findUsuarioById(empleado.getId_usuario().getId()));
        empleadoRepository.updateEmpleado(empleado);
        return "redirect:/empleados";
    }

    @GetMapping("/empleados/{id}/delete")
    public String empleadoEliminar(@PathVariable String id) {
        empleadoRepository.deleteEmpleado(id);
        return "redirect:/empleados";
    }

}
