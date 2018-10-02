package com.ychp.blog.impl.server.service;

import com.ychp.blog.impl.server.repository.CategoryRepository;
import com.ychp.blog.model.Category;
import com.ychp.blog.service.CategoryWriteService;
import com.ychp.common.exception.ResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;


/**
* @author yingchengpeng
* @date 2018-08-09
*/
@Service
public class CategoryWriteServiceImpl implements CategoryWriteService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Long create(Category category) {
        try {
            Category exist = categoryRepository.findByUserIdAndName(category.getUserId(), category.getName());
            if(exist != null) {
                throw new ResponseException("category.is.exist");
            }
            categoryRepository.create(category);
            return category.getId();
        } catch (ResponseException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseException("category.create.fail", e.getMessage(), e.getCause());
        }
    }

    @Override
    public Boolean update(Category category) {
        try {
            Category exist = categoryRepository.findByUserIdAndName(category.getUserId(), category.getName());
            if(exist != null && !Objects.equals(category.getId(), exist.getId())) {
                throw new ResponseException("category.is.exist");
            }
            return categoryRepository.update(category);
        } catch (ResponseException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseException("category.update.fail", e.getMessage(), e.getCause());
        }
    }

    @Override
    public Boolean delete(Long id) {
        try {
            return categoryRepository.delete(id);
        } catch (Exception e) {
            throw new ResponseException("category.delete.fail", e.getMessage(), e.getCause());
        }
    }

}
