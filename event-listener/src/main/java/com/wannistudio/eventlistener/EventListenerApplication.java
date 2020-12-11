package com.wannistudio.eventlistener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import java.util.Set;

@SpringBootApplication
public class EventListenerApplication {

    public static void main(String[] args) {
//        SpringApplication.run(EventListenerApplication.class, args);
        SpringApplication app = new SpringApplication(EventListenerApplication.class);
//        app.addListeners(event -> {
//            System.out.println("==========================================");
//            System.out.println(event.getClass());
//            System.out.println("==========================================");
//        });
        app.run(args);
    }

}

/*

==========================================
class org.springframework.boot.context.event.ApplicationContextInitializedEvent
==========================================
2020-12-10 11:08:04.492  INFO 9832 --- [           main] c.w.e.EventListenerApplication           : Starting EventListenerApplication using Java 1.8.0_241 on DESKTOP-HDALO7H with PID 9832 (D:\003_sideProject\000_web\000_java\000_spring\workspace\spring_boot\event-listener\target\classes started by chju in D:\003_sideProject\000_web\000_java\000_spring\workspace\spring_boot\event-listener)
2020-12-10 11:08:04.497  INFO 9832 --- [           main] c.w.e.EventListenerApplication           : No active profile set, falling back to default profiles: default
==========================================
class org.springframework.boot.context.event.ApplicationPreparedEvent
==========================================
2020-12-10 11:08:05.440  INFO 9832 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2020-12-10 11:08:05.447  INFO 9832 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2020-12-10 11:08:05.448  INFO 9832 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.39]
2020-12-10 11:08:05.530  INFO 9832 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2020-12-10 11:08:05.530  INFO 9832 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 977 ms
2020-12-10 11:08:05.673  INFO 9832 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
2020-12-10 11:08:05.819  INFO 9832 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
==========================================
class org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent
==========================================
==========================================
class org.springframework.context.event.ContextRefreshedEvent
==========================================
2020-12-10 11:08:05.837  INFO 9832 --- [           main] c.w.e.EventListenerApplication           : Started EventListenerApplication in 1.771 seconds (JVM running for 4.319)
==========================================
class org.springframework.boot.context.event.ApplicationStartedEvent
==========================================
==========================================
class org.springframework.boot.availability.AvailabilityChangeEvent
==========================================
==========================================
class org.springframework.boot.context.event.ApplicationReadyEvent
==========================================
==========================================
class org.springframework.boot.availability.AvailabilityChangeEvent
==========================================

 */