package com.example.application.metodos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "cliente")
public class Cliente implements Serializable {

    @Id
    private String id;

    @Indexed(name = "cedulaCliente", unique = true)
    private String cedulaCliente;
    private String nombreCliente;
    private String tipoVehiculo;
    private String placaVehiculo;
    private int horasEstadia;
    private double valorAPagar;

    public Cliente() {
        // Constructor sin argumentos requerido por Spring Data MongoDB
    }

    public Cliente(String nombreCliente, String cedulaCliente) {
        this.nombreCliente = nombreCliente;
        this.cedulaCliente = cedulaCliente;
    }

    public Cliente(String nombreCliente, int cedulaCliente) {
    }
    public double getValorAPagar() {
        return valorAPagar;
    }
    public void setValorAPagar(double valorAPagar) {
        this.valorAPagar = valorAPagar;
    }


    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(String cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    private static final long serialVersionUID = 1L; // Agrega esto para la serialización
    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public int getHorasEstadia() {
        return horasEstadia;
    }

    public void setHorasEstadia(int horasEstadia) {
        this.horasEstadia = horasEstadia;
    }
}
