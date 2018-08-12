package com.ychp.mblog.web.controller.log;

import com.ychp.async.publisher.AsyncPublisher;
import com.ychp.blog.model.LikeLog;
import com.ychp.blog.service.LikeLogReadService;
import com.ychp.blog.service.LikeLogWriteService;
import com.ychp.common.exception.ResponseException;
import com.ychp.ip.component.IPServer;
import com.ychp.ip.enums.IPAPIType;
import com.ychp.ip.model.IpAddress;
import com.ychp.mblog.web.async.log.DislikeEvent;
import com.ychp.mblog.web.async.log.LikeEvent;
import com.ychp.mblog.web.controller.bean.request.log.LikeLogRequest;
import com.ychp.request.model.UserAgent;
import com.ychp.request.util.RequestUtils;
import com.ychp.user.model.DeviceInfo;
import com.ychp.user.model.IpInfo;
import com.ychp.user.service.DeviceInfoReadService;
import com.ychp.user.service.DeviceInfoWriteService;
import com.ychp.user.service.IpInfoReadService;
import com.ychp.user.service.IpInfoWriteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yingchengpeng
 * @date 2018/8/11
 */
@Api(description = "点赞")
@RestController
@RequestMapping("/api/like-log")
public class LikeLogs {

	@Autowired
	private IpInfoReadService ipInfoReadService;

	@Autowired
	private DeviceInfoReadService deviceInfoReadService;

	@Autowired
	private LikeLogReadService likeLogReadService;

	@Autowired
	private IpInfoWriteService ipInfoWriteService;

	@Autowired
	private DeviceInfoWriteService deviceInfoWriteService;

	@Autowired
	private LikeLogWriteService likeLogWriteService;

	@Autowired
	private IPServer ipServer;

	@Autowired
	private AsyncPublisher publisher;

	@ApiOperation(value = "点赞接口", httpMethod = "POST")
	@PostMapping
	public void like(@RequestBody LikeLogRequest likeLogRequest, HttpServletRequest request) {
		String ip = ipServer.getIp(request);

		LikeLog exist = likeLogReadService.findByAimAndIp(likeLogRequest.getAimId(),
				likeLogRequest.getType(), ip);

		if(exist != null) {
			throw new ResponseException("aim.has.liked");
		}

		LikeLog log = makeLikeLog(likeLogRequest, ip);

		IpInfo ipInfo = ipInfoReadService.findByIp(ip);

		if(ipInfo == null) {
			ipInfo = makeIpInfo(ip);
			ipInfoWriteService.create(ipInfo);
		}

		DeviceInfo deviceInfo = makeDeviceInfo(request);

		DeviceInfo existDevice = deviceInfoReadService.findByUniqueInfo(deviceInfo);
		if(existDevice != null) {
			log.setDeviceId(existDevice.getId());
		} else {
			deviceInfoWriteService.create(deviceInfo);
		}

		likeLogWriteService.create(log);
		publisher.post(new LikeEvent(likeLogRequest.getAimId(), likeLogRequest.getType()));
	}

	private LikeLog makeLikeLog(LikeLogRequest likeLogRequest, String ip) {
		LikeLog log = new LikeLog();
		log.setIp(ip);
		log.setAimId(likeLogRequest.getAimId());
		log.setType(likeLogRequest.getType());
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

	@ApiOperation(value = "取消点赞", httpMethod = "DELETE")
	@DeleteMapping
	public void cancel(@RequestBody LikeLogRequest likeLogRequest, HttpServletRequest request) {
		String ip = ipServer.getIp(request);

		LikeLog exist = likeLogReadService.findByAimAndIp(likeLogRequest.getAimId(),
				likeLogRequest.getType(), ip);

		if(exist == null) {
			throw new ResponseException("aim.not.liked");
		}

		likeLogWriteService.delete(exist.getId());
		publisher.post(new DislikeEvent(likeLogRequest.getAimId(), likeLogRequest.getType()));
	}
}
