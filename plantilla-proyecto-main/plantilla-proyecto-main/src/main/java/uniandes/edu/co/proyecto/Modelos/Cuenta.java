package uniandes.edu.co.proyecto.Modelos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Document(collection = "Cuenta")
public class Cuenta {

    @Id
    private String id;

    private Integer saldo;

    private String tipo;

    private String estado;

    private String fechaCreacion;

    @DBRef
    private Cliente cliente;

    public Cuenta(String id, Integer saldo, String tipo, String estado, String fechaCreacion, Cliente cliente) {
        this.id = id;
        this.saldo = saldo;
        this.tipo = tipo;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.cliente = cliente;
    }

    public Cuenta() {
        ;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Cliente getCliente() {
        return cliente;
    }
    

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
