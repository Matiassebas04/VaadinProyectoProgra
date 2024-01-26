package com.example.application.services;

import com.example.application.metodos.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepositorio clienteRepositorio;

    @Autowired
    public ClienteService(ClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }

    public void guardarClientes(Cliente cliente) {
        try {
            clienteRepositorio.save(cliente);
        } catch (DataAccessException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error al guardar el cliente en la base de datos", ex);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error desconocido al guardar el cliente", ex);
        }
    }

    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepositorio.findAll();
    }
    public void eliminarCliente(Cliente cliente) {
        try {
            clienteRepositorio.delete(cliente);
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
    public void calcularValorAPagar(Cliente cliente) {
        double horasEstadia = cliente.getHorasEstadia();
        double tarifaHora;

        // Verifica el tipo de vehículo y establece la tarifa correspondiente
        if ("automovil".equalsIgnoreCase(cliente.getTipoVehiculo())) {
            tarifaHora = 1.0;  // Tarifa por hora para automóviles
        } else {
            tarifaHora = 0.5;  // Otra tarifa por hora para otros tipos de vehículos
        }

        // Calcula el valor a pagar
        double valorAPagar = horasEstadia * tarifaHora;

        // Actualiza el cliente con el valor a pagar calculado.
        cliente.setValorAPagar(valorAPagar);
    }
}
