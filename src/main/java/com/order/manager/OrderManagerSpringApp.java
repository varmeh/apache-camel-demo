package com.order.manager;

import org.apache.camel.CamelContext;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OrderManagerSpringApp {
    public static void main(String[] args) throws Exception {
        CamelContext context = new SpringCamelContext(new ClassPathXmlApplicationContext("context-spring-order-manager.xml"));

//        CamelContext context = new SpringCamelContext();

        System.out.println("Starting Program");
        context.start();
        Thread.sleep(3000);
        context.stop();
        System.out.println("Exiting Program");
    }
}
