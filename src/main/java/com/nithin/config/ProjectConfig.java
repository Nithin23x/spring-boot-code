package com.nithin.config;

import com.nithin.beans.Phone;
import com.nithin.beans.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.lang.NonNullApi;

@Configuration // the config annotation is used to tell the spring that its a config file which has the objects(Beans) and other config stuff

@ComponentScan(basePackages = "com.nithin.beans")
public class ProjectConfig {

    //we should name the medthods in noun based naming convention
    @Bean  // the @Bean annotation tells the spring that its a Bean to be created and be sent to the IOC container ....
    Vehicle vehicle() {  //Here Vehicle means the return type of the method ... just like int sum()
      var veh = new Vehicle() ;
      veh.setName("Benz");
      return veh ;
    }

    @Bean
    Vehicle vehicle2() {
        var veh2 = new Vehicle();
        veh2.setName("Audi");
        return veh2 ;
    }

    @Bean
    String lolo() {
        return "Hello String method " ;
    }

    @Bean
    Character bolo() {
        return 'b' ;
    }

    @Bean @Primary // the primary annotation is used to mark the primary/default bean ...
        // if in getBean() method calling there is no mention of name/value of other beans of returning same datatype   then this Bean will be executed
    Phone phone1() {
        var ph1 = new Phone() ;
        ph1.setName("Samsung Phone");
        return ph1 ;
    }

    @Bean (name = "second phone")
    Phone phone2() {
        var ph2 = new Phone() ;
        ph2.setName("Apple Phone ");
        return ph2 ;
    }

    @Bean (value = "third phone")
    Phone phone3() {
        var ph3 = new Phone() ;
        ph3.setName("Oneplus Phone ");
        return ph3 ;
    }



}
