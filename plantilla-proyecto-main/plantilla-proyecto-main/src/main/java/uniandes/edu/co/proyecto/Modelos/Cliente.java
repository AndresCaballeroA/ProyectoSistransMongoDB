package uniandes.edu.co.proyecto.Modelos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Cliente")
public class Cliente {

    @Id
    private String id;

    @DBRef
    private Usuario usuario;

    private String rolCliente;

    @DBRef
    private Oficina oficina;

    public Cliente(String id, Usuario usuario, String rolCliente, Oficina oficina) {
        this.id = id;
        this.usuario = usuario;
        this.rolCliente = rolCliente;
        this.oficina = oficina;
    }

    public Cliente() {
        ;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getRolCliente() {
        return rolCliente;
    }

    public void setRolCliente(String rolCliente) {
        this.rolCliente = rolCliente;
    }

    public Oficina getOficina() {
        return oficina;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }
}
