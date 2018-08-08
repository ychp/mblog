package com.ychp.handlebars;

import com.github.jknack.handlebars.Handlebars;
import com.ychp.handlebars.helps.EqualsHelper;
import com.ychp.handlebars.helps.FormateDateHelper;
import com.ychp.handlebars.helps.UnEqualsHelper;

/**
 * @author yingchengpeng
 * @date 2018/8/8
 */
public class HandlebarsFactory {

	public static Handlebars getInstance(){
		Handlebars handlebars = new Handlebars();
		handlebars.registerHelper("equals", new EqualsHelper());
		handlebars.registerHelper("unequals", new UnEqualsHelper());
		handlebars.registerHelper("formatDate", new FormateDateHelper());
		return handlebars;
	}
}
