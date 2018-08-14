package com.ychp.blog.impl.server.converter;

import com.ychp.blog.model.Article;
import com.ychp.blog.model.ArticleDetail;
import com.ychp.common.util.HtmlUtils;
import com.ychp.markdown.model.MarkdownEntity;
import com.ychp.markdown.wrapper.MarkdownWrapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

/**
 * @author yingchengpeng
 * @date 2018/8/12
 */
public class ArticleConverter {

	public static void parse(Article article, ArticleDetail detail) {
		String content = detail.getContent();
		if(StringUtils.isEmpty(content)) {
			return;
		}
		String image;
		String synopsis;
		if(detail.getIsMarkdown()) {
			MarkdownEntity entity = MarkdownWrapper.ofContent(content);
			content = entity.getHtml();
		}

		image = parseImageWithHtml(content);
		synopsis = parseSynopsisWithHtml(content);

		article.setImage(image);
		article.setSynopsis(synopsis);
	}

	public static String parseImageWithMarkdown(String content) {
		MarkdownEntity entity = MarkdownWrapper.ofContent(content);
		return parseImageWithHtml(entity.getHtml());
	}

	public static String parseImageWithHtml(String content) {
		Document document = Jsoup.parse(content);
		Elements elements = document.getElementsByTag("img");
		if(elements.isEmpty()) {
			return "";
		}
		Element image = elements.first();
		return image.attr("src");
	}

	public static String parseSynopsisWithMarkdown(String content) {
		MarkdownEntity entity = MarkdownWrapper.ofContent(content);
		return parseSynopsisWithHtml(entity.getHtml());
	}

	public static String parseSynopsisWithHtml(String content) {
		return HtmlUtils.splitAndFilterString(content, 300);
	}

}
