package com.ychp.user.impl.server.service;

import com.ychp.common.exception.ResponseException;
import com.ychp.user.impl.server.repository.DeviceInfoRepository;
import com.ychp.user.model.DeviceInfo;
import com.ychp.user.service.DeviceInfoReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
* @author yingchengpeng
* @date 2018-08-09
*/
@Service
public class DeviceInfoReadServiceImpl implements DeviceInfoReadService {

    @Autowired
    private DeviceInfoRepository deviceInfoRepository;

    @Override
    public DeviceInfo findByUniqueInfo(DeviceInfo deviceInfo) {
        try {
            return deviceInfoRepository.findByUniqueInfo(deviceInfo);
        } catch (Exception e) {
            throw new ResponseException("device.info.find.fail", e.getMessage(), e.getCause());
        }
    }
}