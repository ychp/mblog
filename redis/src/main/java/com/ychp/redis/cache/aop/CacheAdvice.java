package com.ychp.redis.cache.aop;

import com.ychp.redis.cache.annontation.DataCache;
import com.ychp.redis.cache.exception.CacheException;
import com.ychp.redis.cache.manager.CacheManager;
import com.ychp.redis.cache.utils.CacheAdviceUtils;
import lombok.NoArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author yingchengpeng
 * @date 2018/8/14
 */
@Aspect
@NoArgsConstructor
public class CacheAdvice {

	@Autowired
	private CacheManager cacheManager;

	@Pointcut(value = "@annotation(com.ychp.redis.cache.annontation.DataCache)")
	public void pointCut() {
	}

	@Around(value = "pointCut()")
	public Object before(ProceedingJoinPoint joinPoint) throws Throwable {
		Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
		DataCache dataCache = method.getAnnotation(DataCache.class);

		Map<String, Object> datas = CacheAdviceUtils.getRequestDatas(joinPoint);
		String key = CacheAdviceUtils.getCacheKey(dataCache.value(), datas);

		if(StringUtils.isEmpty(key)) {
			throw new CacheException("cache.key.empty");
		}

		Object returnObject = getCacheData(key, method);

		if(returnObject != null) {
			return returnObject;
		}

		returnObject = joinPoint.proceed();

		saveCache(returnObject, key, dataCache.expireTime());

		return returnObject;
	}

	private Object getCacheData(String key, Method method) {
		Class<?> clazz = method.getReturnType();
		return cacheManager.get(key, clazz);
	}

	private void saveCache(Object returnObject, String key, int expireTime) {
		if(returnObject == null) {
			return;
		}
		cacheManager.save(key, returnObject, expireTime);
	}
}
