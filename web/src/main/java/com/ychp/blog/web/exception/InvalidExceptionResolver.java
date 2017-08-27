package com.ychp.blog.web.exception;

import com.google.common.base.Throwables;
import com.ychp.common.exception.InvalidException;
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
public class InvalidExceptionResolver {

    private final MessageSource messageSource;

    @Autowired
    public InvalidExceptionResolver(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(value = InvalidException.class)
    public void OPErrorHandler(InvalidException se, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Locale locale = request.getLocale();
        String uri = request.getRequestURI();
        log.error("request uri[{}] by error {} = {} fail, case {}", uri, se.getParamKey(), se.getParam(), Throwables.getStackTraceAsString(se));
        log.debug("ResponseException happened, locale={}, cause={}", locale, Throwables.getStackTraceAsString(se));
        String message = se.getErrorCode();
        try {
            message = messageSource.getMessage(se.getErrorCode(), null, se.getErrorCode(), locale);
        } catch (Exception e) {
            log.error("get message fail by code = {}, case {}", se.getErrorCode(), Throwables.getStackTraceAsString(e));
        }
        response.sendError(500, message);
    }
}
