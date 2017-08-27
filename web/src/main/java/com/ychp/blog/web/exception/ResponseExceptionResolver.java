package com.ychp.blog.web.exception;

import com.google.common.base.Throwables;
import com.ychp.common.exception.ResponseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 2017/8/27
 */
@Slf4j
@ControllerAdvice
public class ResponseExceptionResolver {

    private final MessageSource messageSource;

    @Autowired
    public ResponseExceptionResolver(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(value = ResponseException.class)
    public void OPErrorHandler(ResponseException se, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Locale locale = request.getLocale();
        String uri = request.getRequestURI();
        Map<String, String[]> parameterMap = request.getParameterMap();
        log.error("request uri[{}] by params = {} fail, case {}", uri, parameterMap, Throwables.getStackTraceAsString(se));
        log.debug("ResponseException happened, locale={}, cause={}", locale, Throwables.getStackTraceAsString(se));
        String message = se.getErrorCode();
        try {
            message = messageSource.getMessage(se.getErrorCode(), null, se.getErrorCode(), locale);
        } catch (Exception e) {
            log.error("get message fail by code = {}, case {}", se.getErrorCode(), Throwables.getStackTraceAsString(e));
        }
        response.sendError(se.getStatus(), message);
    }
}
