package com.ychp.blog.web.session;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 2017/8/28
 */
@Slf4j
public class SessionManager {

//    @Autowired
//    private JedisTemplate jedisTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    private final static TypeReference<Map<String, Object>> TYPE_OF_MAP = new TypeReference<Map<String, Object>>(){};
    private final static Integer EXPIRE_SECONDS = 1800;

    public void saveSession(HttpSession session, String msid) {
        Enumeration<String> keys = session.getAttributeNames();
        Map<String, Object> sessionContent = Maps.newHashMap();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            sessionContent.put(key, session.getAttribute(key));
        }

//        jedisTemplate.excute(jedis -> {
//            try {
//                String sessionJson = objectMapper.writeValueAsString(sessionContent);
//                jedis.setex(getKey(msid), EXPIRE_SECONDS, sessionJson);
//                return true;
//            } catch (JsonProcessingException e) {
//                log.warn("write to json string data = {}, case {}", sessionContent, Throwables.getStackTraceAsString(e));
//                return false;
//            }
//        });
    }

    public HttpSession getSession(HttpServletRequest request, String msid) {
        HttpSession session = request.getSession();

//        Map<String, Object> sessionContent = jedisTemplate.excute(jedis -> {
//            String value = jedis.get(getKey(msid));
//            if(StringUtils.isEmpty(value)) {
//                return null;
//            }
//            try {
//                return objectMapper.readValue(value, TYPE_OF_MAP);
//            } catch (IOException e) {
//                log.warn("read to json string data = {}, case {}", value, Throwables.getStackTraceAsString(e));
//                return null;
//            }
//        });
//        if(sessionContent == null) {
//            return session;
//        }
//
//        sessionContent.entrySet().forEach(entry -> session.setAttribute(entry.getKey(), entry.getValue()));
        return session;
    }

    private String getKey(String msid) {
        return "session:" + msid;
    }
}
