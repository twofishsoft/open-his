package com.twofish.config;

import cn.hutool.core.util.ReflectUtil;
import com.github.xiaoymin.swaggerbootstrapui.conf.Consts;
import com.twofish.annotation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ww
 * @description 自定义参数解析器
 **/
@Slf4j
public class ValidatorResolver implements HandlerMethodArgumentResolver {

    /**
     * @author ww
     * @Description 用于判定是否需要处理该参数，返回true为需要，并会去调用下面的方法resolveArgument
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Validator.class);
    }

    /**
     * @author ww
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //获取参数的class
        Class clazz = parameter.getParameterType();
        //实例化参数对象
        Object object = BeanUtils.instantiate(clazz);
        return object;
    }

}
