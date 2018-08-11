package com.ychp.mblog.web.async.log;

import com.ychp.blog.impl.server.repository.ArticleSummaryRepository;
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
	private ArticleSummaryRepository articleSummaryRepository;

	@Async
	public void increase(Long aimId, Integer type) {
		switch (type) {
			case 1:
				articleSummaryRepository.increaseLike(aimId);
				break;
			case 2:
			case 3:
			default:
		}
	}

}
