package com.ychp.session.manager;

import com.ychp.redis.dao.JedisTemplate;
import com.ychp.session.constant.SessionConstants;
import com.ychp.session.utils.SerializableUtils;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * @author yingchengpeng
 * @date 2018/8/16
 */
public class SessionManager {

	private final JedisTemplate jedisTemplate;

	public SessionManager(JedisTemplate jedisTemplate) {
		this.jedisTemplate = jedisTemplate;
	}

	public void save(String id, Map<String, Object> attributes, int expireTime) {
		byte[] keyBytes = getKeyBytes(id);
		byte[] valueBytes = SerializableUtils.serialize(attributes);
		jedisTemplate.excute(jedis -> jedis.setex(keyBytes, expireTime, valueBytes));
	}

	public Map<String, Object> get(String id) {
		byte[] keyBytes = getKeyBytes(id);
		byte[] valueBytes = jedisTemplate.excute(jedis -> jedis.get(keyBytes));
		return (Map<String, Object>) SerializableUtils.deserialize(valueBytes);
	}

	public void refresh(String id, int expireTime) {
		byte[] keyBytes = getKeyBytes(id);
		jedisTemplate.excute(jedis -> jedis.expire(keyBytes, expireTime));
	}

	public void remove(String id) {
		byte[] keyBytes = getKeyBytes(id);
		jedisTemplate.excute(jedis -> jedis.del(keyBytes));
	}
	private byte[] getKeyBytes(String id) {
		return (SessionConstants.SESSION_PRE + id).getBytes(Charset.forName("UTF-8"));
	}
}
