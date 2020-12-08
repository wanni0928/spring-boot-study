package com.wannistudio.autoconfig;

import com.wannistudio.Holoman;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootConfiguration
//@ComponentScan // 컴포넌트 조회
//@EnableAutoConfiguration // 웹 서버 관련 설정 애노테이션 WebApplicationType.NONE 설정을 하면, 해당 애노테이션이 없어도 앱은 동작한다.

@SpringBootApplication
//@ComponentScan
public class AutoConfigApplication {

    public static void main(String[] args) {
//        SpringApplication.run(AutoConfigApplication.class, args);
        SpringApplication springApplication = new SpringApplication(AutoConfigApplication.class);
//        springApplication.setWebApplicationType(WebApplicationType.NONE);
        springApplication.run(args);
    }
}
