package com.twofish.config.shiro;

import com.twofish.constants.Constants;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;
import java.util.UUID;
import static org.apache.shiro.web.servlet.ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE;

/**
 * sessionId === token
 * 用于生成token
 * 如果head里有就返回
 * 没用就构建一个返回
 * @author ccy
 */
@Configuration
public class TokenWebSessionManager extends DefaultWebSessionManager {
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        //从头里面得到请求TOKEN 如果不存在就生成一个
        String header = WebUtils.toHttp(request).getHeader(Constants.TOKEN);
        if(StringUtils.hasText(header)){
            request.setAttribute(REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, header);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return  header;
        }
        return  UUID.randomUUID().toString() ;
        //return super.getSessionId(request, response);
    }
}
