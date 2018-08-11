package com.ychp.user.service;

import com.ychp.user.model.IpInfo;

/**
* @author yingchengpeng
* @date: 2018/08/11
*/
public interface IpInfoReadService {

    /**
     * 获取ip信息
     *
     * @param ip ip地址
     * @return 结果
     */
    IpInfo findByIp(String ip);

}