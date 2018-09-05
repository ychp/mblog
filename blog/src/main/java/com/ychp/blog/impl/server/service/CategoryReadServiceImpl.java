package com.ychp.blog.impl.server.service;

import com.google.common.collect.Maps;
import com.ychp.common.exception.InvalidException;
import com.ychp.common.exception.ResponseException;
import com.ychp.common.model.paging.Paging;
import com.ychp.blog.bean.query.CategoryCriteria;
import com.ychp.blog.model.Category;
import com.ychp.blog.impl.server.repository.CategoryRepository;
import com.ychp.blog.service.CategoryReadService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
* @author yingchengpeng
* @date 2018-08-09
*/
@Service
public class CategoryReadServiceImpl implements CategoryReadService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category findById(Long id) {
        if(id == null) {
            throw new InvalidException("category.id.empty", "id", id);
        }
        try {
            return categoryRepository.findById(id);
        } catch (Exception e) {
            throw new ResponseException("category.find.fail", e.getMessage(), e.getCause());
        }
    }

    @Override
    public Paging<Category> paging(CategoryCriteria criteria) {
        try {
            return categoryRepository.paging(criteria.toMap());
        } catch (Exception e) {
            throw new ResponseException("category.paging.fail", e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Category> listAll() {
        try {
            return categoryRepository.list(Maps.newHashMap());
        } catch (Exception e) {
            throw new ResponseException("category.list.fail", e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Category> list(CategoryCriteria criteria) {
        try {
            return categoryRepository.list(criteria.toMap());
        } catch (Exception e) {
            throw new ResponseException("category.list.fail", e.getMessage(), e.getCause());
        }
    }

}