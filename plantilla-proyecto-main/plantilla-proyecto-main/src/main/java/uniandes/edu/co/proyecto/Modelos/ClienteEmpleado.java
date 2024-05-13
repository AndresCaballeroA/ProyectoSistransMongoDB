package uniandes.edu.co.proyecto.Modelos;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "ClienteEmpleado")
public class ClienteEmpleado implements Serializable {

    @DBRef
    private Empleado empleado;

    @DBRef
    private Cliente cliente;

    public ClienteEmpleado() {
        ;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
