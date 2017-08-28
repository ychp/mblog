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
    protected String buildFile(String templatePath, Map<String, Object> params) throws IOException {
        Handlebars handlebars = getHandlebars();
        Template template = handlebars.compile(templatePath);
        return template.apply(params);
    }
}
