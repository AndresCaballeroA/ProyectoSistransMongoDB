package uniandes.edu.co.proyecto.Modelos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Operacion")
public class Operacion {
    
    @Id
    private String id;
    
    private String fechaYHora;
    
    private String tipoOperacion;
    
    public Operacion(String id, String fechaYHora, String tipoOperacion){
        this.id = id;
        this.fechaYHora = fechaYHora;
        this.tipoOperacion = tipoOperacion;
    }

    public Operacion()
    {;}

    public String getId() {
        return id;
    }

    public String getFechaYHora() {
        return fechaYHora;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFechaYHora(String fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }
}
