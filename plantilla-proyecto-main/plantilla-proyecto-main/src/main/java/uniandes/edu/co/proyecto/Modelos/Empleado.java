package uniandes.edu.co.proyecto.Modelos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Empleado")
public class Empleado {

    @Id
    private String id;

    private String rolempleado;

    private Usuario id_usuario;

    public Empleado(String id, Usuario id_usuario, String rolempleado) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.rolempleado = rolempleado;
    }

    public Empleado() {
        ;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Usuario getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Usuario id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getRolempleado() {
        return rolempleado;
    }

    public void setRolempleado(String rolempleado) {
        this.rolempleado = rolempleado;
    }
}
