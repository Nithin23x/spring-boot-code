package com.nithin.main;

import com.nithin.beans.*;
import com.nithin.config.ProjectConfig;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;
import java.util.function.Supplier;

public class ProjectOne {
    static void main() {
        System.out.println("Hello projectone ");
        var veh1 = new Vehicle() ;
        veh1.setName("Non spring context/ Container oject  Tata Car ");
        System.out.println(veh1.getName());

        System.out.println("\n");



        var context = new AnnotationConfigApplicationContext(ProjectConfig.class) ;
        //there are two types of ioc container 1.Bean context 2.Application Context ... 1.Bean context is older way to intiate the spring ioc container
        //2.Application context is more modern way to initiate the Spring ioc container ... in which the all the beans are intiated and  maintained...

        //(line 16) initializes the spring ioc container and lets us to access the objects(Beans) by taking the config files (ProjectConfig.class) as parameter
        // i.e we declared the objects(Beans) in the projectconfig.java file ... by providing those info to the context ... those objects will be converted into Beans
        // and will be available to the whole project and also IOC/context maintains those objects/Beans ...

        // here the "context" has many methods predefined methods such as getBean() which takes the required return type of the object ...

        // in below code ... getBean() we pass Vehicle.class as parameter ... i.e the getBean or context or IOC container has default mode of identifying the beans with the return type/data-type of the @Bean in config file
        // for this getBean() the object/Bean will be of Vehicle type that's why we used Vehicle.class
        //if there are more than one method having the same object returning then we use the method name or value or name to uniquely identify the methods ... or else it gives the exception NoUniqueBeanDefinitionException

        Vehicle veh =  context.getBean("vehicle",Vehicle.class) ;
        System.out.println(veh.getName());

        var veh2 = context.getBean("vehicle2",Vehicle.class) ;
        System.out.println(veh2.getName());

        String ss1 = context.getBean(String.class) ;
        System.out.println(ss1);

        Character ch1 = context.getBean(Character.class) ;
        System.out.println(ch1);


        // if we run below code ... we will get exception
        //Phone phoneOne = context.getBean(Phone.class );
        //NoUniqueBeanDefinitionException .... No qualifying bean of type 'com.nithin.beans.Phone' available: expected single matching bean but found 3: phone1,phone2,phone3

        Phone phonOne = context.getBean("phone1", Phone.class) ;
        System.out.println("THe phone is " + phonOne.getName() );

        var phoneTwo = context.getBean("second phone", Phone.class);
        // we use "var" here because there is no need to explictly mentioning the type of the phoneTwo .. beacuse the Phone.class will automatically return the Phone object and assign to the phoneTwo

        System.out.println("The second phone is " + phoneTwo.getName());


        Phone phoneThree = context.getBean("third phone" , Phone.class) ;
        System.out.println("The third phone is " + phoneThree.getName());

        var primaryPhone = context.getBean(Phone.class) ;
        System.out.println("The primary phone is " + primaryPhone.getName() );


        //COMPONENT BEAN CREATION CODE
        //but by creating beans with @Component annoatation there will be no control over the bean for programmer .. like naming the bean .. and assigning the deafult values ... while creating the bean


        var compoBean = context.getBean(ComponentBean.class) ;
        //compoBean.setName("Laptop component "); setting the attributes manually for the beans created using @Component

        System.out.println("This is bean by Component annotation --> " + compoBean.getName() + " <-- name " ); // here getName() will directly print the value assigned in @PostConstruct annotation method ...
        compoBean.Hello() ;
        //context.close();


        // CREATING BEANS USING registerBean() method ... using context object --> context.registerBean(beanName , Class of the bean , Supplier of the object/bean)

        // if we want to create a new instance/object of the class(in Bean package) based on a condition or create programatically  or just using the context object directly
        // we use registerBean method ...
        // the registerBean() method takes arguements ...
        //      beanName -- name of the bean u want have to it ,
        //      class of the the bean -- from which class the bean should be created ... with .class extension ...usually those classes are in the com.bean package
        //      supplier of the bean -- the supplier returns the bean object that will be added to the spring context
        // Supplier is an predefined interface in java ... and below is an lambda expression just like in javascript ...


        Supplier<RegisterBeanLaptop> lenovoLaptopSupplier = () -> {
            var lenovoLaptop = new RegisterBeanLaptop() ;
            lenovoLaptop.setName("Lenovo Laptop ");
            return lenovoLaptop ;
        } ;

        context.registerBean("lenovoLaptop" , RegisterBeanLaptop.class , lenovoLaptopSupplier);
        var getLaptopBean = context.getBean(RegisterBeanLaptop.class) ;

        System.out.println("the laptop bean is " + getLaptopBean.getName());



        Supplier<RegisterBeanPhone> phoneBeanSupplier = () -> {
            var phoneBean = new RegisterBeanPhone();
            phoneBean.setName("Lolo");
            return phoneBean ;

        } ;

        context.registerBean("phoneBean" , RegisterBeanPhone.class, phoneBeanSupplier );
        var getPhoneBean = context.getBean(RegisterBeanPhone.class) ;
        System.out.println("the phone is bean is " + getPhoneBean.getName());

//        Scanner sc = new Scanner(System.in) ;
//        int number = sc.nextInt() ;
//
//
//        //creating beans based on conditions ...
//        if(number>34) {
//            context.registerBean("phoneBeanTwo" , RegisterBeanPhone.class , phoneBeanSupplier);
//        }
//        else{
//            context.registerBean("laptopBeanTwo" , RegisterBeanLaptop.class, lenovoLaptopSupplier);
//        }
//
//        try {
//            var beanLaptopTwo = context.getBean("phoneBeanTwo" , RegisterBeanPhone.class) ;
//            System.out.println("the try catch beanLaptopTwo bean name " + beanLaptopTwo.getName());
//        } catch (NoSuchBeanDefinitionException e) {
//            System.out.println("The bean does not exist ");
//        }

        // Wiring Beans and Auto-Wiring

        var vehicle = context.getBean(VehicleWiring.class) ;
        System.out.println(vehicle.getVehicleName());

        var person = context.getBean(PersonWiring.class) ;
        System.out.println(person.getPersonName());
        System.out.println(person.getVehicleWiring());


    }
}
