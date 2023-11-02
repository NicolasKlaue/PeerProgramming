package com.example.application.views.main;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
//#region Imports
import java.time.LocalDate;
import java.util.ArrayList;

import com.nimbusds.jose.shaded.gson.Gson;
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

     private TextField sales1;
     private TextField hours1;
     private TextField sales2;
     private TextField hours2;
     private Button Calculate1;
     private Button Calculate2;
     private TabSheet tabsheet = new TabSheet();
     private ComboBox<Employee> type_employee1 = new ComboBox<>("Type of employee");
     private ComboBox<Employee> type_employee2 = new ComboBox<>("Type of employee");
     

     public ArrayList<Employee> employees = new ArrayList<>();
   

  

     ///////////////////////////////////////////////// MAIN/////////////////////////////////////////////////
     public MainView() {
          // Horizontal Layout Setup
          HorizontalLayout hl = new HorizontalLayout( type_employee1);
          hl.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
          HorizontalLayout hl2 = new HorizontalLayout( type_employee2);
          hl2.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
          

          ArrayList<Employee> employees = new ArrayList<>();
          employees.add(new Employee("manager"));
          employees.add(new Employee("seller"));

          
          type_employee1.setItems(employees);
          type_employee1.setItemLabelGenerator(Employee::getType);

          type_employee2.setItems(employees);
          type_employee2.setItemLabelGenerator(Employee::getType);


          // Components
          sales1 = new TextField("# Montly sales");
          hours1 = new TextField("Extra hours");

          sales2 = new TextField("# Montly sales");
          hours2 = new TextField("Extra hours");

          Calculate1 = new Button("Calculate");
          Calculate2 = new Button("Calculate");
       
          Calculate1.addClickListener(e -> {
               
               Employee type = type_employee1.getValue();
               String TotalSales = sales1.getValue();
               String TotalHours = hours1.getValue();

               Employee newEmployee = new Employee(type.getType(), Integer.parseInt(TotalSales), Float.parseFloat(TotalHours));

               Notification.show("The " + newEmployee.getType() + " With " + newEmployee.getMonthlySales() + " sales, and " + newEmployee.getExtraHours() + " extra hours was added successfully.");
               
     
               postHttpRequest1(newEmployee, false);
             

          });
          Calculate2.addClickListener(e -> {
               
               Employee type = type_employee2.getValue();
               String TotalSales = sales2.getValue();
               String TotalHours = hours2.getValue();

               Employee newEmployee = new Employee(type.getType(), Integer.parseInt(TotalSales), Float.parseFloat(TotalHours));

               Notification.show("The " + newEmployee.getType() + " With " + newEmployee.getMonthlySales() + " sales, and " + newEmployee.getExtraHours() + " extra hours was added successfully.");
               
     
               postHttpRequest2(newEmployee, true);
             

          });
          



          // ADD
         
          
          hl.add(sales1, hours1, Calculate1);
          hl2.add(sales2, hours2, Calculate2);

          tabsheet.add("Calculate pre-tax salary", hl);
          tabsheet.add("Calculate after-tax salary", hl2);

          add(tabsheet);
     }


     public static void postHttpRequest1(Employee newEmployee, boolean option){
          Gson gson = new Gson();
          String body = gson.toJson(newEmployee);

          HttpClient client = HttpClient.newHttpClient();

           HttpRequest request = HttpRequest.newBuilder()
               .uri(URI.create("http://localhost:8082/pretax"))
               .header("Content-Type", "application/json")
               .POST(HttpRequest.BodyPublishers.ofString(body))
               .build();

          try {
               HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
               System.out.println(response.body());
      }   catch (IOException e) {
               e.printStackTrace();
      }   catch (InterruptedException e) {
               e.printStackTrace();
      }
    }
    public static void postHttpRequest2(Employee newEmployee, boolean option){
          Gson gson = new Gson();
          String body = gson.toJson(newEmployee);
          
          
          HttpClient client = HttpClient.newHttpClient();
          
          HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create("http://localhost:8082/posttax"))
          .header("Content-Type", "application/json")
          .POST(HttpRequest.BodyPublishers.ofString(body))
          .build();
          
          try {
               HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
               System.out.println(response.body());
      }   catch (IOException e) {
               e.printStackTrace();
      }   catch (InterruptedException e) {
               e.printStackTrace();
      }
    }

}
