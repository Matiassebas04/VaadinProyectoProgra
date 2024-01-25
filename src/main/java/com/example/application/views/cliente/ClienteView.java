package com.example.application.views.cliente;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Composite;
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
import java.util.ArrayList;
import java.util.List;

@PageTitle("Cliente")
@Route(value = "cliente", layout = MainLayout.class)
@Uses(Icon.class)
public class ClienteView extends Composite<VerticalLayout> {

    public ClienteView() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        H1 h1 = new H1();
        TextField textField = new TextField();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        FormLayout formLayout2Col = new FormLayout();
        TextField textField2 = new TextField();
        ComboBox comboBox = new ComboBox();
        FormLayout formLayout2Col2 = new FormLayout();
        TextField textField3 = new TextField();
        TextField textField4 = new TextField();
        FormLayout formLayout3Col = new FormLayout();
        Button buttonPrimary = new Button();
        Button buttonSecondary = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        h1.setText("Ingrese los datos del cliente");
        h1.setWidth("max-content");
        textField.setLabel("Nombre");
        textField.setWidth("min-content");
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        formLayout2Col.setWidth("100%");
        textField2.setLabel("Número de Cédula");
        textField2.setWidth("min-content");
        comboBox.setLabel("Tipo de vehículo");
        comboBox.setWidth("min-content");
        setComboBoxSampleData(comboBox);
        formLayout2Col2.setWidth("100%");
        textField3.setLabel("Placa del vehículo");
        textField3.setWidth("min-content");
        textField4.setLabel("Tiempo Estacionado(Hora o Fracción)");
        textField4.setWidth("min-content");
        formLayout3Col.setWidth("100%");
        formLayout3Col.setResponsiveSteps(new ResponsiveStep("0", 1), new ResponsiveStep("250px", 2),
                new ResponsiveStep("500px", 3));
        buttonPrimary.setText("Aceptar");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonSecondary.setText("Cancelar");
        buttonSecondary.setWidth("min-content");
        getContent().add(layoutRow);
        layoutRow.add(h1);
        getContent().add(textField);
        getContent().add(layoutColumn2);
        layoutColumn2.add(formLayout2Col);
        formLayout2Col.add(textField2);
        formLayout2Col.add(comboBox);
        formLayout2Col.add(formLayout2Col2);
        formLayout2Col2.add(textField3);
        formLayout2Col.add(textField4);
        layoutColumn2.add(formLayout3Col);
        formLayout3Col.add(buttonPrimary);
        formLayout3Col.add(buttonSecondary);
    }

    record SampleItem(String value, String label, Boolean disabled) {
    }

    private void setComboBoxSampleData(ComboBox comboBox) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem("first", "First", null));
        sampleItems.add(new SampleItem("second", "Second", null));
        sampleItems.add(new SampleItem("third", "Third", Boolean.TRUE));
        sampleItems.add(new SampleItem("fourth", "Fourth", null));
        comboBox.setItems(sampleItems);
        comboBox.setItemLabelGenerator(item -> ((SampleItem) item).label());
    }
}
