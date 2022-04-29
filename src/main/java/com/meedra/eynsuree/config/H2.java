package com.meedra.eynsuree.config;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


/**
 * Spring Security by default prevents access to the GUI console of the in-memory H2 database
 * This class allows you access the console on port 8090
 */
@Component
//@Profile("test") // <-- up to you
public class H2 {

    private org.h2.tools.Server webServer;

    private org.h2.tools.Server tcpServer;

    @EventListener(org.springframework.context.event.ContextRefreshedEvent.class)
    public void start() throws java.sql.SQLException {
        this.webServer = org.h2.tools.Server.createWebServer("-webPort", "8090", "-tcpAllowOthers").start();
        this.tcpServer = org.h2.tools.Server.createTcpServer("-tcpPort", "7069", "-tcpAllowOthers").start();
    }

    @EventListener(org.springframework.context.event.ContextClosedEvent.class)
    public void stop() {
        this.tcpServer.stop();
        this.webServer.stop();
    }

}