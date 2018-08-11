package com.ychp.blog.impl.server.repository;

import com.google.common.collect.ImmutableMap;
import com.ychp.blog.model.LikeLog;
import com.ychp.mybatis.repository.BaseRepository;
import org.springframework.stereotype.Repository;

/**
* @author yingchengpeng
* @date: 2018/08/11
*/
@Repository
public class LikeLogRepository extends BaseRepository<LikeLog, Long> {

	public LikeLog findByAimAndIp(Long aimId, Integer type, String ip) {
		return getSqlSession().selectOne(sqlId("findByAimAndIp"),
				ImmutableMap.of("aimId", aimId, "type", type, "ip", ip));
	}
}