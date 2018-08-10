package com.ychp.blog.impl.server.service;

import com.ychp.common.exception.InvalidException;
import com.ychp.common.exception.ResponseException;
import com.ychp.common.model.paging.Paging;
import com.ychp.blog.dto.query.LabelCriteria;
import com.ychp.blog.model.Label;
import com.ychp.blog.impl.server.repository.LabelRepository;
import com.ychp.blog.service.LabelReadService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
* @author yingchengpeng
* @date 2018-08-09
*/
@Service
public class LabelReadServiceImpl implements LabelReadService {

    @Autowired
    private LabelRepository labelRepository;

    @Override
    public Label findById(Long id) {
        if(id == null) {
            throw new InvalidException("label.id.empty", "id", id);
        }
        try {
            return labelRepository.findById(id);
        } catch (Exception e) {
            throw new ResponseException("label.find.fail", e.getMessage(), e.getCause());
        }
    }

    @Override
    public Paging<Label> paging(LabelCriteria criteria) {
        try {
            return labelRepository.paging(criteria.toMap());
        } catch (Exception e) {
            throw new ResponseException("label.paging.fail", e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Label> listVisible() {
        try {
            LabelCriteria criteria = new LabelCriteria();
            criteria.setVisible(true);
            return labelRepository.list(criteria.toMap());
        } catch (Exception e) {
            throw new ResponseException("label.paging.fail", e.getMessage(), e.getCause());
        }
    }

}