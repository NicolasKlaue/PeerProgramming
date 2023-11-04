package com.example.application.views.main;

public class Employee {
     private String employeeType;
     private int monthlySales = 0;
     private float extraHours = 0;

     Employee(String c)
     {
          this.employeeType = c;
     }
     
     public void setEmployeeType(String type) {
          this.employeeType = type;
     }
     public void setMonthlySales(int monthlySales) {
          this.monthlySales = monthlySales;
     }
     public void setExtraHours(float extraHours) {
          this.extraHours = extraHours;
     }
     public String getEmployeeType() {
          return employeeType;
     }
     public int getMonthlySales() {
          return monthlySales;
     }
     public float getExtraHours() {
          return extraHours;
     }
     public Employee(String type, int monthlySales, float extraHours) {
          this.employeeType = type;
          this.monthlySales = monthlySales;
          this.extraHours = extraHours;
     }
   
          
     
    
     
     
}
