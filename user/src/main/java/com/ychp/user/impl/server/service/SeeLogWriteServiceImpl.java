package com.ychp.user.impl.server.service;

import com.ychp.common.exception.ResponseException;
import com.ychp.user.bean.request.SeeLogCreateRequest;
import com.ychp.user.impl.server.repository.DeviceInfoRepository;
import com.ychp.user.impl.server.repository.IpInfoRepository;
import com.ychp.user.impl.server.repository.SeeLogRepository;
import com.ychp.user.service.SeeLogWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
* @author yingchengpeng
* @date 2018-08-09
*/
@Service
public class SeeLogWriteServiceImpl implements SeeLogWriteService {

    @Autowired
    private SeeLogRepository seeLogRepository;

    @Autowired
    private IpInfoRepository ipInfoRepository;

    @Autowired
    private DeviceInfoRepository deviceInfoRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long create(SeeLogCreateRequest request) {
        try {
            if(request.getIpInfo() != null) {
                ipInfoRepository.create(request.getIpInfo());
            }

            if(request.getDeviceInfo() != null) {
                deviceInfoRepository.create(request.getDeviceInfo());
                request.getSeeLog().setDeviceId(request.getDeviceInfo().getId());
            }

            seeLogRepository.create(request.getSeeLog());
            return request.getSeeLog().getId();
        } catch (Exception e) {
            throw new ResponseException("seeLog.create.fail", e.getMessage(), e.getCause());
        }
    }
}
