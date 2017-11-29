package com.hooray.common.redis;

import java.util.List;

/**
 * 封装Redis方法的公共接口类
 * 
 * @author yangfeng
 * @date 2017年11月2日 上午11:36:02	
 * Email: yangfeng@hooray.cn
 */
public interface IBaseRedisService {

	public boolean set(String key, String value);
	
	public boolean set(String key, String value, Long expire);
    
    public String get(String key);  
      
    public boolean expire(String key, long expire);

    public <T> boolean setObject(String key, T entity);

    public <T> boolean setObject(String key, T entity, Long expire);

    public <T> T getObject(String key, Class<T> clz);

    public <T> boolean setList(String key, List<T> list);

    public <T> List<T> getList(String key, Class<T> clz);

    public long lpush(String key, Object obj);

    public long rpush(String key, Object obj);
      
    public String lpop(String key);

	public Object hGetAll(String key);
    
}
