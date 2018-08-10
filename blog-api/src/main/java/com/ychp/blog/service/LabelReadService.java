package com.ychp.blog.service;

import com.ychp.blog.dto.query.LabelCriteria;
import com.ychp.blog.model.Label;
import com.ychp.common.model.paging.Paging;

/**
* @author yingchengpeng
* @date: 2018/08/10
*/
public interface LabelReadService {

    /**
     * 根据id查询
     *
     * @param id 主键id
     * @return 查询结果
     */
    Label findById(Long id);

    /**
     * 根据条件获取分页
     *
     * @param criteria 条件
     * @return 分页结果
     */
    Paging<Label> paging(LabelCriteria criteria);

}