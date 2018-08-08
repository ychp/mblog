package com.ychp.code.builder;

import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.google.common.collect.Lists;
import com.ychp.handlebars.HandlebarsFactory;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yingchengpeng
 * @date 2018-08-08
 */
public abstract class Builder {

    public List<String> build(String templatePath, String outPath, Map<String, Object> templateParamMap,
                              Boolean isPath, Boolean isWriteToLocal){
        List<String> templates = getTemplatePath(templatePath);
        List<String> contents = Lists.newArrayListWithCapacity(templates.size());
        try {
            outPath = StringUtils.isEmpty(outPath) ? getDefaultOutPath(): outPath;
            String content;
            String fileName;

            for(String template : templates){
                if(StringUtils.isEmpty(template)){
                    continue;
                }
                content = buildFile(template, templateParamMap, isPath);
                contents.add(content);
                if(isWriteToLocal) {
                    fileName = template.substring(template.indexOf(templatePath) + templatePath.length(),
                            template.lastIndexOf('.'));
                    new FileService().writeToLocal(outPath + File.separator + fileName, content);
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return contents;
    }

    /**
     * 获取模板文件
     * @return 有模板文件
     */
    private List<String> getTemplatePath(String basePath) {
        if(basePath == null){
            basePath = getDefaultTemplate().trim();
        }
        File file = new File(basePath);
        if(file.isFile()) {
            return Lists.newArrayList(basePath);
        }
        List<File> files = getAllFiles(basePath);
        return files.stream().map(File::getAbsolutePath).collect(Collectors.toList());
    }

    private List<File> getAllFiles(String basePath) {
        File file = new File(basePath);
        if(file.isFile()) {
            return Lists.newArrayList(file);
        } else if(!file.isDirectory()) {
            return Lists.newArrayListWithCapacity(0);
        }
        File[] files = file.listFiles();
        if(files == null) {
            return Lists.newArrayListWithCapacity(0);
        }
        List<File> result = Lists.newArrayList();
        for(File item : files) {
            if(item.isFile()) {
                result.add(item);
            } else if(file.isDirectory()) {
                result.addAll(getAllFiles(file.getPath()));
            }
        }
        return result;
    }

    /**
     * 获取默认模板路径
     * @return 默认模板路径
     */
    protected String getDefaultTemplate() {
        return "";
    }

    /**
     * 默认输出路径
     * @return
     */
    protected String getDefaultOutPath(){
        String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        int lastIndex = path.lastIndexOf(File.separator) + 1;
        return path.substring(0, lastIndex);
    }

    /**
     * 渲染模板内容数据
     * @param templateOrPath 模板或路径
     * @param params 变量
     * @param isPath 是否为文件路径
     * @return 内容
     * @throws IOException
     */
    protected String buildFile(String templateOrPath, Map<String, Object> params, Boolean isPath) throws IOException {
        Handlebars handlebars = HandlebarsFactory.getInstance();
        Template template;
        if(isPath) {
            template = handlebars.compile(templateOrPath);
        } else {
            template = handlebars.compileInline(templateOrPath);
        }
        return template.apply(params);
    }
}
