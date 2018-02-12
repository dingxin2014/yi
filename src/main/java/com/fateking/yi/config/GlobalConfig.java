package com.fateking.yi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class GlobalConfig {

    @Value("${simulate: #{true}}")
    private Boolean simulate;

}
