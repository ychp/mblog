package com.ychp.mblog.web.exception;

import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import com.ychp.cache.exception.CacheException;
import com.ychp.common.exception.InvalidException;
import com.ychp.common.exception.ResponseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

/**
 * @author yingchengpeng
 * @date 2017-08-27
 */
@Slf4j
@ControllerAdvice
public class ExceptionResolver {

    private final MessageSource messageSource;

    @Autowired
    public ExceptionResolver(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseBody
    @ExceptionHandler(value = CacheException.class)
    public ResponseEntity<String> cacheErrorHandler(CacheException ce,
                                                 HttpServletRequest request) {
        Locale locale = request.getLocale();
        String uri = request.getRequestURI();
        Map<String, String[]> parameterMap = Maps.newHashMap();
        parameterMap.putAll(request.getParameterMap());
        log.error("request uri[{}] by params = {} fail, case {}", uri, parameterMap, Throwables.getStackTraceAsString(ce));
        log.debug("ResponseException happened, locale={}, cause={}", locale, Throwables.getStackTraceAsString(ce));
        String message = getMessage(ce.getMessage(), locale);
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @ExceptionHandler(value = InvalidException.class)
    public ResponseEntity<String> invalidErrorHandler(InvalidException se,
                                                 HttpServletRequest request) {
        Locale locale = request.getLocale();
        String uri = request.getRequestURI();
        log.error("request uri[{}] by error {} = {} fail, case {}", uri, se.getParamKey(), se.getParam(), Throwables.getStackTraceAsString(se));
        log.debug("ResponseException happened, locale={}, cause={}", locale, Throwables.getStackTraceAsString(se));
        String message = getMessage(se.getErrorCode(), locale);
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @ExceptionHandler(value = ResponseException.class)
    public ResponseEntity<String> responseErrorHandler(ResponseException se,
                                                 HttpServletRequest request) {
        Locale locale = request.getLocale();
        String uri = request.getRequestURI();
        Map<String, String[]> parameterMap = Maps.newHashMap();
        parameterMap.putAll(request.getParameterMap());
        Integer status = se.getStatus();
        if (!Objects.equals(status, HttpStatus.UNAUTHORIZED.value())){
            log.error("request uri[{}] by params = {} fail, error = {}, case {}",
                        uri, parameterMap, se.getErrorCode(), Throwables.getStackTraceAsString(se));
            log.debug("ResponseException happened, locale = {}, cause = {}",
                    locale, Throwables.getStackTraceAsString(se));
        }
        String message = getMessage(se.getErrorCode(), locale);
        return new ResponseEntity<>(message, HttpStatus.valueOf(se.getStatus()));
    }

    private String getMessage(String errorCode, Locale locale) {
        String finalMessage = errorCode;
        try {
            finalMessage = messageSource.getMessage(errorCode, null, errorCode, locale);
        } catch (Exception e) {
            log.error("get message fail by code = {}, case {}",
                    errorCode, Throwables.getStackTraceAsString(e));
        }
        return finalMessage;
    }
}
