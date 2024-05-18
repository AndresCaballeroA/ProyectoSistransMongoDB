package uniandes.edu.co.proyecto.Controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import uniandes.edu.co.proyecto.Modelos.Cuenta;
import uniandes.edu.co.proyecto.Modelos.Operacion;
import uniandes.edu.co.proyecto.Modelos.OperacionCuenta;
import uniandes.edu.co.proyecto.Repositorio.CuentaRepository;
import uniandes.edu.co.proyecto.Repositorio.OperacionCuentaRepository;
import uniandes.edu.co.proyecto.Repositorio.OperacionRepository;
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

    public String generarNuevoId() {
        int i = 1;
        String nuevoId;
        
        while (true) {
            nuevoId = String.valueOf(i);
            OperacionCuenta oficina = operacionCuentaRepository.findOperacionCuentaById(nuevoId);
            if (oficina == null) {
                break;
            }
            i++;
        }        
        return nuevoId;
    }

    public String generarNuevoId1() {
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

    @SuppressWarnings({ "null", "deprecation" })
    @GetMapping("/operacionCuentas")
    public String operacionCuentas(Model model, String fechaYHora, Integer numeroOrigen, String Mes) throws ParseException {

        Boolean HayFecha = (fechaYHora == null || "".equals(fechaYHora));
        @SuppressWarnings("unlikely-arg-type")
        Boolean HayCuenta = (numeroOrigen == null || "".equals(numeroOrigen));
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
            LocalDateTime ahora = LocalDateTime.now();
            String fechaActual = ahora.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String[] partesFechaActual = fechaActual.split("-");
            String yearActual = partesFechaActual[0];
            Collection<OperacionCuenta> operacionCuentas = operacionCuentaRepository.findByNumeroOrigen(numeroOrigen);
            Integer Dinero = cuentaRepository.findCuentaById(numeroOrigen.toString()).getSaldo();
            
            SimpleDateFormat sdf = new SimpleDateFormat("MM");
            String mesSiguiente = "";
            try {
            Date date = sdf.parse(Mes);
            date.setMonth(date.getMonth() + 1);
            mesSiguiente = sdf.format(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
            int mesInicial = Integer.parseInt(mesSiguiente);
            
            for (int i = mesInicial; i <= 12; i++) {
                String mes = String.format("%02d", i);
                for (OperacionCuenta operacion : operacionCuentas) {

                    String tipoOperacion = operacion.getId_operacion().getTipoOperacion();
                    Integer numeroOrigenO = operacion.getNumeroOrigen();
                    Integer numeroDestino = operacion.getNumeroDestino();
                    String fecha = operacion.getId_operacion().getFechaYHora(); 
                    LocalDateTime fechaHora = LocalDateTime.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    String mesOperacion = String.format("%02d", fechaHora.getMonthValue());
                    String yearOperacion = String.valueOf(fechaHora.getYear());                    
                    
                    if (mesOperacion.equals(mes) && yearActual.equals(yearOperacion)) {
                        if (tipoOperacion.equals("CONSIGNACION") && numeroDestino.equals(numeroOrigen)) {
                            Dinero -= operacion.getMonto();
                        } else if (tipoOperacion.equals("RETIRO") && numeroOrigenO.equals(numeroOrigen)) {
                            Dinero += operacion.getMonto();
                        } else if (tipoOperacion.equals("TRANSFERENCIA") && numeroOrigenO.equals(numeroOrigen) && numeroDestino != numeroOrigen) {
                            Dinero += operacion.getMonto();
                        }
                    }
                }
            }


            int saldo = Dinero;
            int valorInicial = saldo;
            if (Mes != "12") {
                for (OperacionCuenta operacion : operacionCuentas) {
                
                    String tipoOperacion = operacion.getId_operacion().getTipoOperacion();
                    Integer numeroOrigenO = operacion.getNumeroOrigen();
                    Integer numeroDestino = operacion.getNumeroDestino();
                    String fecha = operacion.getId_operacion().getFechaYHora(); 
                    LocalDateTime fechaHora = LocalDateTime.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    String mesOperacion = String.format("%02d", fechaHora.getMonthValue()); 
                    String yearOperacion = String.valueOf(fechaHora.getYear()); 

                    if (mesOperacion.equals(Mes) && yearActual.equals(yearOperacion)) {
                        if (tipoOperacion.equals("CONSIGNACION") && numeroDestino.equals(numeroOrigen)) {
                        
                            valorInicial -= operacion.getMonto();
                        } else if (tipoOperacion.equals("RETIRO") && numeroOrigenO.equals(numeroOrigen)) {
                            
                            valorInicial += operacion.getMonto();
                        } else if (tipoOperacion.equals("TRANSFERENCIA") && numeroOrigenO.equals(numeroOrigen) && numeroDestino != numeroOrigen) {
                            
                            valorInicial += operacion.getMonto();
                        }
                    }
                
                }
            } else {
                for (OperacionCuenta operacion : operacionCuentas) {
                
                    String tipoOperacion = operacion.getId_operacion().getTipoOperacion();
                    Integer numeroOrigenO = operacion.getNumeroOrigen();
                    Integer numeroDestino = operacion.getNumeroDestino();
                    String fecha = operacion.getId_operacion().getFechaYHora(); 
                    LocalDateTime fechaHora = LocalDateTime.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    String mesOperacion = String.format("%02d", fechaHora.getMonthValue()); 
                    String yearOperacion = String.valueOf(fechaHora.getYear()); 
                    
                    if (mesOperacion.equals(Mes) && yearActual.equals(yearOperacion)) {
                        if (tipoOperacion.equals("CONSIGNACION") && numeroDestino.equals(numeroOrigen)) {
                            saldo += operacion.getMonto();
                        } else if (tipoOperacion.equals("RETIRO") && numeroOrigenO.equals(numeroOrigen)) {
                            saldo -= operacion.getMonto();
                        } else if (tipoOperacion.equals("TRANSFERENCIA") && numeroOrigenO.equals(numeroOrigen) && numeroDestino != numeroOrigen) {
                            saldo -= operacion.getMonto();
                        }
                    }
                }
            }

            Collection<OperacionCuenta> operacionesFiltradas = new ArrayList<>();


            for (OperacionCuenta operacion : operacionCuentas) {
                String fecha = operacion.getId_operacion().getFechaYHora(); // Suponiendo que tienes un método getId_operacion().getFechaYHora() para obtener la fecha y hora como cadena
                LocalDateTime fechaHora = LocalDateTime.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                String mesOperacion = String.format("%02d", fechaHora.getMonthValue()); // Obtener el mes de la operación como un string con dos dígitos
                
                if (mesOperacion.equals(Mes)) {
                    operacionesFiltradas.add(operacion);
                }
            }

            model.addAttribute("saldoini", valorInicial);
            model.addAttribute("saldofini", saldo);
            model.addAttribute("operacionCuentas", operacionesFiltradas);
        } else if (!HayCuenta && HayMes){
            model.addAttribute("operacionCuentas", operacionCuentaRepository.findByNumeroOrigen(numeroOrigen));
        } else if (HayCuenta && !HayMes){
            
            model.addAttribute("operacionCuentas", operacionCuentaRepository.findByMonth(Mes));
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
}
