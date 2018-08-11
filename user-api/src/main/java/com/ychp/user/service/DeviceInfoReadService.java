package com.ychp.user.service;

import com.ychp.user.model.DeviceInfo;

/**
* @author yingchengpeng
* @date: 2018/08/11
*/
public interface DeviceInfoReadService {

    /**
     * 获取设备信息
     *
     * @param deviceInfo 设备信息
     * @return 结果
     */
    DeviceInfo findByUniqueInfo(DeviceInfo deviceInfo);

}