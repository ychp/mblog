package com.ychp.mblog.web.async.blog;

import com.ychp.blog.service.ArticleWriteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author yingchengpeng
 * @date 2018/8/12
 */
@Slf4j
@Component
public class CategoryUpdateAsync {

	@Autowired
	private ArticleWriteService articleWriteService;

	@Async
	public void syncName(Long id, String name) {
		articleWriteService.updateCategoryName(id, name);
	}

}
