package uniandes.edu.co.proyecto.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.Modelos.Usuario;
import uniandes.edu.co.proyecto.Repositorio.UsuarioRepository;
import org.springframework.stereotype.Controller;


@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String generarNuevoId() {
        int i = 1;
        String nuevoId;
        
        while (true) {
            nuevoId = String.valueOf(i);
            Usuario oficina = usuarioRepository.findUsuarioById(nuevoId);
            if (oficina == null) {
                break;
            }
            i++;
        }        
        return nuevoId;
    }

    @GetMapping("/usuarios")
    public String usuarios(Model model) {
        model.addAttribute("usuarios", usuarioRepository.findAllUsuarios());
        return "Usuario";
    }
    
    @GetMapping("/usuarios/new")
    public String usuarioform(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "UsuarioNuevo";
    }

    @PostMapping("/usuarios/new/save")
    public String usuarioGuardar(@ModelAttribute Usuario usuario) {
        String nuevoId = generarNuevoId();
        usuario.setId(nuevoId);
        usuarioRepository.insertUsuario(usuario);
        return "redirect:/usuarios";
    }
    
    @GetMapping("/usuarios/{id}/edit")
    public String usuarioEditarForm(@PathVariable String id, Model model) {
        Usuario usuario = usuarioRepository.findUsuarioById(id);
        if(usuario != null){
            model.addAttribute("usuario", usuario);
            return "UsuarioEditar";
        } else {
            return "redirect:/usuarios";
        }
    }
    
    @PostMapping("/usuarios/{id}/edit/save")
    public String usuarioEditarGuardar(@PathVariable int id, @ModelAttribute Usuario usuario) {
        usuarioRepository.updateUsuario(usuario);
        return "redirect:/usuarios";
    }
    
    @GetMapping("/usuarios/{id}/delete")
    public String usuarioEliminar(@PathVariable String id) {
        usuarioRepository.deleteUsuario(id);
        return "redirect:/usuarios";
    }


}
