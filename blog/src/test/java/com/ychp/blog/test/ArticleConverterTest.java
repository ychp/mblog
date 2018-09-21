package com.ychp.blog.test;

import com.ychp.blog.impl.server.converter.ArticleConverter;
import org.junit.Test;

/**
 * @author yingchengpeng
 * @date 2018/8/14
 */
public class ArticleConverterTest {

	@Test
	public void test() {
		String markdown = getMarkdown();
//		String html = getHtmlDemo();
		System.out.println(ArticleConverter.parseImageWithMarkdown(markdown));
//		ArticleConverter.parseImageWithHtml(html);
		System.out.println(ArticleConverter.parseSynopsisWithMarkdown(markdown));

	}

	public String getMarkdown() {
		return "# 一、描述\n" +
				"简易版论坛系统，第一版为个人博客形式<br>\n" +
				"主要功能有用户、分类、文章标签、文章、点赞、收藏、评价、说说、相册等功能<br>\n" +
				"辅助功能有数据分析(PV/UV等),黑名单(IP限制),友情链接<br>\n" +
				"后续可继续迭代，网站访问分析，访问行为分析，推文等功能\n" +
				"\n" +
				"# 二、功能\n" +
				"具体功能说明见 ![Features.md](https://github.com/ychp/blog/wiki/feature)\n" +
				"具体功能说明见 [Features.md](https://github.com/ychp/blog/wiki/feature)\n" +
				"\n" +
				"# 三、模块说明\n" +
				"## 1、handlebars\n" +
				"模板工具模块：基于handlebars创建的模板渲染工具，用于定义常用的模板函数\n" +
				"具体函数说明见 [Handlebars.md](https://github.com/ychp/blog/wiki/handlebars)\n" +
				"\n" +
				"## 2、code-builder\n" +
				"代码生成工具模块：基础的内容生成工具，仅支持模板渲染生成\n" +
				"\n" +
				"## 3、common\n" +
				"通用工具模块：常用的工具类以及实体\n" +
				"\n" +
				"## 4、mybatis\n" +
				"ORM模块：集成mybatis，支持spring-boot以及代码生成\n" +
				"\n" +
				"## 5、msg\n" +
				"消息服务模块：集成各种消息服务，目前仅有邮件服务\n";
	}

	public String getHtmlDemo() {
		return "<div class=\"recommend-item-box type_blog clearfix\" data-track-click=\"{&quot;mod&quot;:&quot;popu_387&quot;,&quot;con&quot;:&quot;,https://blog.csdn.net/Monkey_LZL/article/details/57480599,BlogCommendFromBaidu_2&quot;}\">\n" +
				"\t\t<div class=\"content\" style=\"width: 606px;\">\n" +
				"\t\t\t<h4 class=\"text-truncate oneline\" style=\"width: 506px;\">\n" +
				"\t\t\t\t<a href=\"https://blog.csdn.net/Monkey_LZL/article/details/57480599\" target=\"_blank\" title=\"如何利用Github在<em>Markdown</em>中优雅地插入<em>图片</em>\">\n" +
				"\t\t\t\t\t如何利用Github在<em>Markdown</em>中优雅地插入<em>图片</em>\t\t\t\t</a>\n" +
				"\t\t\t</h4>\n" +
				"\t\t\t<div class=\"info-box d-flex align-content-center\">\n" +
				"\t\t\t\t<p>\n" +
				"\t\t\t\t\t<a class=\"avatar\" src=\"https://blog.csdn.net/Monkey_LZL\" target=\"_blank\">\n" +
				"\t\t\t\t\t\t<img src=\"https://avatar.csdn.net/E/6/5/3_monkey_lzl.jpg\" alt=\"Monkey_LZL\" class=\"avatar-pic\">\n" +
				"\t\t\t\t\t\t<span class=\"namebox\" style=\"left: -34.5px;\">\n" +
				"\t\t\t\t\t\t\t<span class=\"name\">Monkey_LZL</span>\n" +
				"\t\t\t\t\t\t\t<span class=\"triangle\"></span>\n" +
				"\t\t\t\t\t\t</span>\n" +
				"\t\t\t\t\t</a>\n" +
				"\t\t\t\t</p>\n" +
				"\t\t\t\t<p class=\"date-and-readNum\">\n" +
				"\t\t\t\t\t<span class=\"date hover-show\">02-26</span>\n" +
				"\t\t\t\t\t<span class=\"read-num hover-hide\">\n" +
				"\t\t\t\t\t\t<svg class=\"icon csdnc-yuedushu\" aria-hidden=\"true\">\n" +
				"\t\t\t\t\t\t\t<use xlink:href=\"#csdnc-yuedushu\"></use>\n" +
				"\t\t\t\t\t\t</svg>\n" +
				"\t\t\t\t\t\t3755</span>\n" +
				"\t\t\t\t\t</p>\n" +
				"\t\t\t\t</div>\n" +
				"\t\t\t\t<p class=\"content oneline\" style=\"width: 606px;\">\n" +
				"\t\t\t\t\t<a class=\"oneline\" href=\"https://blog.csdn.net/Monkey_LZL/article/details/57480599\" target=\"_blank\">\n" +
				"\t\t\t\t\t\t将博客中的图片托管到GitHub，并在Markdown中引用地址即可，解决本地图片图片误删的麻烦...\t\t\t\t\t</a>\n" +
				"\t\t\t\t</p>\n" +
				"\t\t</div>\n" +
				"\t\t\t\t\t\t<div class=\"img-box float-left\">\n" +
				"\t\t\t\t<a href=\"https://blog.csdn.net/Monkey_LZL/article/details/57480599\">\n" +
				"\t\t\t\t\t<img src=\"https://csdnimg.cn/release/phoenix/article_rand_pic/engineering/engineering19.jpg\" alt=\"\">\t\n" +
				"\t\t\t\t</a>\n" +
				"\t\t\t</div>\n" +
				"\t\t\t</div>";
	}

}
