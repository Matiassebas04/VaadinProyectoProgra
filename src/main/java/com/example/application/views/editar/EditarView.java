package com.example.application.views.editar;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
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

@PageTitle("Editar")
@Route(value = "editar", layout = MainLayout.class)
@Uses(Icon.class)
public class EditarView extends Composite<VerticalLayout> {

    public EditarView() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        H1 h1 = new H1();
        TextField textField = new TextField();
        TextField textField2 = new TextField();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        VerticalLayout layoutColumn3 = new VerticalLayout();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        FormLayout formLayout2Col = new FormLayout();
        TextField textField3 = new TextField();
        ComboBox comboBox = new ComboBox();
        TextField textField4 = new TextField();
        TextField textField5 = new TextField();
        HorizontalLayout layoutRow3 = new HorizontalLayout();
        Button buttonPrimary = new Button();
        Button buttonSecondary = new Button();
        HorizontalLayout layoutRow4 = new HorizontalLayout();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        h1.setText("Editar Cliente");
        h1.setWidth("max-content");
        textField.setLabel("Ingrese el número de Cédula");
        textField.setWidth("200px");
        textField2.setLabel("Ingrese el Nombre del cliente");
        textField2.setWidth("300px");
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        layoutColumn3.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutColumn3);
        layoutColumn3.setWidth("100%");
        layoutColumn3.getStyle().set("flex-grow", "1");
        layoutRow2.setWidthFull();
        layoutColumn3.setFlexGrow(1.0, layoutRow2);
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setWidth("100%");
        layoutRow2.getStyle().set("flex-grow", "1");
        formLayout2Col.setWidth("100%");
        textField3.setLabel("Nuevo numero de Cedula");
        textField3.setWidth("min-content");
        comboBox.setLabel("Tipo del vehiculo");
        comboBox.setWidth("min-content");
        setComboBoxSampleData(comboBox);
        textField4.setLabel("Placa del vehiculo");
        textField4.setWidth("min-content");
        textField5.setLabel("Tiempo Estacionado(Hora o Fraccion)");
        textField5.setWidth("min-content");
        layoutRow3.setWidthFull();
        layoutColumn3.setFlexGrow(1.0, layoutRow3);
        layoutRow3.addClassName(Gap.MEDIUM);
        layoutRow3.setWidth("100%");
        layoutRow3.getStyle().set("flex-grow", "1");
        buttonPrimary.setText("Aceptar");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonSecondary.setText("Cancelar");
        buttonSecondary.setWidth("min-content");
        layoutRow4.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutRow4);
        layoutRow4.addClassName(Gap.MEDIUM);
        layoutRow4.setWidth("100%");
        layoutRow4.getStyle().set("flex-grow", "1");
        getContent().add(layoutRow);
        layoutRow.add(h1);
        getContent().add(textField);
        getContent().add(textField2);
        getContent().add(layoutColumn2);
        layoutColumn2.add(layoutColumn3);
        layoutColumn3.add(layoutRow2);
        layoutRow2.add(formLayout2Col);
        formLayout2Col.add(textField3);
        formLayout2Col.add(comboBox);
        formLayout2Col.add(textField4);
        formLayout2Col.add(textField5);
        layoutColumn3.add(layoutRow3);
        layoutRow3.add(buttonPrimary);
        layoutRow3.add(buttonSecondary);
        layoutColumn2.add(layoutRow4);
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
