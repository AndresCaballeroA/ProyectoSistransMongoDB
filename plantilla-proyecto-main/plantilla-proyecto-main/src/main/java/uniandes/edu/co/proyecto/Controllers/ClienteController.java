package uniandes.edu.co.proyecto.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.stereotype.Controller;
import uniandes.edu.co.proyecto.Modelos.Cliente;
import uniandes.edu.co.proyecto.Modelos.Oficina;
import uniandes.edu.co.proyecto.Modelos.Usuario;
import uniandes.edu.co.proyecto.Repositorio.ClienteRepository;
import uniandes.edu.co.proyecto.Repositorio.EmpleadoRepository;
import uniandes.edu.co.proyecto.Repositorio.OficinaRepository;
import uniandes.edu.co.proyecto.Repositorio.UsuarioRepository;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private OficinaRepository oficinaRepository;

    @Autowired
    private MongoOperations mongoOperations;

    public String generarNuevoId() {
        
        Query query = new Query().limit(1).with(Sort.by(Sort.Order.desc("id")));
        Cliente ultimaCliente = mongoOperations.findOne(query, Cliente.class);

        String nuevoId = "1";
        if (ultimaCliente != null) {
            String ultimoId = ultimaCliente.getId();
            int ultimoNumero = Integer.parseInt(ultimoId); 
            nuevoId = String.valueOf(ultimoNumero + 1); 
        }
        return nuevoId;
    }
    @GetMapping("/clientes")
    public String clientes(Model model, String Cliente, String oficina) {

        boolean HayCliente = Cliente == null || ("".equals(Cliente));
        boolean Hayofi = oficina == null || ("".equals(oficina)) || "No".equals(oficina);

        if (!HayCliente && !Hayofi){
            //model.addAttribute("clientes", clienteRepository.darClientesPorOficinaDeCliente(Cliente));
        }
        else if (!HayCliente) {
            //model.addAttribute("clientes", clienteRepository.darCuentasPorCliente(Cliente));
        }
        else {
            model.addAttribute("clientes", clienteRepository.findAllClientes());
        }
        return "Cliente";
    }

    @GetMapping("/clientes/new")
    public String clienteform(Model model) {
        Cliente cliente = new Cliente();
        cliente.setOficina(new Oficina());
        cliente.setUsuario(new Usuario());
        model.addAttribute("cliente", cliente);
        return "ClienteNuevo";
    }

    @PostMapping("/clientes/new/save")
    public String clienteGuardar(@ModelAttribute Cliente cliente, String usuarioID, String oficinaID) {
        String nuevoId = generarNuevoId();
        cliente.setId(nuevoId);
        if ((usuarioRepository.findUsuarioById(usuarioID) != null) && (oficinaRepository.findOficinaById(oficinaID) != null)) {
            cliente.setOficina(oficinaRepository.findOficinaById(oficinaID));
            cliente.setUsuario(usuarioRepository.findUsuarioById(usuarioID));
            clienteRepository.insertCliente(cliente);
        }
        return "redirect:/clientes";
    }

    @GetMapping("/clientes/{id}/edit")
    public String clienteEditarForm(@PathVariable String id, Model model) {
        Cliente cliente = clienteRepository.findClienteById(id);
        if (cliente != null) {
            model.addAttribute("cliente", cliente);
            return "ClienteEditar";
        } else {
            return "redirect:/clientes";
        }}

    @PostMapping("/clientes/{id}/edit/save")
    public String clienteEditarGuardar(@PathVariable String id, @ModelAttribute Cliente cliente) {
        cliente.setOficina(oficinaRepository.findOficinaById(cliente.getOficina().getId()));
        cliente.setUsuario(usuarioRepository.findUsuarioById(cliente.getUsuario().getId()));
        clienteRepository.updateCliente(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/clientes/{id}/delete")
    public String clienteEliminar(@PathVariable String id) {
        clienteRepository.deleteCliente(id);
        return "redirect:/clientes";
    }

}
