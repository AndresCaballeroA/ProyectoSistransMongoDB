package uniandes.edu.co.proyecto.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import uniandes.edu.co.proyecto.Modelos.Cliente;
import uniandes.edu.co.proyecto.Modelos.Cuenta;
import uniandes.edu.co.proyecto.Modelos.Oficina;
import uniandes.edu.co.proyecto.Repositorio.CuentaRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;


@Controller
public class CuentaController {

    @Autowired
    private CuentaRepository cuentaRepository;


    @Autowired
    private MongoOperations mongoOperations;

    public String generarNuevoId() {
        
        Query query = new Query().limit(1).with(Sort.by(Sort.Order.desc("id")));
        Cuenta ultimaCuenta = mongoOperations.findOne(query, Cuenta.class);

        String nuevoId = "1";
        if (ultimaCuenta != null) {
            String ultimoId = ultimaCuenta.getId();
            int ultimoNumero = Integer.parseInt(ultimoId); 
            nuevoId = String.valueOf(ultimoNumero + 1); 
        }
        return nuevoId;
    }
    @SuppressWarnings({ "unlikely-arg-type", "null" })
    @GetMapping("/cuentas")
    public String cuentas(Model model, String TipoCuenta, Integer minSaldo, Integer Maxsaldo, String fechaCreacion,
            String cliente, String oficina) throws ParseException {

        boolean HayFecha = fechaCreacion == null || ("".equals(fechaCreacion));
        boolean Haymin = minSaldo == null || ("".equals(minSaldo));
        boolean Haymax = Maxsaldo == null || ("".equals(Maxsaldo));
        boolean HayCliente = cliente == null || ("".equals(cliente));
        boolean HayTipo = TipoCuenta == null || ("".equals(TipoCuenta));
        boolean Hayofi = oficina == null || ("".equals(oficina)) || ("No".equals(oficina));

        if (!HayCliente && !Hayofi){
            //model.addAttribute("cuentas", cuentaRepository.darCuentasPorOficinaDeCliente(Cliente));
        } else if (!HayCliente && HayFecha && Haymin && Haymax && HayTipo) {
            model.addAttribute("cuentas", cuentaRepository.darCuentasPorCliente(cliente));
        } else if (!HayCliente && !HayFecha && !Haymin && !Haymax && !HayTipo) {
            SimpleDateFormat dateFormat;
            java.util.Date parsedDate;
            if (fechaCreacion.matches("\\d{2}/\\d{2}/\\d{4}")) {
                dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                parsedDate = dateFormat.parse(fechaCreacion);
            } else {
                dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                parsedDate = dateFormat.parse(fechaCreacion);
            }
            java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
            String fechaFormateada = dateFormat.format(sqlDate);
            model.addAttribute("cuentas", cuentaRepository.findByTipoAndSaldoBetweenAndFechaCreacion(TipoCuenta, minSaldo, Maxsaldo, fechaFormateada));
        } else if (HayCliente && !HayFecha && Haymin && Haymax && HayTipo) {
            SimpleDateFormat dateFormat;
            java.util.Date parsedDate;
            if (fechaCreacion.matches("\\d{2}/\\d{2}/\\d{4}")) {
                dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                parsedDate = dateFormat.parse(fechaCreacion);
            } else {
                dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                parsedDate = dateFormat.parse(fechaCreacion);
            }
            java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
            String fechaFormateada = dateFormat.format(sqlDate);
            model.addAttribute("cuentas", cuentaRepository.darCuentasPorFecha(fechaFormateada));
        } else if (HayCliente && HayFecha && (!Haymin || !Haymax) && HayTipo) {
            if (Haymin) {
                minSaldo = 0;
                model.addAttribute("cuentas", cuentaRepository.findBySaldoBetween(minSaldo, Maxsaldo));
            }
            else if (Haymax) {
                Maxsaldo = Integer.MAX_VALUE;
                model.addAttribute("cuentas", cuentaRepository.findBySaldoBetween(minSaldo, Maxsaldo));
            }
            else {
                model.addAttribute("cuentas", cuentaRepository.findBySaldoBetween(minSaldo, Maxsaldo));
            }
        } else if (HayCliente && HayFecha && Haymin && Haymax && !HayTipo) {
            model.addAttribute("cuentas", cuentaRepository.darCuentasPortipo(TipoCuenta));
        } else {
            model.addAttribute("cuentas", cuentaRepository.findAllCuentas());
        }

        return "Cuenta";
    }

    @GetMapping("/cuentas/new")
    public String cuentaform(Model model) {
        Cuenta cuenta = new Cuenta();
        cuenta.setCliente(new Cliente());
        model.addAttribute("cuenta", cuenta);
        return "CuentaNuevo";
    }

    @GetMapping("/cuentas/newest")
    public String cuentaform2(Model model) {
        model.addAttribute("cuenta", new Cuenta());
        return "CuentaNuevoEst";
    }

    @PostMapping("/cuentas/new/save")
    public String cuentaGuardar(@ModelAttribute Cuenta cuenta) throws ParseException {
        String nuevoId = generarNuevoId();
        cuenta.setId(nuevoId);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date parsedDate = dateFormat.parse(cuenta.getFechaCreacion());
        java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
        String fechaFormateada = dateFormat.format(sqlDate);
        cuenta.setFechaCreacion(fechaFormateada);
        cuentaRepository.insertCuenta(cuenta);
        return "redirect:/cuentas";
    }

    @PostMapping("/cuentas/new/saveest")
    public String cuentaGuardar2(@ModelAttribute Cuenta cuenta1) throws ParseException {
        Cuenta cuenta = cuentaRepository.findCuentaById(cuenta1.getId());
        SimpleDateFormat dateFormat;
        java.util.Date parsedDate;
        if (cuenta.getFechaCreacion().matches("\\d{2}/\\d{2}/\\d{4}")) {
            dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            parsedDate = dateFormat.parse(cuenta.getFechaCreacion());
        } else {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            parsedDate = dateFormat.parse(cuenta.getFechaCreacion());
        }
        java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
        String fechaFormateada = dateFormat.format(sqlDate);
        cuenta.setFechaCreacion(fechaFormateada);

        if ((cuenta.getEstado().contains("Activ") && cuenta1.getEstado().contains("Desactiv")) || (cuenta.getSaldo() == 0 && cuenta1.getEstado().contains("Cerrad"))){
            cuenta.setEstado(cuenta1.getEstado());
            cuentaRepository.updateCuenta(cuenta);
        }
        return "redirect:/cuentas";
    }

@GetMapping("/cuentas/{id}/edit")
public String cuentaEditarForm(@PathVariable String id, Model model) {
    Cuenta cuenta = cuentaRepository.findCuentaById(id);
    if (cuenta != null) {
        String fechaString = cuenta.getFechaCreacion();
        if (fechaString != null) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
                java.util.Date parsedDate = dateFormat.parse(fechaString);
                java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
                
                String fechaFormateada = new SimpleDateFormat("dd/MM/yyyy").format(sqlDate);
                model.addAttribute("fechaCreacionFormateada", fechaFormateada);
            } catch (ParseException e) {
                model.addAttribute("fechaCreacionFormateada", "");
                e.printStackTrace(); 
            }
        } else {
            model.addAttribute("fechaCreacionFormateada", "");
        }
        model.addAttribute("cuenta", cuenta);
        return "CuentaEditar";
    } else {
        return "redirect:/cuentas";
    }
}

    @PostMapping("/cuentas/{id}/edit/save")
    public String cuentaEditarGuardar(@PathVariable int id, @ModelAttribute Cuenta cuenta) throws ParseException {
        SimpleDateFormat dateFormat;
        java.util.Date parsedDate;
        if (cuenta.getFechaCreacion().matches("\\d{2}/\\d{2}/\\d{4}")) {
            dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            parsedDate = dateFormat.parse(cuenta.getFechaCreacion());
        } else {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            parsedDate = dateFormat.parse(cuenta.getFechaCreacion());
        }
        java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
        String fechaFormateada = dateFormat.format(sqlDate);
        cuenta.setFechaCreacion(fechaFormateada);
        cuentaRepository.updateCuenta(cuenta);
        return "redirect:/cuentas";
    }


    @GetMapping("/cuentas/{id}/delete")
    public String cuentaEliminar(@PathVariable String id) {
        cuentaRepository.deleteCuenta(id);
        return "redirect:/cuentas";
    }
    
}
