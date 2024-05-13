package uniandes.edu.co.proyecto.Modelos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "OperacionCuenta")
public class OperacionCuenta {

    @Id
    private String id;

    private Integer numeroOrigen;

    private Integer numeroDestino;

    private Integer monto;

    private Operacion id_operacion;

    public OperacionCuenta(String id, Integer numeroOrigen, Integer numeroDestino, Integer monto, Operacion id_operacion) {
        this.id = id;
        this.numeroOrigen = numeroOrigen;
        this.numeroDestino = numeroDestino;
        this.monto = monto;
        this.id_operacion = id_operacion;
    }

    public OperacionCuenta() {
        ;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNumeroOrigen() {
        return numeroOrigen;
    }

    public void setNumeroOrigen(Integer numeroOrigen) {
        this.numeroOrigen = numeroOrigen;
    }

    public Integer getNumeroDestino() {
        return numeroDestino;
    }

    public void setNumeroDestino(Integer numeroDestino) {
        this.numeroDestino = numeroDestino;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public Operacion getId_operacion() {
        return id_operacion;
    }

    public void setId_operacion(Operacion id_operacion) {
        this.id_operacion = id_operacion;
    }
}
