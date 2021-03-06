package com.ychp.mblog.web.controller.bean;

import com.ychp.common.model.paging.Paging;
import com.ychp.user.api.bean.response.FollowRelationVO;
import com.ychp.user.model.FollowRelation;
import com.ychp.user.model.User;
import com.ychp.user.model.UserProfile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yingchengpeng
 */
public class FollowRelationConverter {


    public static Paging<FollowRelationVO> get(Paging<FollowRelation> paging,
                                        List<User> users, List<UserProfile> userProfiles,
                                        Boolean isFollowee) {
        Map<Long, User> userById = users.stream().collect(Collectors.toMap(User::getId, user -> user));
        Map<Long, UserProfile> userProfileById = userProfiles.stream()
                .collect(Collectors.toMap(UserProfile::getUserId, userProfile -> userProfile));
        List<FollowRelationVO> followRelationVOS = paging.getDatas().stream()
                .map(followRelation -> {
                    Long id = isFollowee ? followRelation.getFolloweeId() : followRelation.getFollowerId();
                    User user = userById.get(id);
                    UserProfile userProfile = userProfileById.get(id);
                    FollowRelationVO vo = new FollowRelationVO();
                    vo.setFolloweeId(followRelation.getFolloweeId());
                    vo.setFollowerId(followRelation.getFollowerId());
                    vo.setIsSubscribe(followRelation.getIsSubscribe());
                    vo.setName(user.getName());
                    vo.setAvatar(userProfile.getAvatar());
                    return vo;
                }).collect(Collectors.toList());
        return new Paging<>(paging.getTotal(), followRelationVOS);
    }

}
