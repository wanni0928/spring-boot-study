package com.wannistudio.externalconfig;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties("wanni")
@Validated
@Getter @Setter
public class WanniProperties {
    @NonNull
    private String name;
    private int age;
    private String profile;
}
