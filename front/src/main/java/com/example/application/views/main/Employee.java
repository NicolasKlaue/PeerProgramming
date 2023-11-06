package com.example.application.views.main;


public class Employee {
     public String employeeType;
     public int monthlySales;
     public float base_salary;
     public float bonus;
     public float extraHours;
     public float preTaxSalary;
     public float finalSalary;

     public Employee(String employeeType, int monthlySales, float extraHours) {
          this.employeeType = employeeType;
          this.monthlySales = monthlySales;
          this.extraHours = extraHours;
     }
     
     public Employee(String employeeType) {
          this.employeeType = employeeType;
     }

     public float getFinalSalary() {
          return finalSalary;
     }

     public void setFinalSalary(float postTaxSalary) {
          this.finalSalary = postTaxSalary;
     }

     public String getEmployeeType() {
          return employeeType;
     }

     public void setEmployeeType(String employeeType) {
          this.employeeType = employeeType;
     }

     public int getMonthlySales() {
          return monthlySales;
     }

     public void setMonthlySales(int monthlySales) {
          this.monthlySales = monthlySales;
     }

     public float getExtraHours() {
          return extraHours;
     }

     public void setExtraHours(float extraHours) {
          this.extraHours = extraHours;
     }

     public float getPreTaxSalary() {
          return preTaxSalary;
     }

     public void setPreTaxSalary(float preTaxSalary) {
          this.preTaxSalary = preTaxSalary;
     }

     public float getBase_salary() {
          return base_salary;
     }

     public void setBase_salary(float base_salary) {
          this.base_salary = base_salary;
     }

     public float getBonus() {
          return bonus;
     }

     public void setBonus(float bonus) {
          this.bonus = bonus;
     }

}
