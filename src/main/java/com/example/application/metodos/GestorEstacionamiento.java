
package com.example.application.metodos;

        import java.io.*;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.Scanner;

public class GestorEstacionamiento {
    private Estacionamiento estacionamiento;
    private ArrayList<Vehiculo> vehiculosEstacionados;
    private int tiempoHoras;
    private String cedulaCliente;

    // Añade este campo para almacenar el tiempo de estadía de cada vehículo
    private HashMap<Vehiculo, Integer> tiempoEstadia;

    public GestorEstacionamiento() {
        estacionamiento = new Estacionamiento();
        vehiculosEstacionados = new ArrayList<>();
        tiempoHoras = 0;
        tiempoEstadia = new HashMap<>();

        cargarDatos();  // Cargar datos almacenados en la ejecución anterior
    }

    public void ejecutar() {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            mostrarMenu();

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    ingresarCliente(scanner);
                    break;
                case 2:
                    editarCliente(scanner);
                    break;
                case 3:
                    eliminarCliente(scanner);
                    break;
                case 4:
                    mostrarFactura(scanner);
                    break;
                case 5:
                    verClientesRegistrados();
                    break;
                case 6:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
            }
        }

        guardarDatos();  // Se guarda al salir del programa
        scanner.close();
    }

    private void mostrarFactura(Scanner scanner) {
        System.out.print("Ingrese el número de cédula del cliente para mostrar la factura: ");
        String cedulaCliente = String.valueOf(scanner.nextInt());
        scanner.nextLine(); // Consumir el salto de línea

        Cliente cliente = buscarClientePorCedula(cedulaCliente);
        Vehiculo vehiculo = buscarVehiculoPorCliente(cliente);

        if (cliente != null) {

            if (vehiculo != null) {
                HashMap<Vehiculo, Integer> tiempoEstadia = estacionamiento.getTiempoEstadia();
                Integer tiempoVehiculo = tiempoEstadia.get(vehiculo);

                if (tiempoVehiculo != null) {
                    double costo = estacionamiento.calcularCosto(vehiculo, tiempoVehiculo);

                    System.out.println("Factura:");
                    System.out.println("Cliente: " + cliente.getNombreCliente() + " - Cédula: " + cliente.getCedulaCliente());
                    System.out.println("Vehículo: " + vehiculo.getTipoAuto() + " - Placa: " + vehiculo.getPlacaAuto());
                    System.out.println("Tiempo de estadía: " + tiempoVehiculo + " horas");
                    System.out.println("Costo: $" + costo);

                    boolean subMenu = true;
                    while (subMenu) {
                        System.out.println("\nSubmenú:");
                        System.out.println("1. Guardar en archivo de texto");
                        System.out.println("2. Mostrar factura");
                        System.out.println("3. Salir al menú principal");
                        System.out.print("Seleccione una opción: ");

                        int subOpcion = scanner.nextInt();
                        scanner.nextLine(); // Consumir el salto de línea

                        switch (subOpcion) {
                            case 1:
                                guardarFacturaEnArchivo(cliente, vehiculo, costo);
                                break;
                            case 2:
                                mostrarFacturaDetallada(cliente, vehiculo, tiempoVehiculo, costo);
                                break;
                            case 3:
                                subMenu = false;
                                break;
                            default:
                                System.out.println("Opción no válida. Por favor, ingrese una opción válida.");
                        }
                    }
                } else {
                    System.out.println("Error: Tiempo de estadía no encontrado para este vehículo.");
                }
            } else {
                System.out.println("Error: Vehículo no encontrado para este cliente.");
            }
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private void verClientesRegistrados() {
        System.out.println("\nClientes Registrados:");
        for (Cliente cliente : estacionamiento.getClientes()) {
            System.out.println("Cliente: " + cliente.getNombreCliente() + " - Cédula: " + cliente.getCedulaCliente());
        }
    }

    private Vehiculo buscarVehiculoPorCliente(Cliente cliente) {
        for (Vehiculo vehiculo : estacionamiento.getVehiculosEstacionados()) {
            Integer tiempoVehiculo = estacionamiento.getTiempoEstadia().get(vehiculo);

            //if (tiempoVehiculo != null && cliente.getCedulaCliente() == tiempoVehiculo) {
            //  return vehiculo;
            //}
        }
        System.out.println("Error: Vehículo no encontrado para este cliente.");
        return null;
    }

    private void mostrarFacturaDetallada(Cliente cliente, Vehiculo vehiculo, int tiempoVehiculo, double costo) {
        System.out.println("Detalles de la Factura:");
        System.out.println("Cliente: " + cliente.getNombreCliente() + " - Cédula: " + cliente.getCedulaCliente());
        System.out.println("Vehículo: " + vehiculo.getTipoAuto() + " - Placa: " + vehiculo.getPlacaAuto());
        System.out.println("Tiempo de estadía: " + tiempoVehiculo + " horas");
        System.out.println("Costo: $" + costo);
    }

    private void guardarFacturaEnArchivo(Cliente cliente, Vehiculo vehiculo, double costo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("factura.txt", true))) {
            writer.println("Factura:");
            writer.println("Cliente: " + cliente.getNombreCliente() + " - Cédula: " + cliente.getCedulaCliente());
            writer.println("Vehículo: " + vehiculo.getTipoAuto() + " - Placa: " + vehiculo.getPlacaAuto());
            writer.println("Costo: $" + costo);
            writer.println("--------");
            System.out.println("Factura guardada en archivo.");
        } catch (IOException e) {
            System.out.println("Error al guardar la factura en archivo.");
            e.printStackTrace();
        }
    }

    private void cargarDatos() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("factura.txt"))) {
            // Cargar la información almacenada en el archivo
            estacionamiento.setClientes((ArrayList<Cliente>) inputStream.readObject());
            estacionamiento.setVehiculosEstacionados((ArrayList<Vehiculo>) inputStream.readObject());
            tiempoEstadia = (HashMap<Vehiculo, Integer>) inputStream.readObject();

            System.out.println("Datos cargados exitosamente.");
        } catch (IOException | ClassNotFoundException e) {
            // Ignorar errores de carga, puede ser la primera vez que se ejecuta el programa
        }
    }


    private void guardarDatos() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("factura.txt"))) {
            // Guardar todos los datos al salir del programa
            outputStream.writeObject(estacionamiento.getClientes());
            outputStream.writeObject(vehiculosEstacionados);
            outputStream.writeInt(tiempoHoras);

            System.out.println("Datos guardados exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar los datos.");
            e.printStackTrace();
        }
    }

    private void mostrarMenu() {
        System.out.println("Menú de opciones:");
        System.out.println("1. Ingresar Nuevo Cliente");
        System.out.println("2. Editar Cliente");
        System.out.println("3. Eliminar Cliente");
        System.out.println("4. Mostrar Factura");
        System.out.println("5. Ver Clientes Registrados");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private void ingresarCliente(Scanner scanner) {
        System.out.print("Ingrese el nombre del cliente (nombre y apellido): ");
        String nombreCliente = scanner.nextLine();

        System.out.print("Ingrese el número de cédula del cliente: ");
        String cedulaCliente = scanner.nextLine();
        scanner.nextLine();

        // Verifica si el cliente ya existe
        Cliente clienteExistente = buscarClientePorCedula(cedulaCliente);

        if (clienteExistente != null) {
            // Si el cliente ya existe, actualiza la información
            System.out.println("Cliente ya existe. Actualizando información.");
            clienteExistente.setNombreCliente(nombreCliente);
        } else {
            // Si el cliente no existe, crea uno nuevo y agrégalo a la lista
            Cliente nuevoCliente = new Cliente(nombreCliente, cedulaCliente);
            estacionamiento.getClientes().add(nuevoCliente);
        }

        System.out.print("Ingrese el tipo de vehículo (automóvil o motocicleta): ");
        String tipoAuto = scanner.next();
        System.out.print("Ingrese la placa del vehículo: ");
        String placaAuto = scanner.next();

        System.out.print("Ingrese el tiempo en horas que se quedará el vehículo en el estacionamiento: ");
        int tiempoHoras = scanner.nextInt();
        scanner.nextLine();

        Cliente cliente = new Cliente(nombreCliente, cedulaCliente);
        estacionamiento.getClientes().add(cliente);

        System.out.println("Cliente ingresado exitosamente.");
    }

    private void editarCliente(Scanner scanner) {
        System.out.print("Ingrese el número de cédula del cliente que desea editar: ");
        String cedulaEditar = String.valueOf(scanner.nextInt());
        scanner.nextLine();

        Cliente clienteEditar = buscarClientePorCedula(cedulaEditar);

        if (clienteEditar != null) {
            System.out.println("Cliente encontrado. Ingrese los nuevos datos:");

            // Editar nombre
            System.out.print("Nuevo nombre del cliente (nombre y apellido): ");
            String nuevoNombre = scanner.nextLine();
            clienteEditar.setNombreCliente(nuevoNombre);

            // Editar tipo de vehículo
            System.out.print("Nuevo tipo de vehículo (automóvil o motocicleta): ");
            String nuevoTipoAuto = scanner.next();

            // Editar placa
            System.out.print("Nueva placa del vehículo: ");
            String nuevaPlacaAuto = scanner.next();

            // Editar tiempo
            System.out.print("Nuevo tiempo en horas que se quedará el vehículo en el estacionamiento: ");
            int nuevoTiempoHoras = scanner.nextInt();
            scanner.nextLine();

            // Buscar el vehículo asociado a este cliente
            Vehiculo vehiculo = buscarVehiculoPorCliente(clienteEditar);

            if (vehiculo != null) {
                // Actualizar información del vehículo
                vehiculo.setTipoAuto(nuevoTipoAuto);
                vehiculo.setPlacaAuto(nuevaPlacaAuto);

                // Actualizar tiempo de estadía asociado al vehículo
                tiempoEstadia.put(vehiculo, nuevoTiempoHoras);
            } else {
                System.out.println("Vehículo no encontrado para este cliente.");
            }

            System.out.println("Cliente editado exitosamente.");
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private void eliminarCliente(Scanner scanner) {
        System.out.print("Ingrese el número de cédula del cliente que desea eliminar: ");
        String cedulaEliminar = String.valueOf(scanner.nextInt());
        scanner.nextLine();

        Cliente clienteEliminar = buscarClientePorCedula(cedulaEliminar);

        if (clienteEliminar != null) {
            System.out.println("Cliente encontrado:");
            System.out.println("Cliente: " + clienteEliminar.getNombreCliente() + " - Cédula: " + clienteEliminar.getCedulaCliente());

            System.out.print("¿Está seguro de que desea eliminar este cliente? (S/N): ");
            String confirmacion = scanner.next();
            scanner.nextLine(); // Consumir el salto de línea

            if (confirmacion.equalsIgnoreCase("S")) {
                // Buscar el vehículo asociado a este cliente
                Vehiculo vehiculo = buscarVehiculoPorCliente(clienteEliminar);

                if (vehiculo != null) {
                    // Remover el vehículo y su tiempo de estadía
                    estacionamiento.getVehiculosEstacionados().remove(vehiculo);
                    tiempoEstadia.remove(vehiculo);
                }

                // Remover el cliente de la lista de clientes de Estacionamiento
                estacionamiento.getClientes().remove(clienteEliminar);

                System.out.println("Cliente eliminado exitosamente.");
            } else {
                System.out.println("Operación de eliminación cancelada.");
            }
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private Cliente buscarClientePorCedula(String cedula) {
        for (Cliente cliente : estacionamiento.getClientes()) {
            if (cliente.getCedulaCliente() == cedula) {
                return cliente;
            } else {
                System.out.println("Cliente no encontrado.");
            }
        }
        return null;
    }
}