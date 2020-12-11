package com.wannistudio.externalconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SimpleRunner implements ApplicationRunner {
//    @Value("${wanni.name}")
//    private String name;
    @Autowired
    WanniProperties wanniProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("=================");
//        System.out.println("name : " + wanniProperties.getName());
//        System.out.println("age : " + wanniProperties.getAge());
        System.out.println(wanniProperties.getProfile());
        System.out.println("한글");
        System.out.println("=================");

    }
}
