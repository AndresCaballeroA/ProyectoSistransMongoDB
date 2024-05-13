package uniandes.edu.co.proyecto.Controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.Modelos.Cuenta;
import uniandes.edu.co.proyecto.Modelos.Empleado;
import uniandes.edu.co.proyecto.Modelos.Operacion;
import uniandes.edu.co.proyecto.Modelos.OperacionCuenta;
import uniandes.edu.co.proyecto.Repositorio.CuentaRepository;
import uniandes.edu.co.proyecto.Repositorio.OperacionCuentaRepository;
import uniandes.edu.co.proyecto.Repositorio.OperacionRepository;
import uniandes.edu.co.proyecto.Services.OperacionCuentaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.text.SimpleDateFormat;
import java.text.ParseException;

@Controller
public class OperacionCuentaController {

    @Autowired
    private OperacionCuentaRepository operacionCuentaRepository;

    @Autowired
    private OperacionRepository operacionRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    // @Autowired
    // private OperacionCuentaService operacionCuentaService;

    @Autowired
    private MongoOperations mongoOperations;

    public String generarNuevoId() {
        
        Query query = new Query().limit(1).with(Sort.by(Sort.Order.desc("id")));
        OperacionCuenta ultimoOp = mongoOperations.findOne(query, OperacionCuenta.class);

        String nuevoId = "1";
        if (ultimoOp != null) {
            String ultimoId = ultimoOp.getId();
            int ultimoNumero = Integer.parseInt(ultimoId); 
            nuevoId = String.valueOf(ultimoNumero + 1); 
        }
        return nuevoId;
    }

    public String generarNuevoId1() {
        
        Query query = new Query().limit(1).with(Sort.by(Sort.Order.desc("id")));
        Operacion ultimoOp = mongoOperations.findOne(query, Operacion.class);

        String nuevoId = "1";
        if (ultimoOp != null) {
            String ultimoId = ultimoOp.getId();
            int ultimoNumero = Integer.parseInt(ultimoId); 
            nuevoId = String.valueOf(ultimoNumero+1); 
        }
        return nuevoId;
    }

    @SuppressWarnings("null")
    @GetMapping("/operacionCuentas")
    public String operacionCuentas(Model model, String fechaYHora, Integer Cuenta, Integer Mes) throws ParseException {

        Boolean HayFecha = (fechaYHora == null || "".equals(fechaYHora));
        @SuppressWarnings("unlikely-arg-type")
        Boolean HayCuenta = (Cuenta == null || "".equals(Cuenta));
        @SuppressWarnings("unlikely-arg-type")
        Boolean HayMes = (Mes == null || "".equals(Mes));
        if (!HayFecha && HayCuenta && HayMes) {
            SimpleDateFormat dateFormat;
            java.util.Date parsedDate;
            if (fechaYHora.matches("\\d{2}/\\d{2}/\\d{4}")) {
                dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                parsedDate = dateFormat.parse(fechaYHora);
            } else {
                dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                parsedDate = dateFormat.parse(fechaYHora);
            }
            java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime());
            String fechaFormateada = dateFormat.format(sqlDate);
            model.addAttribute("operacionCuentas", operacionCuentaRepository.findById_operacionFechaYHora(fechaFormateada));
        } else if (!HayCuenta && !HayMes) {
            // Integer saldo = cuentaRepository.DineroCuenta(Cuenta);
            // Integer saldoretiro = operacionCuentaRepository
            //         .calcularSumaSaldosRetirosPorNumeroCuentaOrigen(Cuenta) != null
            //                 ? operacionCuentaRepository.calcularSumaSaldosRetirosPorNumeroCuentaOrigen(Cuenta)
            //                 : 0;
            // Integer saldotrans = operacionCuentaRepository.calcularSumaTransferenciasSalientes(Cuenta) != null
            //         ? operacionCuentaRepository.calcularSumaTransferenciasSalientes(Cuenta)
            //         : 0;

            // Integer valorInicial = saldo + saldoretiro + saldotrans;
            // model.addAttribute("saldoini", valorInicial);
            // model.addAttribute("saldofini", saldo);
            // model.addAttribute("operacionCuentas", operacionCuentaRepository.FiltrarPorCM(Cuenta, Mes));
        } else if (!HayCuenta && HayMes){
            //model.addAttribute("operacionCuentas", operacionCuentaRepository.FiltrarPorC(Cuenta));
        } else if (HayCuenta && !HayMes){
            //model.addAttribute("operacionCuentas", operacionCuentaRepository.FiltrarPorM(Mes));
        } else {
            model.addAttribute("operacionCuentas", operacionCuentaRepository.findAllOperacionCuentas());
        }
        return "OperacionCuenta";
    }

    @GetMapping("/operacionCuentas/new")
    public String operacionCuentaform(Model model) {
        OperacionCuenta operacionCuenta = new OperacionCuenta();
        operacionCuenta.setId_operacion(new Operacion()); // Inicialización del id_usuario
        model.addAttribute("operacionCuenta", operacionCuenta);
        model.addAttribute("titulo", "Nueva operacionCuenta");
        return "OperacionCuentaNueva";
    }

    @GetMapping("/operacionCuentas/newcons")
    public String operacionCuentaform2(Model model) {
        OperacionCuenta operacionCuenta = new OperacionCuenta();
        operacionCuenta.setId_operacion(new Operacion()); // Inicialización del id_usuario
        model.addAttribute("operacionCuenta", operacionCuenta);
        model.addAttribute("titulo", "Nueva operacionCuenta");
        return "OperacionCuentaNuevaCons";
    }

    @GetMapping("/operacionCuentas/newret")
    public String operacionCuentaform3(Model model) {
        OperacionCuenta operacionCuenta = new OperacionCuenta();
        operacionCuenta.setId_operacion(new Operacion());
        model.addAttribute("operacionCuenta", operacionCuenta);
        model.addAttribute("titulo", "Nueva operacionCuenta");
        return "OperacionCuentaNuevaRet";
    }

    @PostMapping("/operacionCuentas/new/save")
    public String operacionCuentaGuardar(@ModelAttribute OperacionCuenta operacionCuenta) {
        String nuevoId = generarNuevoId();
        operacionCuenta.setId(nuevoId);
        operacionCuenta.setId_operacion(operacionRepository.findOperacionById(operacionCuenta.getId_operacion().getId()));
        operacionCuentaRepository.insertOperacionCuenta(operacionCuenta);
        return "redirect:/operacionCuentas";
    }

    @PostMapping("/operacionCuentas/new/savecons")
    public String operacionCuentaGuardar2(@ModelAttribute OperacionCuenta operacionCuenta) {
        Operacion operacion = new Operacion();
        operacion.setId(generarNuevoId1());
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaHoraString = fechaHoraActual.format(formatter);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date parsedDate;
        try {
            parsedDate = dateFormat.parse(fechaHoraString);
        } catch (ParseException e) {
            e.printStackTrace();
            return "error"; 
        }
        java.sql.Date fechaSQL = new java.sql.Date(parsedDate.getTime());
        String fechaFormateada = dateFormat.format(fechaSQL);
        if (operacionCuenta.getNumeroDestino() == operacionCuenta.getNumeroOrigen()){
            operacion.setFechaYHora(fechaFormateada);
            operacion.setTipoOperacion("CONSIGNACION");
            operacionRepository.insertOperacion(operacion);
            operacion = operacionRepository.findOperacionById(operacion.getId());
            operacionCuenta.setId_operacion(operacion);
            operacionCuenta.setId(generarNuevoId());
            operacionCuentaRepository.insertOperacionCuenta(operacionCuenta);
            Cuenta cuenta = cuentaRepository.findCuentaById(operacionCuenta.getNumeroDestino().toString());
            cuenta.setSaldo(cuenta.getSaldo()+operacionCuenta.getMonto());
            cuentaRepository.updateCuenta(cuenta);
        }
        else {
            operacion.setFechaYHora(fechaFormateada);
            operacion.setTipoOperacion("TRANSFERENCIA");
            operacionRepository.insertOperacion(operacion);
            operacionRepository.insertOperacion(operacion);
            operacion = operacionRepository.findOperacionById(operacion.getId());
            operacionCuenta.setId_operacion(operacion);
            operacionCuenta.setId(generarNuevoId());
            operacionCuentaRepository.insertOperacionCuenta(operacionCuenta);
            Cuenta cuenta = cuentaRepository.findCuentaById(operacionCuenta.getNumeroOrigen().toString());
            if (cuenta.getSaldo() >= operacionCuenta.getMonto()){
                cuenta.setSaldo(cuenta.getSaldo()-operacionCuenta.getMonto());
                cuentaRepository.updateCuenta(cuenta);
                cuenta = cuentaRepository.findCuentaById(operacionCuenta.getNumeroDestino().toString());
                cuenta.setSaldo(cuenta.getSaldo()+operacionCuenta.getMonto());
                cuentaRepository.updateCuenta(cuenta);
            }
        }

        return "redirect:/operacionCuentas";
    }

    @PostMapping("/operacionCuentas/new/saveret")
    public String operacionCuentaGuardar3(@ModelAttribute OperacionCuenta operacionCuenta) {
        LocalDateTime fechaHoraActual = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaHoraString = fechaHoraActual.format(formatter);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date parsedDate;
        try {
            parsedDate = dateFormat.parse(fechaHoraString);
        } catch (ParseException e) {
            e.printStackTrace();
            return "error"; // Maneja el error apropiadamente
        }
        java.sql.Date fechaSQL = new java.sql.Date(parsedDate.getTime());
        String fechaFormateada = dateFormat.format(fechaSQL);
        Operacion operacion = new Operacion();
        operacion.setId(generarNuevoId1());
        operacion.setFechaYHora(fechaFormateada);
        operacion.setTipoOperacion("RETIRO");
        operacionRepository.insertOperacion(operacion);
        operacionCuenta.setId_operacion(operacionRepository.findOperacionById(operacion.getId()));
        operacionCuenta.setNumeroDestino(operacionCuenta.getNumeroOrigen());
        operacionCuenta.setId(generarNuevoId());
        operacionCuentaRepository.insertOperacionCuenta(operacionCuenta);
        Cuenta cuenta = cuentaRepository.findCuentaById(operacionCuenta.getNumeroOrigen().toString());
        if (cuenta.getSaldo() >= operacionCuenta.getMonto()) {
            cuenta.setSaldo(cuenta.getSaldo()-operacionCuenta.getMonto());
            cuentaRepository.updateCuenta(cuenta);
        }
        return "redirect:/operacionCuentas";
    }

    @GetMapping("/operacionCuentas/{id}/edit")
    public String operacionCuentaEditarForm(@PathVariable String id, Model model) {
        OperacionCuenta operacionCuenta = operacionCuentaRepository.findOperacionCuentaById(id);
        if (operacionCuenta != null) {
            model.addAttribute("operacionCuenta", operacionCuenta);
            return "OperacionCuentaEditar";
        } else {
            return "redirect:/operacionCuentas";
        }
    }

    @PostMapping("/operacionCuentas/{id}/edit/save")
    public String operacionCuentaEditarGuardar(@PathVariable String id, @ModelAttribute OperacionCuenta operacionCuenta) {
        operacionCuenta.setId_operacion(operacionRepository.findOperacionById(operacionCuenta.getId_operacion().getId()));
        operacionCuentaRepository.updateOperacionCuenta(operacionCuenta);
        return "redirect:/operacionCuentas";
    }

    @GetMapping("/operacionCuentas/{id}/delete")
    public String operacionCuentaEliminar(@PathVariable String id) {
        operacionCuentaRepository.deleteOperacionCuenta(id);
        return "redirect:/operacionCuentas";
    }

    // @GetMapping("/operacionCuentas/newser")
    // public String cuentaformser(Model model) {
    //     return "SerializableFormNuevo";
    // }

    // @GetMapping("/operacionCuentas/serializable")
    // public String consultarOperacionesSerializable(@RequestParam("id") Integer id, Model model) {
    //     System.out.println(id);
    //     try{
    //         Collection<OperacionCuenta> operaciones = operacionCuentaService.darOperacionesSerializable(id);
    //         model.addAttribute("operacionCuentas", operaciones);
    //     }
    //     catch (Exception e){
    //         System.err.println("Error en la consulta de cuentas:" + e.getMessage());
    //     }
    //     return "OperacionCuenta";
    // }

    // @GetMapping("/operacionCuentas/newnoser")
    // public String cuentaformnoser(Model model) {
    //     return "SerializableFormNuevo1";
    // }
    // @GetMapping("/operacionCuentas/noserializable")
    // public String consultarOperacionesNoSerializable(@RequestParam("id") Integer id, Model model) {
    //     try{
    //         Collection<OperacionCuenta> operaciones = operacionCuentaService.darOperacionesNoSerializable(id);
    //         model.addAttribute("operacionCuentas", operaciones);
    //     }
    //     catch (Exception e){
    //         System.err.println("Error en la consulta de cuentas:" + e.getMessage());
    //     }
    //     return "OperacionCuenta";
    // }

}
