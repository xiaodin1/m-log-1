/**
 * 
 */
package org.mspring.mlog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mspring.mlog.entity.User;
import org.mspring.mlog.service.UserService;
import org.mspring.platform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Gao Youbo
 * @since 2013-1-11
 * @Description
 * @TODO
 */
public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    public static final String VALIDATE_CODE = "validateCode";
    public static final String USERNAME = "name";
    public static final String PASSWORD = "password";

    @Autowired
    private UserService userService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        // checkValidateCode(request);

        String username = obtainUsername(request);
        String password = obtainPassword(request);

        // 验证用户账号与密码是否对应
        username = username.trim();

        //User user = userService.getUserByName(username);
        User user = userService.login(username, password);

        if (user == null) {
            /*
             * 
             * if (forwardToDestination) {
             * request.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION,
             * exception); } else { HttpSession session =
             * request.getSession(false);
             * 
             * if (session != null || allowSessionCreation) {
             * request.getSession(
             * ).setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION,
             * exception); } }
             */
            throw new AuthenticationServiceException("password or username is notEquals");
        }

        // UsernamePasswordAuthenticationToken实现 Authentication
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
        // Place the last username attempted into HttpSession for views

        // 允许子类设置详细属性
        setDetails(request, authRequest);

        // 运行UserDetailsService的loadUserByUsername 再次封装Authentication
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    protected void checkValidateCode(HttpServletRequest request) {
        HttpSession session = request.getSession();

        String sessionValidateCode = obtainSessionValidateCode(session);
        // 让上一次的验证码失效
        session.setAttribute(VALIDATE_CODE, null);
        String validateCodeParameter = obtainValidateCodeParameter(request);
        if (StringUtils.isEmpty(validateCodeParameter) || !sessionValidateCode.equalsIgnoreCase(validateCodeParameter)) {
            throw new AuthenticationServiceException("validateCode.notEquals");
        }
    }

    private String obtainValidateCodeParameter(HttpServletRequest request) {
        Object obj = request.getParameter(VALIDATE_CODE);
        return null == obj ? "" : obj.toString();
    }

    protected String obtainSessionValidateCode(HttpSession session) {
        Object obj = session.getAttribute(VALIDATE_CODE);
        return null == obj ? "" : obj.toString();
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        Object obj = request.getParameter(USERNAME);
        return null == obj ? "" : obj.toString();
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        Object obj = request.getParameter(PASSWORD);
        return null == obj ? "" : obj.toString();
    }
}
