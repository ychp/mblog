package com.ychp.mblog.web.cache;

import com.ychp.cache.annontation.DataCache;
import com.ychp.common.model.SkyUser;
import com.ychp.mblog.web.util.SkyUserMaker;
import com.ychp.user.service.UserReadService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author yingchengpeng
 * @date 2018-09-07
 */
@Component
public class UserCache {

    @Autowired
    private UserReadService userReadService;

    @DataCache("user:{{id}}")
    public SkyUser findDetail(@ApiParam(example = "1") @PathVariable Long id) {
        return SkyUserMaker.make(userReadService.findById(id));
    }
}
