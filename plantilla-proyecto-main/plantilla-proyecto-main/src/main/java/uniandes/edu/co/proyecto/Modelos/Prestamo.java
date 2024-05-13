package uniandes.edu.co.proyecto.Modelos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Prestamo")
public class Prestamo {

    @Id
    private Integer id;

    private String estado;

    private String tipo;

    private int monto;

    private float interes;

    private int numCuotas;

    private int numMes;

    private int valorCuota;

    private Cliente id_cliente;

    public Prestamo(Integer id, String estado, String tipo, int monto, float interes, int numCuotas,
            int numMes, int valorCuota, Cliente id_cliente) {
        this.id = id;
        this.estado = estado;
        this.tipo = tipo;
        this.monto = monto;
        this.interes = interes;
        this.numCuotas = numCuotas;
        this.numMes = numMes;
        this.valorCuota = valorCuota;
        this.id_cliente = id_cliente;
    }

    public Prestamo() {
        ;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public float getInteres() {
        return interes;
    }

    public void setInteres(float interes) {
        this.interes = interes;
    }

    public int getNumCuotas() {
        return numCuotas;
    }

    public void setNumCuotas(int numCuotas) {
        this.numCuotas = numCuotas;
    }

    public int getNumMes() {
        return numMes;
    }

    public void setNumMes(int numMes) {
        this.numMes = numMes;
    }

    public int getValorCuota() {
        return valorCuota;
    }

    public void setValorCuota(int valorCuota) {
        this.valorCuota = valorCuota;
    }

    public Cliente getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Cliente id_cliente) {
        this.id_cliente = id_cliente;
    }

}
