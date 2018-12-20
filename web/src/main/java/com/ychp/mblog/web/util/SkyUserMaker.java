package com.ychp.mblog.web.util;

import com.ychp.user.model.User;
import com.ychp.common.model.SkyUser;
import org.dozer.DozerBeanMapper;

/**
 * Desc:
 * @author yingchengpeng
 * @date 2016/08/27
 */
public class SkyUserMaker {

    private static DozerBeanMapper dozer = new DozerBeanMapper();

    public static SkyUser make(User user) {
        SkyUser skyUser = new SkyUser();
        dozer.map(user, skyUser);
        return skyUser;
    }
}
