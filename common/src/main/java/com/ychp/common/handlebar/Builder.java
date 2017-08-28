package com.ychp.common.handlebar;

import com.github.jknack.handlebars.Handlebars;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.Map;

import static com.ychp.common.handlebar.constant.BuilderConstants.*;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 17/2/9
 */
@Slf4j
public abstract class Builder {

    public String build(String template, Map<String, Object> params, Map<String, String> args, Boolean isPath, Boolean isWriteToLocal){
        String content = null;
        try {
            if(StringUtils.isEmpty(template)){
                return "";
            }
            content = buildFile(template, params, isPath);
            if(isWriteToLocal) {
                String fileSuffix = args.get(FILE_FILESUFFIX_KEY);
                String outPath = args.get(OUT_PATH_KEY) != null ? args.get(OUT_PATH_KEY) : getDefaultOutPath(args);
                String fullOutPath = outPath.endsWith("/") ?
                        outPath + (StringUtils.isEmpty(args.get(FILE_NAME_KEY)) ? DEFAULT_OUT_FILE_NAME : args.get(FILE_NAME_KEY)) :
                        outPath + "/" + (StringUtils.isEmpty(args.get(FILE_NAME_KEY)) ? DEFAULT_OUT_FILE_NAME : args.get(FILE_NAME_KEY));
                writeToLocal(fullOutPath, fileSuffix, content);
            }
        } catch (IOException e){
            log.error("generate content fail, template = {}, params = {}, args = {}, isWriteToLocal = {}, case {}",
                    template, params, args, isWriteToLocal, Throwables.getStackTraceAsString(e));
        }
        return content;
    }

    private String getDefaultOutPath(Map<String, String> paramMap){
        String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        int lastIndex = path.lastIndexOf(File.separator) + 1;
        return path.substring(0, lastIndex) + (StringUtils.isEmpty(paramMap.get(FILE_NAME_KEY)) ? "" : paramMap.get(FILE_NAME_KEY));
    }

    private void writeToLocal(String outPath, String fileSuf, String content){
        BufferedWriter bufferedWriter = null;
        try{
            String fileName = outPath + (StringUtils.isEmpty(fileSuf) ? "" : fileSuf);
            File file = new File(fileName);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            bufferedWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(bufferedWriter != null){
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected abstract String buildFile(String templateOrPath, Map<String, Object> params, Boolean isPath) throws IOException;

    protected Handlebars getHandlebars(){
        Handlebars handlebars = new Handlebars();
        handlebars.registerHelper("equals", (context, options) -> {
            CharSequence result;
            String right = context.toString();
            String left = options.param(0).toString();
            if ((right != null) && (left != null)) {
                if (right.equals(left)) {
                    result = options.fn(context);
                } else {
                    result = options.inverse(context);
                }
                return result;
            } else {
                return null;
            }
        });
        handlebars.registerHelper("unequals", (context, options) -> {
            CharSequence result;
            String right = context.toString();
            String left = options.param(0).toString();
            if ((right != null) && (left != null)) {
                if (!right.equals(left)) {
                    result = options.fn(context);
                } else {
                    result = options.inverse(context);
                }
                return result;
            } else {
                return null;
            }
        });
        return handlebars;
    }

}
