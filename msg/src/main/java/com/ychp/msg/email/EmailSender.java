/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package com.ychp.msg.email;

import java.util.Map;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 2017/8/27
 */
public interface EmailSender {

    void sendTemplate(String address, String templateKey, Map<String, Object> params);

    void sendTemplate(String address, String subject, String template, Map<String, Object> params);

    void sendText(String address, String subject, String content, boolean html);
}
