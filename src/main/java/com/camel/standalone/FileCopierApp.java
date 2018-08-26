package com.camel.standalone;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class FileCopierApp {
    public static void main(String[] args) throws Exception {
        CamelContext context = new DefaultCamelContext();

        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file:data/inbox?noop=true")
                        .to("file:data/outbox");
            }
        });

        System.out.println("Starting Program");
        context.start();
        Thread.sleep(3000);
        context.stop();
        System.out.println("Exiting Program");
    }
}
