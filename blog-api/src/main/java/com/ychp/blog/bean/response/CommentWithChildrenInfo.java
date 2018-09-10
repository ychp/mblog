package com.ychp.blog.bean.response;

import com.ychp.blog.model.Comment;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 16/8/29
 */
@Data
@ApiModel(description = "评价展示数据")
public class CommentWithChildrenInfo implements Serializable {

    private static final long serialVersionUID = -325997914420452006L;

    private Comment comment;

    private List<Comment> children;

}
