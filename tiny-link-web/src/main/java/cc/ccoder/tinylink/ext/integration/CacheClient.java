package cc.ccoder.tinylink.ext.integration;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * <p>
 * </p>
 *
 * @author chencong
 * @email cong.chen@payby.com | cong.ccoder@gmail.com
 * @date CacheClient.java v1.0 2021/6/29 12:33
 */
public interface CacheClient {

    void put(Object key, Object value);

    Object get(Object key);

    Object get(Object key, Callable<?> value) throws ExecutionException;
}
