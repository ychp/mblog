/*
 * Copyright (c) 2016. 杭州端点网络科技有限公司.  All rights reserved.
 */

package com.ychp.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;

import java.text.SimpleDateFormat;
import java.util.Map;

/**
 *
 * Author:  yingchengpeng
 * Date: 2017-08-27 11:01:01
 */
public abstract class Criteria{

    private final static TypeReference<Map<String, Object>> model = new TypeReference<Map<String, Object>>(){};
    protected static final ObjectMapper MAPPER = new ObjectMapper();
    static{
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        MAPPER.registerModule(new GuavaModule());
        MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    public Map<String, Object> toMap(){
        return MAPPER.convertValue(this, model);
    }
}
