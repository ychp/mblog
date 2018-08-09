package com.ychp.common.model.paging;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Desc:
 * Author: <a href="ychp@terminus.io">应程鹏</a>
 * Date: 2017/8/27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paging<T> implements Serializable {

    private static final long serialVersionUID = 6012920402624009621L;

    private Long total;
    private List<T> datas;

    public Boolean isEmpty() {
        return datas == null || datas.isEmpty();
    }

    public static Paging empty() {
        return new Paging<>(0L, Lists.newArrayListWithCapacity(0));
    }
}
