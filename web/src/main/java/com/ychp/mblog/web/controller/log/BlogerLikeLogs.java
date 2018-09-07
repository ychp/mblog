package com.ychp.mblog.web.controller.log;

import com.google.common.collect.Lists;
import com.ychp.blog.bean.query.LikeLogCriteria;
import com.ychp.blog.model.LikeLog;
import com.ychp.blog.service.LikeLogReadService;
import com.ychp.common.exception.ResponseException;
import com.ychp.common.model.paging.Paging;
import com.ychp.mblog.web.controller.bean.response.log.LikeLogVO;
import com.ychp.user.model.DeviceInfo;
import com.ychp.user.model.IpInfo;
import com.ychp.user.service.DeviceInfoReadService;
import com.ychp.user.service.IpInfoReadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yingchengpeng
 * @date 2018/8/11
 */
@Api(description = "点赞记录")
@RestController
@RequestMapping("/api/bloger/like-log")
public class BlogerLikeLogs {

	@Autowired
	private LikeLogReadService likeLogReadService;

	@Autowired
	private IpInfoReadService ipInfoReadService;

	@Autowired
	public DeviceInfoReadService deviceInfoReadService;

	@ApiOperation("点赞记录分页接口")
	@GetMapping("paging")
	public Paging<LikeLogVO> paging(LikeLogCriteria criteria) {
		Paging<LikeLog> likeLogPaging;
		try {
			likeLogPaging = likeLogReadService.paging(criteria);
		} catch (Exception e) {
			throw new ResponseException("like.log.paging.fail", e.getMessage(), e.getCause());
		}

		if(likeLogPaging.isEmpty()) {
			return Paging.empty();
		}

		List<LikeLog> likeLogs = likeLogPaging.getDatas();

		List<String> ips = likeLogs.stream().map(LikeLog::getIp).collect(Collectors.toList());

		List<IpInfo> ipInfos;

		try {
			ipInfos = ipInfoReadService.findByIps(ips);
		} catch (Exception e) {
			throw new ResponseException("ip.info.find.fail", e.getMessage(), e.getCause());
		}

		Map<String, IpInfo> ipInfoByIp = ipInfos.stream()
				.collect(Collectors.toMap(IpInfo::getIp, ipInfo -> ipInfo));

		List<Long> deviceIds = likeLogs.stream().map(LikeLog::getDeviceId).collect(Collectors.toList());

		List<DeviceInfo> deviceInfos;

		try {
			deviceInfos = deviceInfoReadService.findByIds(deviceIds);
		} catch (Exception e) {
			throw new ResponseException("device.info.find.fail", e.getMessage(), e.getCause());
		}

		Map<Long, DeviceInfo> deviceInfoById = deviceInfos.stream()
				.collect(Collectors.toMap(DeviceInfo::getId, deviceInfo -> deviceInfo));

		List<LikeLogVO> result = Lists.newArrayListWithCapacity(likeLogs.size());

		for (LikeLog likeLog : likeLogs) {
			result.add(new LikeLogVO(likeLog,
					ipInfoByIp.get(likeLog.getIp()),
					deviceInfoById.get(likeLog.getDeviceId())));
		}
		return new Paging<>(likeLogPaging.getTotal(), result);
	}

}
