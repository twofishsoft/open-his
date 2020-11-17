package com.twofish.config;

import com.alibaba.fastjson.JSON;
import com.twofish.annotation.CurrUser;
import com.twofish.domain.SimpleUser;
import com.twofish.utils.ShiroSecurityUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.lang.reflect.Field;

/**
 * @author ww
 **/
public class ValidatorResolver implements HandlerMethodArgumentResolver {

    /**
     * @author ww
     * @Description 用于判定是否需要处理该参数，返回true为需要，并会去调用下面的方法resolveArgument
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrUser.class);
    }

    /**
     * @author ww
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //获取参数的class
        Class clazz = parameter.getParameterType();
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        CurrUser currUser = parameter.getParameterAnnotation(CurrUser.class);
        // 把reqeust的body读取到StringBuilder
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        char[] buf = new char[1024];
        int rd;
        while((rd = reader.read(buf)) != -1){
            sb.append(buf, 0, rd);
        }
        Object object = JSON.parseObject(sb.toString(), clazz);
        SimpleUser currentSimpleUser = ShiroSecurityUtils.getCurrentSimpleUser();

        Field field = clazz.getSuperclass().getDeclaredField("simpleUser");
        field.setAccessible(true);
        field.set(object, currentSimpleUser);

        field = clazz.getDeclaredField("createBy");
        field.setAccessible(true);
        field.set(object, currentSimpleUser.getUserName());

        field = clazz.getDeclaredField("updateBy");
        field.setAccessible(true);
        field.set(object, currentSimpleUser.getUserName());

        return object;
    }

}
