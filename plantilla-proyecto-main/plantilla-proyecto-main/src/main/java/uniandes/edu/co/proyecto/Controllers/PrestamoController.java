package uniandes.edu.co.proyecto.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import uniandes.edu.co.proyecto.Modelos.Cliente;
import uniandes.edu.co.proyecto.Modelos.Operacion;
import uniandes.edu.co.proyecto.Modelos.Prestamo;
import uniandes.edu.co.proyecto.Repositorio.OperacionRepository;
import uniandes.edu.co.proyecto.Repositorio.PrestamoRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class PrestamoController {

//     @Autowired
//     private PrestamoRepository prestamoRepository;

//     @Autowired
//     private OperacionRepository operacionRepository;
    
//     @GetMapping("/prestamos")
//     public String prestamos(Model model, Integer Cliente, String oficina) {

//         @SuppressWarnings("unlikely-arg-type")
//         boolean HayCliente = Cliente == null || ("".equals(Cliente));
//         boolean Hayofi = oficina == null || ("".equals(oficina)) || ("No".equals(oficina));

//         if (!HayCliente && !Hayofi) {
//             model.addAttribute("prestamos", prestamoRepository.darPrestamosPorOficinaDeCliente(Cliente));
//         } else if (!HayCliente) {
//             model.addAttribute("prestamos", prestamoRepository.darPrestamosPorCliente(Cliente));
//         }
//         else {
//         model.addAttribute("prestamos", prestamoRepository.findAllPrestamos());
//         }
//         return "Prestamo";
//     }

//     @GetMapping("/prestamos/new")
//     public String prestamoform(Model model) {
//         Prestamo prestamo = new Prestamo();
//         prestamo.setId_cliente(new Cliente()); // Inicialización del id_usuario
//         model.addAttribute("prestamo", prestamo);
//         model.addAttribute("titulo", "Nuevo prestamo");
//         return "PrestamoNuevo";
//     }

//     @GetMapping("/prestamos/newpago")
//     public String prestamoform2(Model model) {
//         Prestamo prestamo = new Prestamo();
//         prestamo.setId_cliente(new Cliente()); // Inicialización del id_usuario
//         model.addAttribute("prestamo", prestamo);
//         model.addAttribute("titulo", "Nuevo prestamo");
//         return "PagoNuevo";
//     }

//     @PostMapping("/prestamos/new/save")
//     public String prestamoGuardar(@ModelAttribute Prestamo prestamo) throws ParseException {
//         prestamoRepository.insertPrestamo(prestamo.getEstado(), prestamo.getTipo(), prestamo.getMonto(), prestamo.getInteres(), prestamo.getNumCuotas(), prestamo.getNumMes(), prestamo.getValorCuota(), prestamo.getId_cliente().getId());
//         return "redirect:/prestamos";
//     }

//     @PostMapping("/prestamos/new/savepago")
//     public String prestamoGuardar2(@ModelAttribute Prestamo prestamo, @RequestParam("tipo") String tipo) throws ParseException {
//         prestamoRepository.retirarDinero(prestamo.getId(), prestamo.getMonto());
//         LocalDateTime fechaHoraActual = LocalDateTime.now();
//         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//         String fechaHoraString = fechaHoraActual.format(formatter);
//         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//         java.util.Date parsedDate;
//         try {
//             parsedDate = dateFormat.parse(fechaHoraString);
//         } catch (ParseException e) {
//             e.printStackTrace();
//             return "error"; // Maneja el error apropiadamente
//         }
//         java.sql.Date fechaSQL = new java.sql.Date(parsedDate.getTime());
//         operacionRepository.insertOperacion(fechaSQL, tipo);
//         Operacion operacion = operacionRepository.findLastInsertedOperacion();
//         prestamoRepository.insertOperacionPrestamo(prestamo.getId(), prestamo.getMonto(), operacion.getId());
//         return "redirect:/prestamos";
//     }



// @GetMapping("/prestamos/{id}/edit")
// public String prestamoEditarForm(@PathVariable int id, Model model) {
//         Prestamo prestamo = prestamoRepository.findPrestamoById(id);
//         if(prestamo != null){
//             model.addAttribute("prestamo", prestamo);
//             return "PrestamoEditar";
//         } else {
//             return "redirect:/prestamos";
//         }
//     }

//     @PostMapping("/prestamos/{id}/edit/save")
//     public String prestamoEditarGuardar(@PathVariable int id, @ModelAttribute Prestamo prestamo) {
//             prestamoRepository.updatePrestamo(prestamo.getId(), prestamo.getEstado(), prestamo.getTipo(), prestamo.getMonto(), prestamo.getInteres(), prestamo.getNumCuotas(), prestamo.getNumMes(), prestamo.getValorCuota(), prestamo.getId_cliente().getId());
//             return "redirect:/prestamos";
//     }


//     @GetMapping("/prestamos/{id}/delete")
//     public String prestamoEliminar(@PathVariable int id) {
//         prestamoRepository.deletePrestamo(id);
//         return "redirect:/prestamos";
//     }

}
