package com.ychp.mblog.web.controller.log;

import com.ychp.ip.component.IPServer;
import com.ychp.ip.enums.IPAPIType;
import com.ychp.ip.model.IpAddress;
import com.ychp.mblog.web.controller.bean.request.log.SeeLogRequest;
import com.ychp.request.model.UserAgent;
import com.ychp.request.util.RequestUtils;
import com.ychp.user.bean.request.SeeLogCreateRequest;
import com.ychp.user.model.DeviceInfo;
import com.ychp.user.model.IpInfo;
import com.ychp.user.model.SeeLog;
import com.ychp.user.service.DeviceInfoReadService;
import com.ychp.user.service.IpInfoReadService;
import com.ychp.user.service.SeeLogWriteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yingchengpeng
 * @date 2018/8/11
 */
@Api(description = "访问记录-所有页面都要请求，不需要关心请求结果")
@RestController
@RequestMapping("/api/see-log")
public class SeeLogs {

	@Autowired
	private IpInfoReadService ipInfoReadService;

	@Autowired
	private DeviceInfoReadService deviceInfoReadService;

	@Autowired
	private SeeLogWriteService seeLogWriteService;

	@Autowired
	private IPServer ipServer;

	@ApiOperation("访问记录记录接口")
	@PostMapping
	public void log(@RequestBody SeeLogRequest seeLogRequest, HttpServletRequest request) {
		String ip = ipServer.getIp(request);

		SeeLogCreateRequest createRequest = new SeeLogCreateRequest();

		SeeLog log = makeSeeLog(seeLogRequest, ip);

		IpInfo ipInfo = ipInfoReadService.findByIp(ip);

		if(ipInfo == null) {
			ipInfo = makeIpInfo(ip);
			createRequest.setIpInfo(ipInfo);
		}

		DeviceInfo deviceInfo = makeDeviceInfo(request);

		DeviceInfo exist = deviceInfoReadService.findByUniqueInfo(deviceInfo);
		if(exist != null) {
			log.setDeviceId(exist.getId());
		} else {
			createRequest.setDeviceInfo(deviceInfo);
		}

		createRequest.setSeeLog(log);
		seeLogWriteService.create(createRequest);
	}

	private SeeLog makeSeeLog(SeeLogRequest seeLogRequest, String ip) {
		SeeLog log = new SeeLog();
		log.setIp(ip);
		log.setUrl(seeLogRequest.getUrl());
		log.setUri(seeLogRequest.getUri());
		return log;
	}

	private IpInfo makeIpInfo(String ip) {
		IpInfo ipInfo = new IpInfo();
		IpAddress ipAddress = ipServer.getIpAddress(ip, IPAPIType.TAOBAO.value());
		ipInfo.setIp(ip);
		ipInfo.setCountry(ipAddress.getCountry());
		ipInfo.setProvince(ipAddress.getProvince());
		ipInfo.setCity(ipAddress.getCity());
		return ipInfo;
	}

	private DeviceInfo makeDeviceInfo(HttpServletRequest request) {
		DeviceInfo deviceInfo = new DeviceInfo();
		UserAgent userAgent = RequestUtils.getUaInfo(request);
		deviceInfo.setOs(userAgent.getSystem());
		deviceInfo.setBrowser(userAgent.getBrowser());
		deviceInfo.setBrowserVersion(userAgent.getBrowserVersion());
		deviceInfo.setDevice(userAgent.getDevice());
		return deviceInfo;
	}
}
