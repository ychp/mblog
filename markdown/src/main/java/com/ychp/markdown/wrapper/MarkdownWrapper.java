package com.ychp.markdown.wrapper;

import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.util.options.MutableDataSet;
import com.ychp.markdown.model.MarkdownEntity;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;


/**
 * @author yingchengpeng
 * @date 2018/8/14
 */
public class MarkdownWrapper {

	private static String MD_CSS = null;

	@PostConstruct
	public void init() {
		try {
			// 从文件中读取markdown内容
			InputStream stream = getClass().getClassLoader().getResourceAsStream("markdown.css");
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "utf-8"));

			String line = reader.readLine();
			StringBuffer sb = new StringBuffer("");
			while (line != null) {
				sb.append(line).append("\n");
				line = reader.readLine();
			}
			MD_CSS = sb.toString();
			MD_CSS = "<style type=\"text/css\">\n" + MD_CSS + "\n</style>\n";
		} catch (Exception e) {
			MD_CSS = "";
		}
	}

	/**
	 * 直接将markdown语义的文本转为html格式输出
	 *
	 * @param content markdown语义文本
	 */
	public static MarkdownEntity ofContent(String content) {
		String html = parse(content);
		MarkdownEntity entity = new MarkdownEntity();
		entity.setCss(MD_CSS);
		entity.setHtml(html);
		entity.addDivStyle("class", "markdown-body ");
		return entity;
	}


	/**
	 * markdown to image
	 *
	 * @param content markdown contents
	 * @return parse html contents
	 */
	public static String parse(String content) {
		MutableDataSet options = new MutableDataSet();
		options.setFrom(ParserEmulationProfile.MARKDOWN);

		// enable table parse!
		options.set(Parser.EXTENSIONS, Collections.singletonList(TablesExtension.create()));


		Parser parser = Parser.builder(options).build();
		HtmlRenderer renderer = HtmlRenderer.builder(options).build();

		Node document = parser.parse(content);
		return renderer.render(document);
	}

}
