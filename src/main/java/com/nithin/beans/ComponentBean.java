package com.nithin.beans;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component      //@Component is a stereotype annotation .. and there are other stereo annotations
                // By using @Component annotation the beans can be created to the IOC container/Spring context by writing relatively less code than @Bean option to create beans
                // the @Component annotation should be added on top of the java class which we want to convert into the Bean
public class ComponentBean {



    private String name ;

    public String getName() {
        return name;
    }

    //but by creating beans with @Component annoatation there will be no control over the bean for programmer .. like naming the bean .. and assigning the deafult values ... while creating the bean

    //to have a facility to assign a deafult values (or) to have some control over the bean creation the @PostConstruct method is used ..
    // @PostConstruct  executes the method after bean creation ... @PostConstruct instructs the spring to execute the method under it after the bean creation....

    @PostConstruct
    public void init() {
        this.name = "Default Post Construct Bean" ;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void Hello() {
        System.out.println("Hi this is Component from Hello method ");
    }

    @PreDestroy  // the @PreDestroy method will executed just before the context is closed (or) destroyed ... this @PreDestroy will be useful to close some of the external/internal code or connections like database or apis etc
                 // but in modern Spring and spring boot context is destoryed very well and all the connections and code are closed properly ... @PreDestroy is used very rarely in the modern code bases ...
    public void notInIt() {
        System.out.println("Pre Destroy triggered after context.close() method ");
    }
}
