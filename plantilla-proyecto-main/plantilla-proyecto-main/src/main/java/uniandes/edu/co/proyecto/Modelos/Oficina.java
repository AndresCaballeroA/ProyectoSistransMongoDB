package uniandes.edu.co.proyecto.Modelos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Oficina")
public class Oficina {

    @Id
    private String id;

    private String nombre;

    private String direccion;

    private Integer numPuntosPosibles;

    private Empleado gerente;

    public Oficina(String id, String nombre, String direccion, Integer numPuntosPosibles, Empleado gerente) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.numPuntosPosibles = numPuntosPosibles;
        this.gerente = gerente;
    }

    public Oficina() {
        ;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getNumPuntosPosibles() {
        return numPuntosPosibles;
    }

    public void setNumPuntosPosibles(Integer numPuntosPosibles) {
        this.numPuntosPosibles = numPuntosPosibles;
    }

    public Empleado getGerente() {
        return gerente;
    }

    public void setGerente(Empleado gerente) {
        this.gerente = gerente;
    }
}
