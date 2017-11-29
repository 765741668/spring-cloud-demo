package com.hooray.demo.config;

import com.hooray.cache.api.spi.common.CacheManagerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;

import javax.annotation.PostConstruct;
import java.util.Properties;

/**
 * desc: 描述
 *
 * @auth: Orochi-Yzh
 * @dateTime: 2017/11/6 11:33
 */
//@Configuration
//@EnableCaching
public class RedisConfig extends CachingConfigurerSupport{

    @Value("${spring.redis.cluster-modle}")
    private String clusterModle;
    @Value("${spring.redis.database}")
    private int database;
    @Value("${spring.redis.nodes}")
    private String nodes;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.config.maxTotal}")
    private int maxTotal;
    @Value("${spring.redis.config.maxIdle}")
    private int maxIdle;
    @Value("${spring.redis.config.minIdle}")
    private int minIdle;
    @Value("${spring.redis.config.maxWaitMillis}")
    private int maxWaitMillis;
    @Value("${spring.redis.config.testOnBorrow}")
    private boolean testOnBorrow;

    @PostConstruct
    private void initCacheManager() {
        Properties prop = new Properties();
        //部署方式，单机：single,集群：cluster
        prop.setProperty("redis.cluster-model", clusterModle);
        // 格式是ip:port, 多个逗号隔开，每一项都加双引号
        prop.setProperty("redis.nodes", nodes);
        prop.setProperty("redis.password", password);
        //最大连接数
        prop.setProperty("redis.maxTotal", String.valueOf(maxTotal));
        //最小空闲连接数
        prop.setProperty("redis.minIdle", String.valueOf(minIdle));
        //最大空闲连接数
        prop.setProperty("redis.maxIdle", String.valueOf(maxIdle));
        //连接数达上限时，等待获取连接时间
        prop.setProperty("redis.maxWaitMillis", String.valueOf(maxWaitMillis));
        prop.setProperty("redis.testOnBorrow", String.valueOf(testOnBorrow));
        CacheManagerFactory.create().init(prop);
    }
}
