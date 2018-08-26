package com.order.manager.routes;

import com.order.manager.processor.BadProcessor;
import com.order.manager.processor.BadProcessorBean;
import com.order.manager.processor.TxtProcessor;
import com.order.manager.processor.WarehouseProcessor;
import com.order.manager.processor.XmlProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InRouteBuilder extends SpringRouteBuilder {
    @Autowired
    private BadProcessor badProcessor;

    @Autowired
    private BadProcessorBean badProcessorBean;

    @Autowired
    private TxtProcessor txtProcessor;

    @Autowired
    private WarehouseProcessor warehouseProcessor;

    @Autowired
    private XmlProcessor xmlProcessor;

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
                .bean(xmlProcessor);

        from("file:order/txt")
                .process(txtProcessor);

        from("file:order/bad")
                .bean(badProcessorBean)
                .process(badProcessor);

        from("file:order/warehouse")
                .process(warehouseProcessor);
    }
}
