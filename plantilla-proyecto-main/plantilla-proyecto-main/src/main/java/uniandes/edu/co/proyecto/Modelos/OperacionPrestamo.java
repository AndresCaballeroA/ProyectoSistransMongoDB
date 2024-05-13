package uniandes.edu.co.proyecto.Modelos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "OperacionPrestamo")
public class OperacionPrestamo {

    @Id
    private Integer id;

    private int numPrestamo;

    private int montoPago;

    private Operacion id_operacion;

    public OperacionPrestamo(Integer id, int numPrestamo, int montoPago, Operacion id_operacion) {
        this.id = id;
        this.numPrestamo = numPrestamo;
        this.montoPago = montoPago;
        this.id_operacion = id_operacion;
    }

    public OperacionPrestamo() {
        ;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumPrestamo() {
        return numPrestamo;
    }

    public void setNumPrestamo(int numPrestamo) {
        this.numPrestamo = numPrestamo;
    }

    public int getMontoPago() {
        return montoPago;
    }

    public void setMontoPago(int montoPago) {
        this.montoPago = montoPago;
    }

    public Operacion getId_operacion() {
        return id_operacion;
    }

    public void setId_operacion(Operacion id_operacion) {
        this.id_operacion = id_operacion;
    }
}
