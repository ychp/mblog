package com.ychp.markdown.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yingchengpeng
 * @date 2018/8/14
 */
@Data
@NoArgsConstructor
public class MarkdownEntity implements Serializable {

	private static final long serialVersionUID = -4642358232586143884L;

	public static String TAG_WIDTH = "<style type=\"text/css\"> %s { width:85%%} </style>";

	/**
	 * css 样式
	 */
	private String css;

	/**
	 * 最外网的div标签， 可以用来设置样式，宽高，字体等
	 */
	private Map<String, String> divStyle = new ConcurrentHashMap<>();

	/**
	 * 转换后的html文档
	 */
	private String html;

	public MarkdownEntity(String html) {
		this.html = html;
	}

	@Override
	public String toString() {
		return css + "\n<div " + parseDiv() + ">\n" + html + "\n</div>";
	}


	private String parseDiv() {
		StringBuilder builder = new StringBuilder();
		for (Map.Entry<String, String> entry : divStyle.entrySet()) {
			builder.append(entry.getKey()).append("=\"")
					.append(entry.getValue()).append("\" ");
		}
		return builder.toString();
	}


	public void addDivStyle(String attrKey, String value) {
		if (divStyle.containsKey(attrKey)) {
			divStyle.put(attrKey, divStyle.get(attrKey) + " " + value);
		} else {
			divStyle.put(attrKey, value);
		}
	}


	public void addWidthCss(String tag) {
		String wcss = String.format(TAG_WIDTH, tag);
		css += wcss;
	}

}
