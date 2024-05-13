package uniandes.edu.co.proyecto.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
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

    @Autowired
    private MongoOperations mongoOperations;

    public String generarNuevoId() {
        
        Query query = new Query().limit(1).with(Sort.by(Sort.Order.desc("id")));
        Empleado ultimoEmpleado = mongoOperations.findOne(query, Empleado.class);

        String nuevoId = "1";
        if (ultimoEmpleado != null) {
            String ultimoId = ultimoEmpleado.getId();
            int ultimoNumero = Integer.parseInt(ultimoId); 
            nuevoId = String.valueOf(ultimoNumero + 1); 
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
