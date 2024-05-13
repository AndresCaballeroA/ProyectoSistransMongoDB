package uniandes.edu.co.proyecto.Modelos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "PuntoDeAtencion")
public class PuntoDeAtencion {
    
    @Id
    private String id;
    
    private String tipo;

    private Oficina oficina;

    public PuntoDeAtencion(String id, String tipo, Oficina oficina){
        this.id = id;
        this.tipo = tipo;
        this.oficina = oficina;
    }

    public PuntoDeAtencion()
    {;}

    public String getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Oficina getOficina() {
        return oficina;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }
}
