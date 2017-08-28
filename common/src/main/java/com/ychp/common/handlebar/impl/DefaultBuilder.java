package com.ychp.common.handlebar.impl;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.ychp.common.handlebar.Builder;

import java.io.IOException;
import java.util.Map;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 2017/8/28
 */
public class DefaultBuilder extends Builder {

    @Override
    protected String buildFile(String templateOrPath, Map<String, Object> params, Boolean isPath) throws IOException {
        Handlebars handlebars = getHandlebars();
        Template template;
        if(isPath) {
            template = handlebars.compile(templateOrPath);
        } else {
            template = handlebars.compileInline(templateOrPath);
        }
        return template.apply(params);
    }
}
