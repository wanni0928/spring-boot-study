package com.wannistudio.externalconfig;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest(properties = "wanni.name=curry")
@SpringBootTest
class ExternalConfigApplicationTests {

//    @Autowired
//    Environment environment;

    @Autowired
    WanniProperties wanniProperties;

    @Test
    void contextLoads() {
        assertThat(wanniProperties.getName()).isEqualTo("currrryyyyy");
    }

}
