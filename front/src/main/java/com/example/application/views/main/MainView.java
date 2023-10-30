package com.example.application.views.main;
//#region Imports
import java.time.LocalDate;
import java.util.ArrayList;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.tabs.TabSheet;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
//#endregion

@PageTitle("Main")
@Route(value = "")
public class MainView extends VerticalLayout {

     private TextField name;
     private Button AddCountry;
     private ComboBox<Country> CountriesBox;
     private TabSheet tabsheet;
     private Dialog dialog = new Dialog();
     public ArrayList<Country> Countries = new ArrayList<>();
     private Grid<Country> grid = new Grid<>(Country.class);

     //#region DialogLayout
     public VerticalLayout createVerticalDialogLayout() {
          VerticalLayout verticalLayout = new VerticalLayout();
          TextField nameField = new TextField("Your name", "John");
          TextField SurnameField = new TextField("Your surname", "Doe");
          Checkbox checkbox1 = new Checkbox("Check me!");
          Checkbox checkbox2 = new Checkbox("Check me too!");
          RadioButtonGroup<String> radioButtons = new RadioButtonGroup<String>("Choose your gender:");
          ArrayList<String> Genders = new ArrayList<>();
          Genders.add("Male");
          Genders.add("Female");
          radioButtons.setItems(Genders);
          DatePicker datepicker = new DatePicker();
          datepicker.setValue(LocalDate.now());
          verticalLayout.add(nameField, SurnameField, checkbox1, checkbox2, radioButtons, datepicker);

          return verticalLayout;

     }
     //#endregion

     ///////////////////////////////////////////////// MAIN/////////////////////////////////////////////////
     public MainView() {
          // Horizontal Layout Setup
          HorizontalLayout hl = new HorizontalLayout();
          hl.setDefaultVerticalComponentAlignment(Alignment.BASELINE);

          // ArrayList Setup
          Countries.add(new Country("USA", "North america", 500000, "Washington"));
          Countries.add(new Country("Germany", "Europe", 500000, "Berlin"));
          Countries.add(new Country("Spain", "Europe", 500000, "Madrid"));

          // CountryBox
          this.CountriesBox = new ComboBox<Country>("Select country");
          CountriesBox.setItems(Countries);
          CountriesBox.setItemLabelGenerator(Country::getName);

          // Components
          name = new TextField("Your name");
          AddCountry = new Button("Add Country");
          AddCountry.addClickListener(e -> {
               Countries.add(new Country(name.getValue(), null, 0));
               Notification.show("Country of " + name.getValue() + " Added correctly", 1000,
                         com.vaadin.flow.component.notification.Notification.Position.TOP_END);
          });
          AddCountry.addClickShortcut(Key.ENTER);

          // Dialog
          dialog.setHeaderTitle("Create person");
          VerticalLayout dialogLayout = createVerticalDialogLayout();
          dialog.add(dialogLayout);
          Button dialogButton = new Button("Show Dialog", e -> dialog.open());

          // Tabsheet
          tabsheet = new TabSheet();
          VerticalLayout tabLayoutDashboard = new VerticalLayout();
          // Grid
          grid.setColumns("name", "continent");
          grid.addColumn(country -> country.getCity().getName())
                    .setHeader("City")
                    .setSortable(true);
          grid.addColumn(country -> country.getPopulation())
                    .setHeader("Population")
                    .setSortable(true);
          grid.setItems(Countries);

          // ADD
          tabLayoutDashboard.add(dialogButton);
          tabsheet.add("Dashboard", tabLayoutDashboard);
          tabsheet.add("Payment",
                    new Div(new Text("This is the Payment Tab")));
          
          hl.add(name, AddCountry);
          add(CountriesBox, hl, tabsheet, grid);
     }

}
