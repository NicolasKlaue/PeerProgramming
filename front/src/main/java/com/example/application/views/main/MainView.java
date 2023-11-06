package com.example.application.views.main;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.Data;

import com.nimbusds.jose.shaded.gson.Gson;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.model.Label;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
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
     private Label l = new Label();
     
     TextField readonlyField = new TextField();
     
     public ArrayList<Employee> employees = new ArrayList<>();
   

  

     ///////////////////////////////////////////////// MAIN/////////////////////////////////////////////////
     public MainView() {
          // Horizontal Layout Setup
          HorizontalLayout hl = new HorizontalLayout( type_employee1);
          hl.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
          HorizontalLayout hl2 = new HorizontalLayout( type_employee2);
          hl2.setDefaultVerticalComponentAlignment(Alignment.BASELINE);
          

          ArrayList<Employee> employees = new ArrayList<>();
          employees.add(new Employee("Manager"));
          employees.add(new Employee("Seller"));

          
          type_employee1.setItems(employees);
          type_employee1.setItemLabelGenerator(Employee::getEmployeeType);

          type_employee2.setItems(employees);
          type_employee2.setItemLabelGenerator(Employee::getEmployeeType);


          // Components
          sales1 = new TextField("# Montly sales");
          hours1 = new TextField("Extra hours");

          sales2 = new TextField("# Montly sales");
          hours2 = new TextField("Extra hours");

          Calculate1 = new Button("Calculate");
          Calculate2 = new Button("Calculate");
       
          Calculate1.addClickListener(e -> {
               readonlyField.clear();
               Employee type = type_employee1.getValue();
               String TotalSales = sales1.getValue();
               String TotalHours = hours1.getValue();

               Employee newEmployee = new Employee(type.getEmployeeType(), Integer.parseInt(TotalSales), Float.parseFloat(TotalHours));
               
               Notification.show("The " + newEmployee.getEmployeeType() + " With " + newEmployee.getMonthlySales() + " sales, and " + newEmployee.getExtraHours() + " extra hours was added successfully.");
               
               
               float FSalary = postHttpRequest1(newEmployee,false);
               //TextField readonlyField = new TextField();

               readonlyField.setReadOnly(true);
               readonlyField.setValue(String.valueOf(FSalary));
               l.setText("Final Salary is: " + String.valueOf(FSalary));
               hl.add(readonlyField);
               
          });
          Calculate2.addClickListener(e -> {
               
               readonlyField.clear();
               Employee type = type_employee2.getValue();
               String TotalSales = sales2.getValue();
               String TotalHours = hours2.getValue();
               
               Employee newEmployee = new Employee(type.getEmployeeType(), Integer.parseInt(TotalSales), Float.parseFloat(TotalHours));

               Notification.show("The " + newEmployee.getEmployeeType() + " With " + newEmployee.getMonthlySales() + " sales, and " + newEmployee.getExtraHours() + " extra hours was added successfully.");
               
     
               float FSalary = postHttpRequest2(newEmployee, true);

               //TextField readonlyField = new TextField();

               readonlyField.setReadOnly(true);
               readonlyField.setValue(String.valueOf(FSalary));
               //hl2.add("Final Salary is: " + String.valueOf(FSalary));
               l.setText("Final Salary is: " + String.valueOf(FSalary));
               hl2.add(readonlyField);
             

          });
          



          // ADD
         
          
          hl.add(sales1, hours1, Calculate1);
          hl2.add(sales2, hours2, Calculate2);

          tabsheet.add("Calculate pre-tax salary", hl);
          tabsheet.add("Calculate after-tax salary", hl2);

          add(tabsheet);
     }


     public static float postHttpRequest1(Employee newEmployee, boolean option){
          Gson gson = new Gson();
          String body = gson.toJson(newEmployee);

          HttpClient client = HttpClient.newHttpClient();

           HttpRequest request = HttpRequest.newBuilder()
               .uri(URI.create("http://localhost:8082/pretax"))
               .header("Content-Type", "application/json")
               .POST(HttpRequest.BodyPublishers.ofString(body))
               .build();

          try {
               System.out.println("Making reqquet");
               HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
               System.out.println(response.body());
               Employee e = gson.fromJson(response.body(), Employee.class);
               //System.out.println(e.getFinalSalary());
               
               return (e.getFinalSalary());
               
               
      }   catch (Exception e) {
          System.out.println("error");

               e.printStackTrace();
      }  
          return (0);
    }
    public static float postHttpRequest2(Employee newEmployee, boolean option){
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
               Employee e = gson.fromJson(response.body(), Employee.class);
               //System.out.println(e.getFinalSalary());
               
               return (e.getFinalSalary());
      }   catch (IOException e) {
               e.printStackTrace();
      }   catch (InterruptedException e) {
               e.printStackTrace();
      }
      return (0);
    }

}
