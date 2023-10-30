package com.example.application.views.main;

public class Country {
     private String name;
     private String continent;
     private int population;
     private City city;
     class City{
          private String name;
          private int population;
          public String getName() {
               return name;
          }
          public void setName(String name) {
               this.name = name;
          }
          public int getPopulation() {
               return population;
          }
          public void setPopulation(int population) {
               this.population = population;
          }
          public City(String name, int population) {
               this.name = name;
               this.population = population;
          }
          public City(String name) {
               this.name = name;
          }
          
          
     }
     
     public Country(String name, String continent, int population) {
          this.name = name;
          this.continent = continent;
          this.population = population;
     }
     
     public Country(String name, String continent, int population, String capitalCity) {
          this.name = name;
          this.continent = continent;
          this.population = population;
          this.city = new City(capitalCity);
     }

     public String getName() {
          return name;
     }
     public void setName(String name) {
          this.name = name;
     }
     public String getContinent() {
          return continent;
     }
     public void setContinent(String continent) {
          this.continent = continent;
     }
     public int getPopulation() {
          return population;
     }
     public void setPopulation(int population) {
          this.population = population;
     }
     public City getCity() {
          return city;
     }
     public void setCity(City city) {
          this.city = city;
     }
     
     
}
