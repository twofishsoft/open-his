package com.twofish.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import java.util.List;

/**
 * @author ww
 * @Description 配置参数解析器
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(validatorArgumentResolvers());
        super.addArgumentResolvers(argumentResolvers);
    }

    @Bean
    public ValidatorResolver validatorArgumentResolvers() {
        return new ValidatorResolver();
    }
}
