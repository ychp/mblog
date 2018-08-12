package com.ychp.mblog.web.async.log;

import com.ychp.blog.service.ArticleWriteService;
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
public class LikeAsync {

	@Autowired
	private ArticleWriteService articleWriteService;

	@Async
	public void increase(Long aimId, Integer type) {
		switch (type) {
			case 1:
				articleWriteService.increaseLike(aimId);
				break;
			case 2:
			case 3:
			default:
		}
	}

	@Async
	public void decrease(Long aimId, Integer type) {
		switch (type) {
			case 1:
				articleWriteService.decreaseLike(aimId);
				break;
			case 2:
			case 3:
			default:
		}
	}

}
