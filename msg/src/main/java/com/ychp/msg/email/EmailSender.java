package com.ychp.msg.email;

import java.util.Map;

/**
 * Desc:
 * @author yingchengpeng
 * @date 2016/08/27
 */
public interface EmailSender {

    /**
     * 发送信息
     * @param address 接收地址
     * @param templateKey 模板key
     * @param params 业务参数
     */
    void sendTemplate(String address, String templateKey, Map<String, Object> params);

    /**
     * 发送信息
     * @param address 接收地址
     * @param subject 标题
     * @param template 模板
     * @param params 业务参数
     */
    void sendTemplate(String address, String subject, String template, Map<String, Object> params);

    /**
     * 发送信息
     * @param address 接收地址
     * @param subject 标题
     * @param content 内容
     * @param html 内容是否为html
     */
    void sendText(String address, String subject, String content, boolean html);
}
