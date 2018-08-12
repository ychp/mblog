package com.ychp.mblog.web.async.blog;

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
public class BlogVisitAsync {

	@Autowired
	private ArticleWriteService articleWriteService;

	@Async
	public void visit(Long id) {
		articleWriteService.increasePopular(id);
	}

}
