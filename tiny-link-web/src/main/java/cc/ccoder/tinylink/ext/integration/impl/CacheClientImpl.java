package cc.ccoder.tinylink.ext.integration.impl;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import cc.ccoder.tinylink.ext.integration.CacheClient;

/**
 * <p>
 * </p>
 *
 * @author chencong
 * @email cong.chen@payby.com | cong.ccoder@gmail.com
 * @date CacheClientImpl.java v1.0 2021/6/29 12:33
 */
@Component
public class CacheClientImpl implements CacheClient {

    Cache<Object, Object> localCache = CacheBuilder.newBuilder().expireAfterAccess(60, TimeUnit.SECONDS).build();

    @Override
    public void put(Object key, Object value) {
        localCache.put(key, value);
    }

    @Override
    public Object get(Object key) {
        return localCache.getIfPresent(key);
    }

    @Override
    public Object get(Object key, Callable<?> value) throws ExecutionException {
        return localCache.get(key, value);
    }
}
