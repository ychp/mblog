package com.ychp.mblog.web.controller.log;

import com.ychp.async.publisher.AsyncPublisher;
import com.ychp.blog.model.LikeLog;
import com.ychp.blog.service.LikeLogReadService;
import com.ychp.blog.service.LikeLogWriteService;
import com.ychp.common.exception.ResponseException;
import com.ychp.ip.component.IPServer;
import com.ychp.mblog.web.async.log.DislikeEvent;
import com.ychp.mblog.web.async.log.LikeEvent;
import com.ychp.mblog.web.controller.bean.LogConverter;
import com.ychp.mblog.web.controller.bean.request.log.LikeLogRequest;
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
	private LogConverter logConverter;

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
			ipInfo = logConverter.getIpInfo(ip);
			ipInfoWriteService.create(ipInfo);
		}

		DeviceInfo deviceInfo = logConverter.getDeviceInfo(request);

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
