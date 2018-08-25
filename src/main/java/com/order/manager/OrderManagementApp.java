package com.order.manager;

import com.order.manager.processor.BadProcessor;
import com.order.manager.processor.TxtProcessor;
import com.order.manager.processor.WarehouseProcessor;
import com.order.manager.processor.XmlProcessor;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class OrderManagementApp {
    public static void main(String[] args) throws Exception {
        CamelContext context = new DefaultCamelContext();

        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file:order/in?noop=true")
                        .process(new Processor() {
                            public void process(Exchange exchange) throws Exception {
                                System.out.println("<-- Initiating Routing to producer -->");
                                System.out.println("Order Name: " + exchange.getIn().getHeader("CamelFileName"));
                            }
                        })
                        .choice()
                        .when(header("CamelFileName").endsWith(".xml"))
                        .to("file:order/xml")
                        .when(header("CamelFileName").endsWith(".txt"))
                        .to("file:order/txt")
                        .otherwise()
                        .to("file:order/bad").stop() //prevent bad orders from flowing further
                        .end()
                        .process(new Processor() {
                            public void process(Exchange exchange) throws Exception {
                                System.out.println("<-- Routing finished -->");
                            }
                        })
                        .to("file:order/warehouse");

                from("file:order/xml")
                        .process(new XmlProcessor());

                from("file:order/txt")
                        .process(new TxtProcessor());

                from("file:order/bad")
                        .process(new BadProcessor());

                from("file:order/warehouse")
                        .process(new WarehouseProcessor());
            }
        });

        System.out.println("Starting Program");
        context.start();
        Thread.sleep(3000);
        context.stop();
        System.out.println("Exiting Program");
    }
}
