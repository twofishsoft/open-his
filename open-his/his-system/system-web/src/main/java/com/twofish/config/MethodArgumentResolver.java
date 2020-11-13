package com.twofish.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ww
 * @description
 * @create 2020/11/13 9:20
 */
//@Configuration
public class MethodArgumentResolver {

    @Autowired
    private RequestMappingHandlerAdapter adapter;

    @PostConstruct
    public void injectSelfMethodArgumentResolver() {
        List<HandlerMethodArgumentResolver> argumentResolvers = new ArrayList<>();
        argumentResolvers.add(new ValidatorResolver());
        argumentResolvers.addAll(adapter.getArgumentResolvers());
        adapter.setArgumentResolvers(argumentResolvers);
    }

}
