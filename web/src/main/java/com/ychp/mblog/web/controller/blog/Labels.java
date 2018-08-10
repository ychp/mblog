package com.ychp.mblog.web.controller.blog;

import com.ychp.blog.dto.query.LabelCriteria;
import com.ychp.blog.model.Label;
import com.ychp.blog.service.LabelReadService;
import com.ychp.blog.service.LabelWriteService;
import com.ychp.common.model.paging.Paging;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author yingchengpeng
 * @date 2018-08-10
 */
@Api(description = "文章标签")
@RestController
@RequestMapping("/api/label")
public class Labels {

    @Autowired
    private LabelReadService labelReadService;

    @Autowired
    private LabelWriteService labelWriteService;

    @ApiOperation(value = "标签创建接口", httpMethod = "POST")
    @PostMapping
    public Long create(@RequestBody Label label) {
        return labelWriteService.create(label);
    }

    @ApiOperation(value = "标签更新接口", httpMethod = "PUT")
    @PutMapping
    public Boolean update(Long id, String name) {
        Label label = new Label();
        label.setId(id);
        label.setName(name);
        return labelWriteService.update(label);
    }

    @ApiOperation("标签分页接口")
    @GetMapping("paging")
    public Paging<Label> paging(LabelCriteria criteria) {
        return labelReadService.paging(criteria);
    }

    @ApiOperation(value = "设置标签可见", httpMethod = "PUT")
    @PutMapping("{id}/visible")
    public Boolean visible(@PathVariable Long id) {
        Label label = new Label();
        label.setId(id);
        label.setVisible(true);
        return labelWriteService.update(label);
    }

    @ApiOperation(value = "设置标签不可见", httpMethod = "PUT")
    @PutMapping("{id}/invisible")
    public Boolean invisible(@PathVariable Long id) {
        Label label = new Label();
        label.setId(id);
        label.setVisible(false);
        return labelWriteService.update(label);
    }

}
