package com.ychp.mblog.web.controller.article;

import com.ychp.blog.model.Category;
import com.ychp.blog.service.CategoryReadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yingchengpeng
 * @date 2018-08-10
 */
@Api(description = "文章类目")
@RestController
@RequestMapping("/api/category")
public class Categories {

    @Autowired
    private CategoryReadService categoryReadService;

    @ApiOperation("类目")
    @GetMapping("list")
    public List<Category> listAll() {
        return categoryReadService.listAll();
    }

}
