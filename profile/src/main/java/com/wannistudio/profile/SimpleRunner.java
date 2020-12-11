package com.wannistudio.profile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SimpleRunner implements ApplicationRunner {
    private Logger logger = LoggerFactory.getLogger(SimpleRunner.class);

    @Autowired
    String hello;
    @Autowired
    WanniProperties wanniProperties;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        System.out.println("===================");
//        System.out.println(hello);
//        System.out.println(wanniProperties.getProfile());
//        System.out.println("===================");
        logger.info("===================");
        logger.info(hello);
        logger.info(wanniProperties.getProfile());
        logger.info("===================");
    }
}
