package com.ychp.mblog.web.async.article.listener;

import com.ychp.async.annontation.AsyncBean;
import com.ychp.async.annontation.AsyncSubscriber;
import com.ychp.blog.service.ArticleWriteService;
import com.ychp.mblog.web.async.article.ArticleCommentEvent;
import com.ychp.mblog.web.async.article.ArticleVisitEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yingchengpeng
 * @date 2018/8/9
 */
@Slf4j
@AsyncBean
public class BlogAsync {

	@Autowired
	private ArticleWriteService articleWriteService;

	@AsyncSubscriber
	public void visit(ArticleVisitEvent articleVisitEvent) {
		articleWriteService.increasePopular(articleVisitEvent.getId());
	}

	@AsyncSubscriber
	public void comment(ArticleCommentEvent articleCommentEvent) {
		if(articleCommentEvent.getIsSub()) {
			return;
		}
		if(articleCommentEvent.getIsAdd()) {
			articleWriteService.increaseComment(articleCommentEvent.getId());
		} else {
			articleWriteService.decreaseComment(articleCommentEvent.getId());
		}
	}
}
