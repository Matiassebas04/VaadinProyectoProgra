package com.example.application.views.cliente;

import com.example.application.metodos.Cliente;
import com.example.application.services.ClienteService;
import com.example.application.views.MainLayout;
import com.example.application.views.lista.ListaView;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.component.notification.Notification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@PageTitle("Cliente")
@Route(value = "cliente", layout = MainLayout.class)
@Uses(Icon.class)
@Component
public class ClienteView extends Composite<VerticalLayout> {
    private ClienteService clienteService;
    private final List<Cliente> listaClientes = new ArrayList<>();

    public ClienteView(ClienteService clienteService) {
        this.clienteService = clienteService;

        HorizontalLayout layoutRow = new HorizontalLayout();
        H1 h1 = new H1();
        TextField tnclient = new TextField();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        FormLayout formLayout2Col = new FormLayout();
        TextField tnumced = new TextField();
        TextField tipVeh = new TextField();
        FormLayout formLayout2Col2 = new FormLayout();
        TextField placVeh = new TextField();
        TextField textField4 = new TextField();
        FormLayout formLayout3Col = new FormLayout();
        Button botonAceptarCliente = new Button();
        Button botonCancelar = new Button();
        botonCancelar.addClickListener(event -> {
            UI.getCurrent().navigate(ListaView.class);
        });
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        h1.setText("Ingrese los datos del cliente");
        h1.setWidth("max-content");
        tnclient.setLabel("Nombre");
        tnclient.setWidth("min-content");
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        formLayout2Col.setWidth("100%");
        tnumced.setLabel("Número de Cédula");
        tnumced.setWidth("min-content");
        tipVeh.setLabel("Tipo de vehículo");
        tipVeh.setWidth("min-content");
        //setComboBoxSampleData(tipVeh);
        formLayout2Col2.setWidth("100%");
        placVeh.setLabel("Placa del vehículo");
        placVeh.setWidth("min-content");
        textField4.setLabel("Tiempo Estacionado(Hora o Fracción)");
        textField4.setWidth("min-content");
        formLayout3Col.setWidth("100%");
        formLayout3Col.setResponsiveSteps(new ResponsiveStep("0", 1), new ResponsiveStep("250px", 2),
                new ResponsiveStep("500px", 3));

        botonAceptarCliente.setText("Aceptar");
        botonAceptarCliente.setWidth("min-content");
        botonAceptarCliente.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        botonAceptarCliente.addClickListener(event -> {
            String cedulaCliente = tnumced.getValue();
            String nombreCliente = tnclient.getValue();
            String tipoAuto = (String) tipVeh.getValue();
            String placaAuto = placVeh.getValue();
            String horasEstadiaStr = textField4.getValue();

            if (cedulaCliente != null && !cedulaCliente.isEmpty() &&
                    nombreCliente != null && !nombreCliente.isEmpty() &&
                    tipoAuto != null && !tipoAuto.isEmpty() &&
                    placaAuto != null && !placaAuto.isEmpty() &&
                    horasEstadiaStr != null && !horasEstadiaStr.isEmpty()) {

                try {
                    int horasEstadia = Integer.parseInt(horasEstadiaStr);

                    Cliente cliente1 = new Cliente(nombreCliente, cedulaCliente);
                    cliente1.setTipoVehiculo(tipoAuto);
                    cliente1.setPlacaVehiculo(placaAuto);
                    cliente1.setHorasEstadia(horasEstadia);

                    clienteService.guardarClientes(cliente1);

                    UI.getCurrent().navigate(ListaView.class);
                } catch (NumberFormatException e) {
                    Notification.show("Por favor, ingrese un valor válido para las horas de estadía.",
                            3000, Notification.Position.TOP_CENTER);
                }
            } else {
                Notification.show("Por favor, complete todos los campos.",
                        3000, Notification.Position.TOP_CENTER);
            }
        });

        botonCancelar.setText("Cancelar");
        botonCancelar.setWidth("min-content");
        getContent().add(layoutRow);
        layoutRow.add(h1);
        getContent().add(tnclient);
        getContent().add(layoutColumn2);
        layoutColumn2.add(formLayout2Col);
        formLayout2Col.add(tnumced);
        formLayout2Col.add(tipVeh);
        formLayout2Col.add(formLayout2Col2);
        formLayout2Col2.add(placVeh);
        formLayout2Col.add(textField4);
        layoutColumn2.add(formLayout3Col);
        formLayout3Col.add(botonAceptarCliente);
        formLayout3Col.add(botonCancelar);
    }
    public List<Cliente> getListaClientes() {
        return listaClientes;
    }
    public void guardarCliente(Cliente cliente1) {
        try {
            clienteService.guardarClientes(cliente1);
            listaClientes.add(cliente1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    record SampleItem(String value, String label, Boolean disabled) {
    }
}
