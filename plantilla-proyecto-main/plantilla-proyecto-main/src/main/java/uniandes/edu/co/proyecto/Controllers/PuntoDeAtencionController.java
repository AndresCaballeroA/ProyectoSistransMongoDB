package uniandes.edu.co.proyecto.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.Modelos.Empleado;
import uniandes.edu.co.proyecto.Modelos.Oficina;
import uniandes.edu.co.proyecto.Modelos.PuntoDeAtencion;
import uniandes.edu.co.proyecto.Modelos.Usuario;
import uniandes.edu.co.proyecto.Repositorio.OficinaRepository;
import uniandes.edu.co.proyecto.Repositorio.PuntoDeAtencionRepository;
import uniandes.edu.co.proyecto.Repositorio.UsuarioRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class PuntoDeAtencionController {

    @Autowired
    private PuntoDeAtencionRepository puntoDeAtencionRepository;

    @Autowired
    private OficinaRepository oficinaRepository;

    @Autowired
    private MongoOperations mongoOperations;

    public String generarNuevoId() {
        
        Query query = new Query().limit(1).with(Sort.by(Sort.Order.desc("id")));
        PuntoDeAtencion ultimoPunto = mongoOperations.findOne(query, PuntoDeAtencion.class);

        String nuevoId = "1";
        if (ultimoPunto != null) {
            String ultimoId = ultimoPunto.getId();
            int ultimoNumero = Integer.parseInt(ultimoId); 
            nuevoId = String.valueOf(ultimoNumero + 1); 
        }
        return nuevoId;
    }

    @GetMapping("/puntosDeAtencion")
    public String puntosDeAtencion(Model model) {
        model.addAttribute("puntosDeAtencion", puntoDeAtencionRepository.findAllPuntoDeAtencion());
        return "PuntoDeAtencion";
    }
    
    @GetMapping("/puntosDeAtencion/new")
    public String puntoDeAtencionform(Model model) {
        PuntoDeAtencion puntoDeAtencion = new PuntoDeAtencion();
        puntoDeAtencion.setOficina(new Oficina()); 
        model.addAttribute("puntoDeAtencion", puntoDeAtencion);
        return "PuntoDeAtencionNuevo";
    }

    @PostMapping("/puntosDeAtencion/new/save")
    public String puntoDeAtencionGuardar(@ModelAttribute PuntoDeAtencion puntoDeAtencion) {
        String nuevoId = generarNuevoId();
        puntoDeAtencion.setId(nuevoId);
        if (oficinaRepository.findOficinaById(puntoDeAtencion.getOficina().getId()) != null){
            puntoDeAtencion.setOficina(oficinaRepository.findOficinaById(puntoDeAtencion.getOficina().getId()));
            puntoDeAtencionRepository.insertPuntoDeAtencion(puntoDeAtencion);
        } else if (puntoDeAtencion.getTipo().equals("Digital")) {
            Oficina oficina = new Oficina();
            oficina.setId("Null");
            puntoDeAtencion.setOficina(oficina);
            puntoDeAtencionRepository.insertPuntoDeAtencion(puntoDeAtencion);
        }
    
        return "redirect:/puntosDeAtencion";
    }
    
    @GetMapping("/puntosDeAtencion/{id}/edit")
    public String puntoDeAtencionEditarForm(@PathVariable String id, Model model) {
        PuntoDeAtencion puntoDeAtencion = puntoDeAtencionRepository.findPuntoDeAtencionById(id);
        if(puntoDeAtencion != null){
            model.addAttribute("puntoDeAtencion", puntoDeAtencion);
            return "PuntoDeAtencionEditar";
        } else {
            return "redirect:/puntosDeAtencion";
        }
    }
    
    @PostMapping("/puntosDeAtencion/{id}/edit/save")
    public String puntoDeAtencionEditarGuardar(@PathVariable String id, @ModelAttribute PuntoDeAtencion puntoDeAtencion) {
        puntoDeAtencion.setOficina(oficinaRepository.findOficinaById(puntoDeAtencion.getOficina().getId()));
        puntoDeAtencionRepository.updatePuntoDeAtencion(puntoDeAtencion);
        return "redirect:/puntosDeAtencion";
    }
    
    @GetMapping("/puntosDeAtencion/{id}/delete")
    public String puntoDeAtencionEliminar(@PathVariable String id) {
        puntoDeAtencionRepository.deletePuntoDeAtencion(id);
        return "redirect:/puntosDeAtencion";
    }
}
