package uniandes.edu.co.proyecto.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.Modelos.Operacion;
import uniandes.edu.co.proyecto.Modelos.Usuario;
import uniandes.edu.co.proyecto.Repositorio.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.domain.Sort;


@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MongoOperations mongoOperations;

    public String generarNuevoId() {
        
        Query query = new Query().with(Sort.by(Sort.Order.desc("id")));
        
        Usuario ultimaCuenta = mongoOperations.findOne(query, Usuario.class);

        String nuevoId = "1"; 
        if (ultimaCuenta != null) {
            String ultimoId = ultimaCuenta.getId();
            int ultimoNumero = Integer.parseInt(ultimoId);
            nuevoId = String.valueOf(ultimoNumero + 1);  
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
