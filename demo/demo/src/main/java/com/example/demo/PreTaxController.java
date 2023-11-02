package com.example.demo;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PreTaxController {

     //Post
	@PostMapping("/pretax")
	public ResponseEntity<Employee> preTax(@RequestBody Employee preTaxEmployee) throws IOException {
          
          float preTaxSalary = 0;
          float base_salary = 0;
          float bonus = 0;
          

          System.out.println(preTaxEmployee.employeeType);
          System.out.println(preTaxEmployee.monthlySales);
          System.out.println(preTaxEmployee.extraHours);
          
          // Assign base salary
          if (preTaxEmployee.employeeType.equals("Manager")){
               base_salary = 8000;
               bonus = preTaxEmployee.extraHours * 30;
          } else if (preTaxEmployee.employeeType.equals("Employee")) {
               base_salary = 4000;
               bonus = preTaxEmployee.extraHours * 20;
          } else{
               //return fail json
               return ResponseEntity.badRequest().build(); // type of employee not selected correctly 
          }

          // Calculate increase in salary depending on the number of sales
          //if montlySales are more than 1000 and less than 1500
          if ((1000 <= preTaxEmployee.monthlySales) && (1500 >= preTaxEmployee.monthlySales)){
               preTaxSalary = base_salary + 100;
          } else if(preTaxEmployee.monthlySales > 1500){
               preTaxSalary = base_salary + 200;
          } else{
               preTaxSalary = base_salary;
          }
          preTaxEmployee.base_salary = base_salary;
          preTaxEmployee.bonus = bonus;
          preTaxEmployee.preTaxSalary = preTaxSalary;
          preTaxEmployee.finalSalary = preTaxSalary;

          

          return ResponseEntity.ok(preTaxEmployee); // returning the pre tax salary

	}
}
