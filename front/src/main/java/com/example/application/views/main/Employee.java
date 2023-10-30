package com.example.application.views.main;

public class Employee {
     private String type;
     private int monthlySales = 0;
     private float extraHours = 0;
     
     public void setType(String type) {
          this.type = type;
     }
     public void setMonthlySales(int monthlySales) {
          this.monthlySales = monthlySales;
     }
     public void setExtraHours(float extraHours) {
          this.extraHours = extraHours;
     }
     public String getType() {
          return type;
     }
     public int getMonthlySales() {
          return monthlySales;
     }
     public float getExtraHours() {
          return extraHours;
     }
     public Employee(String type, int monthlySales, float extraHours) {
          this.type = type;
          this.monthlySales = monthlySales;
          this.extraHours = extraHours;
     }
   
          
     
    
     
     
}
