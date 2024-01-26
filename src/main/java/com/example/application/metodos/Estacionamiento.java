package com.example.application.metodos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Estacionamiento implements Serializable {
    private ArrayList<Vehiculo> vehiculosEstacionados;
    private ArrayList<Cliente> clientes;
    private HashMap<Vehiculo, Integer> tiempoEstadia; // Cambiado a HashMap<Vehiculo, Integer>

    public Estacionamiento() {
        vehiculosEstacionados = new ArrayList<>();
        clientes = new ArrayList<>();
        tiempoEstadia = new HashMap<>();
    }
    public void ingresarVehiculo(Cliente cliente, Vehiculo vehiculo, int tiempoEstadia) {
        clientes.add(cliente);
        vehiculosEstacionados.add(vehiculo);

        // Almacena el tiempo de estadía asociado al vehículo
        this.tiempoEstadia.put(vehiculo, tiempoEstadia);
    }


    public double calcularCosto(Vehiculo vehiculo, int tiempoHoras) {
        if (vehiculo != null) {
            double costoPorHora = (vehiculo.getTipoAuto().equalsIgnoreCase("automovil")) ? 1.0 : 0.5;
            return costoPorHora * tiempoHoras;
        } else {
            System.out.println("Error: El vehículo es nulo.");
            return 0.0;
        }
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void setVehiculosEstacionados(ArrayList<Vehiculo> vehiculosEstacionados) {
        this.vehiculosEstacionados = vehiculosEstacionados;
    }

    public void setTiempoEstadia(HashMap<Vehiculo, Integer> tiempoEstadia) {
        this.tiempoEstadia.clear();
        this.tiempoEstadia.putAll(tiempoEstadia);
    }

    public ArrayList<Vehiculo> getVehiculosEstacionados() {
        return vehiculosEstacionados;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public HashMap<Vehiculo, Integer> getTiempoEstadia() {
        return tiempoEstadia;
    }
}
