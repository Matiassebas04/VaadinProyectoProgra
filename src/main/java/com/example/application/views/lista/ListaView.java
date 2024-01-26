package com.example.application.views.lista;

import com.example.application.metodos.Cliente;
import com.example.application.services.ClienteService;
import com.example.application.views.MainLayout;
import com.example.application.views.cliente.ClienteView;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.component.notification.Notification;
import java.util.List;

@PageTitle("Lista")
@Route(value = "lista", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@Uses(Icon.class)
public class ListaView extends Composite<VerticalLayout> {
    private final ClienteView clienteView;
    private final ClienteService clienteService;
    private final Grid<Cliente> gridClientes;

    public ListaView(ClienteView clienteView, ClienteService clienteService) {
        this.clienteView = clienteView;
        this.clienteService = clienteService;
        this.gridClientes = new Grid<>(Cliente.class);

        H1 h1 = new H1();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        HorizontalLayout layoutRow = new HorizontalLayout();
        Button botonCN = new Button();
        botonCN.addClickListener(event -> {
            UI.getCurrent().navigate(ClienteView.class);
        });

        Button buttonSecondary = new Button();

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");

        h1.setText("Vehículos Estacionados");
        h1.setWidth("max-content");

        layoutColumn2.setWidthFull();
        getContent().setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");

        gridClientes.addThemeVariants(GridVariant.LUMO_COMPACT, GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS);
        gridClientes.setWidth("100%");
        gridClientes.getStyle().set("flex-grow", "0");

        layoutRow.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");

        botonCN.setText("Nuevo Cliente");
        botonCN.setWidth("min-content");
        botonCN.addThemeVariants(ButtonVariant.LUMO_PRIMARY);


        buttonSecondary.setText("Mostrar Factura");
        buttonSecondary.setWidth("min-content");
        buttonSecondary.addClickListener(event -> {
            // Obtiene el cliente seleccionado de la grid
            Cliente clienteSeleccionado = gridClientes.asSingleSelect().getValue();
            if (clienteSeleccionado != null) {
                generarFactura(clienteSeleccionado);
            } else {
                Notification.show("Selecciona un cliente antes de generar la factura.",
                        3000, Notification.Position.TOP_CENTER);
            }
        });

        getContent().add(h1);
        getContent().add(layoutColumn2);
        layoutColumn2.add(gridClientes);  // Agregar la grid a la disposición
        layoutColumn2.add(layoutRow);
        layoutRow.add(botonCN);
        layoutRow.add(buttonSecondary);

        // Configurar la grid de clientes
        gridClientes.setColumns("nombreCliente", "cedulaCliente", "tipoVehiculo", "placaVehiculo", "horasEstadia");

        // Obtener la lista de clientes del servicio
        List<Cliente> listaClientes = clienteService.obtenerTodosLosClientes();

        gridClientes.addComponentColumn(cliente -> {
            Button deleteButton = new Button(new Icon(VaadinIcon.TRASH));
            deleteButton.addClickListener(event -> {
                // Remover la credencial del Grid
                listaClientes.remove(cliente);
                //Utils.Cred.remove(credencial);
                gridClientes.setItems(listaClientes);
                //grid.setItems(Utils.Cred);
                clienteService.eliminarCliente(cliente);
            });
            return deleteButton;
        });

        // Establecer los datos en la grid
        gridClientes.setItems(listaClientes);
    }

    private void generarFactura(Cliente cliente) {
        // Llama al servicio para calcular el valor a pagar
        clienteService.calcularValorAPagar(cliente);

        // Crea un diseño vertical para mostrar la factura
        VerticalLayout facturaLayout = new VerticalLayout();

        // Añade los detalles de la factura
        facturaLayout.add(new H3(   "Factura para " + cliente.getNombreCliente()));
        facturaLayout.add(new Paragraph("Horas estacionadas: " + cliente.getHorasEstadia()));
        facturaLayout.add(new Paragraph("Valor a pagar: $" + cliente.getValorAPagar()));

        // Crea un diálogo y configúralo para mostrar la factura
        Dialog facturaDialog = new Dialog();
        facturaDialog.add(facturaLayout);

        // Añade un botón para cerrar el diálogo
        Button cerrarButton = new Button("Cerrar", event -> facturaDialog.close());
        facturaLayout.add(cerrarButton);

        // Abre el diálogo con la factura
        facturaDialog.open();

        Notification.show("Factura generada para " + cliente.getNombreCliente() +
                        " - Valor a pagar: $" + cliente.getValorAPagar(),
                3000, Notification.Position.TOP_CENTER);
    }
}
