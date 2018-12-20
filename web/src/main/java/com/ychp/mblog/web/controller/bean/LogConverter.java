package com.ychp.mblog.web.controller.bean;

import com.ychp.user.model.DeviceInfo;
import com.ychp.user.model.IpInfo;
import com.ychp.web.ip.component.IpServer;
import com.ychp.web.ip.enums.IPAPIType;
import com.ychp.web.ip.model.IpAddress;
import com.ychp.web.request.model.UserAgent;
import com.ychp.web.request.util.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Desc:
 * @author yingchengpeng
 * @date 2016/08/27
 */
@Component
public class LogConverter {

    @Autowired
    private IpServer ipServer;

    public IpInfo getIpInfo(String ip) {
        IpInfo ipInfo = new IpInfo();
        IpAddress ipAddress = ipServer.getIpAddress(ip, IPAPIType.TAOBAO.value());
        ipInfo.setIp(ip);
        ipInfo.setCountry(ipAddress.getCountry());
        ipInfo.setProvince(ipAddress.getProvince());
        ipInfo.setCity(ipAddress.getCity());
        return ipInfo;
    }

    public DeviceInfo getDeviceInfo(HttpServletRequest request) {
        DeviceInfo deviceInfo = new DeviceInfo();
        UserAgent userAgent = RequestUtils.getUaInfo(request);
        deviceInfo.setOs(userAgent.getSystem());
        deviceInfo.setBrowser(userAgent.getBrowser());
        deviceInfo.setBrowserVersion(userAgent.getBrowserVersion());
        deviceInfo.setDevice(userAgent.getDevice());
        return deviceInfo;
    }
}
