package uniandes.edu.co.proyecto.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.proyecto.Modelos.ClienteEmpleado;
import uniandes.edu.co.proyecto.Repositorio.ClienteEmpleadoRepository;
import java.util.List;

@RestController
@RequestMapping("/clienteEmpleados")
public class ClienteEmpleadoController {

    @Autowired
    private ClienteEmpleadoRepository clienteEmpleadoRepository;

    @GetMapping
    public List<ClienteEmpleado> getAllClienteEmpleados() {
        return clienteEmpleadoRepository.findAll();
    }

    // @GetMapping("/{idEmpleado}/{idCliente}")
    // public ClienteEmpleado getClienteEmpleadoById(@PathVariable Integer idEmpleado, @PathVariable Integer idCliente) {
        // ClienteEmpleado clienteEmpleado = clienteEmpleadoRepository.findClienteEmpleadoById(idEmpleado, idCliente);
        // return clienteEmpleado; }

    @PostMapping
    public ClienteEmpleado createClienteEmpleado(@RequestBody ClienteEmpleado clienteEmpleado) {
        return clienteEmpleadoRepository.save(clienteEmpleado);
    }

    @PutMapping("/{idEmpleado}/{idCliente}")
    public ResponseEntity<?> updateClienteEmpleado(@PathVariable int idEmpleado, @PathVariable int idCliente,
            @RequestBody ClienteEmpleado clienteEmpleado) {
        // clienteEmpleadoRepository.updateClienteEmpleado(idEmpleado, idCliente);
        return ResponseEntity.ok("ClienteEmpleado actualizado con éxito");
    }

    @DeleteMapping("/{idEmpleado}/{idCliente}")
    public ResponseEntity<?> deleteClienteEmpleado(@PathVariable int idEmpleado, @PathVariable int idCliente) {
        // clienteEmpleadoRepository.deleteClienteEmpleado(idEmpleado, idCliente);
        return ResponseEntity.ok("ClienteEmpleado eliminado con éxito");
    }
}
