package com.ychp.mblog.web.async.user;

import com.ychp.common.model.SkyUser;
import com.ychp.user.model.UserLoginLog;
import com.ychp.user.service.UserLoginLogWriteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author yingchengpeng
 * @date 2018/8/9
 */
@Slf4j
@Component
public class LoginLogAsync {

	@Autowired
	private UserLoginLogWriteService userLoginLogWriteService;

	@Async
	public void log(SkyUser skyUser) {
		if(skyUser == null) {
			return;
		}

		UserLoginLog loginLog = makeLog(skyUser);
		userLoginLogWriteService.create(loginLog);
	}

	private UserLoginLog makeLog(SkyUser skyUser) {
		UserLoginLog loginLog = new UserLoginLog();
		loginLog.setUserId(skyUser.getId());
		loginLog.setIp(skyUser.getIp());
		return loginLog;
	}
}
