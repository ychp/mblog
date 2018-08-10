package com.ychp.blog.impl.server.service;

import com.ychp.common.exception.ResponseException;
import com.ychp.blog.model.Label;
import com.ychp.blog.impl.server.repository.LabelRepository;
import com.ychp.blog.service.LabelWriteService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


/**
* @author yingchengpeng
* @date 2018-08-09
*/
@Service
public class LabelWriteServiceImpl implements LabelWriteService {

    @Autowired
    private LabelRepository labelRepository;

    @Override
    public Long create(Label label) {
        try {
            labelRepository.create(label);
            return label.getId();
        } catch (Exception e) {
            throw new ResponseException("label.create.fail", e.getMessage(), e.getCause());
        }
    }

    @Override
    public Boolean update(Label label) {
        try {
            return labelRepository.update(label);
        } catch (Exception e) {
            throw new ResponseException("label.update.fail", e.getMessage(), e.getCause());
        }
    }

    @Override
    public Boolean delete(Long id) {
        try {
            return labelRepository.delete(id);
        } catch (Exception e) {
            throw new ResponseException("label.delete.fail", e.getMessage(), e.getCause());
        }
    }

}
