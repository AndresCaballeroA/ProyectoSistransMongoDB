package uniandes.edu.co.proyecto.Modelos;

import java.io.Serializable;
import java.util.Objects;

public class ClienteEmpleadoId implements Serializable {
    private int empleado; // Corresponds to the ID of Empleado
    private int cliente; // Corresponds to the ID of Cliente

    // Default constructor
    public ClienteEmpleadoId() {
    }

    // Constructor with parameters
    public ClienteEmpleadoId(int empleado, int cliente) {
        this.empleado = empleado;
        this.cliente = cliente;
    }

    // Getters and setters
    public int getEmpleado() {
        return empleado;
    }

    public void setEmpleado(int empleado) {
        this.empleado = empleado;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    // equals and hashCode

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof ClienteEmpleadoId))
            return false;
        ClienteEmpleadoId that = (ClienteEmpleadoId) o;
        return getEmpleado() == that.getEmpleado() &&
                getCliente() == that.getCliente();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmpleado(), getCliente());
    }
}
