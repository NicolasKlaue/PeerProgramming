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

     private TextField sales;
     private TextField hours;
     private Button AddEmployee;
     private ComboBox<Employee> comboBox;
     private TabSheet tabsheet;

     public ArrayList<Employee> employees = new ArrayList<>();
     private Grid<Employee> grid = new Grid<>(Employee.class);

     //#region DialogLayout
     public VerticalLayout createVerticalDialogLayout() {
          VerticalLayout verticalLayout = new VerticalLayout();


          return verticalLayout;

     }
     //#endregion

     ///////////////////////////////////////////////// MAIN/////////////////////////////////////////////////
     public MainView() {
          // Horizontal Layout Setup
          HorizontalLayout hl = new HorizontalLayout();
          hl.setDefaultVerticalComponentAlignment(Alignment.BASELINE);

          // ArrayList Setup
          

          // ComboBox
          this.comboBox = new ComboBox<Employee>("Type of employee");
          comboBox.setItems();
          

          // Components
          sales = new TextField("# Montly sales");
          hours = new TextField("Extra hours");
          AddEmployee = new Button("Calculate");
          AddEmployee.addClickListener(e -> {
               
          });
          AddEmployee.addClickShortcut(Key.ENTER);


          // Tabsheet
          tabsheet = new TabSheet();
          VerticalLayout tabLayoutDashboard = new VerticalLayout();
          

          // ADD
         
          
          hl.add(sales, hours, AddEmployee);
          add(comboBox, hl);
     }

}
