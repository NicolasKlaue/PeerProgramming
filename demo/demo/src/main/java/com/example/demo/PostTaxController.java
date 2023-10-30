package com.example.demo;

import java.io.IOException;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostTaxController {

     //Post
	@PostMapping("/posttax")
	public ResponseEntity<Employee> preTax(@RequestBody Employee postTaxEmployee) throws IOException {
          
          float preTaxSalary = 0;
          float base_salary = 0;
          float bonus = 0;

          

          System.out.println(postTaxEmployee.employeeType);
          System.out.println(postTaxEmployee.monthlySales);
          System.out.println(postTaxEmployee.extraHours);
          
          // Assign base salary
          if (postTaxEmployee.employeeType.equals("Manager")){
               base_salary = 8000;
               bonus = postTaxEmployee.extraHours * 30;
          } else if (postTaxEmployee.employeeType.equals("Employee")) {
               base_salary = 4000;
               bonus = postTaxEmployee.extraHours * 20;
          } else{
               //return fail json
               return ResponseEntity.badRequest().build(); // type of employee not selected correctly 
          }

          // Calculate increase in salary depending on the number of sales
          //if montlySales are more than 1000 and less than 1500
          if ((1000 <= postTaxEmployee.monthlySales) && (1500 >= postTaxEmployee.monthlySales)){
               preTaxSalary = base_salary + 100 + bonus;
          } else if(postTaxEmployee.monthlySales > 1500){
               preTaxSalary = base_salary + 200 + bonus;
          } else{
               preTaxSalary = base_salary + bonus;
          }
          postTaxEmployee.base_salary = base_salary;
          postTaxEmployee.bonus = bonus;
          postTaxEmployee.preTaxSalary = preTaxSalary;
          if (preTaxSalary > 1500) {
               postTaxEmployee.postTaxSalary = (float) (preTaxSalary *0.84);
               return ResponseEntity.ok(postTaxEmployee); // returning the pre tax salary
          }else if (preTaxSalary > 2000) {
               postTaxEmployee.postTaxSalary = (float) (preTaxSalary *0.82);
               return ResponseEntity.ok(postTaxEmployee); // returning the pre tax salary
          }

          postTaxEmployee.postTaxSalary = preTaxSalary;

          return ResponseEntity.ok(postTaxEmployee); // returning the pre tax salary

	}
}
