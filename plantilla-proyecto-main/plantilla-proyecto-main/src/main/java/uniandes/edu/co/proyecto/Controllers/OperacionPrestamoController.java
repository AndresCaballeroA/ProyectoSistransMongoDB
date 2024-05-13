package uniandes.edu.co.proyecto.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.Modelos.Operacion;
import uniandes.edu.co.proyecto.Modelos.OperacionPrestamo;
import uniandes.edu.co.proyecto.Repositorio.OperacionPrestamoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class OperacionPrestamoController {

    // @Autowired
    // private OperacionPrestamoRepository operacionPrestamoRepository;

    // @GetMapping("/operacionPrestamos")
    // public String operacionPrestamo(Model model) {
    //     model.addAttribute("operacionPrestamo", operacionPrestamoRepository.findAllOperacionPrestamos());
    //     return "OperacionPrestamo";
    // }

    // @GetMapping("/operacionPrestamos/new")
    // public String operacionPrestamoform(Model model) {
    //     OperacionPrestamo operacionPrestamo = new OperacionPrestamo();
    //     operacionPrestamo.setId_operacion(new Operacion()); // Inicialización del id_usuario
    //     model.addAttribute("operacionPrestamo", operacionPrestamo);
    //     model.addAttribute("titulo", "Nueva operacionPrestamo");
    //     return "OperacionPrestamoNuevo";
    // }

    // @PostMapping("/operacionPrestamos/new/save")
    // public String operacionPrestamoGuardar(@ModelAttribute OperacionPrestamo operacionPrestamo) {
    //     operacionPrestamoRepository.insertOperacionPrestamo(operacionPrestamo.getNumPrestamo(), operacionPrestamo.getMontoPago(), operacionPrestamo.getId_operacion().getId());
    //     return "redirect:/operacionPrestamos";
    // }

    // @GetMapping("/operacionPrestamos/{id}/edit")
    // public String operacionPrestamoEditarForm(@PathVariable int id, Model model) {
    //     OperacionPrestamo operacionPrestamo = operacionPrestamoRepository.findOperacionPrestamoById(id);
    //     if (operacionPrestamo != null) {
    //         model.addAttribute("operacionPrestamo", operacionPrestamo);
    //         return "OperacionPrestamoEditar";
    //     } else {
    //         return "redirect:/operacionPrestamos";
    //     }
    // }

    // @PostMapping("/operacionPrestamos/{id}/edit/save")
    // public String operacionPrestamoEditarGuardar(@PathVariable int id, @ModelAttribute OperacionPrestamo operacionPrestamo) {
    //     operacionPrestamoRepository.updateOperacionPrestamo(operacionPrestamo.getId(), operacionPrestamo.getNumPrestamo(), operacionPrestamo.getMontoPago());
    //     return "redirect:/operacionPrestamos";
    // }

    // @GetMapping("/operacionPrestamo/{id}/delete")
    // public String operacionPrestamoEliminar(@PathVariable int id) {
    //     operacionPrestamoRepository.deleteOperacionPrestamo(id);
    //     return "redirect:/operacionPrestamos";
    // }
}
