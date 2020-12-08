package com.wannistudio.servletcontainer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.servlet.http.HttpServlet;
import java.io.File;

@SpringBootApplication
public class ServletcontainerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServletcontainerApplication.class, args);
    }

}
