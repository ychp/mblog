package com.ychp.blog.bean.response;

import com.ychp.blog.model.Comment;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author yingchengpeng
 * @date 2018-09-10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "评价展示数据")
public class CommentWithChildrenInfo implements Serializable {

    private static final long serialVersionUID = -325997914420452006L;

    private Comment comment;

    private List<Comment> children;

}
