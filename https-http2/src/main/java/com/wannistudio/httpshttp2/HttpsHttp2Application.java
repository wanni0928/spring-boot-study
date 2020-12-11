package com.wannistudio.httpshttp2;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HttpsHttp2Application {

    @GetMapping("/hello")
    public String hello() {
        return "Hello Spring";
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(HttpsHttp2Application.class);
//        app.setBannerMode(Banner.Mode.OFF);
//        app.setBanner((environment, sourceClass, out) -> {
//            out.println("banner");
//        });
        app.run(args);
//        SpringApplication.run(HttpsHttp2Application.class, args);

    }

//    @Bean
//    public ServletWebServerFactory serverFactory() {
//        TomcatServletWebServerFactory tomcatServletWebServerFactory = new TomcatServletWebServerFactory();
//        tomcatServletWebServerFactory.addAdditionalTomcatConnectors(createStandardConnector());
//        return tomcatServletWebServerFactory;
//    }
//
//    private Connector createStandardConnector() {
//        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        connector.setPort(8080);
//        return connector;
//    }

}
