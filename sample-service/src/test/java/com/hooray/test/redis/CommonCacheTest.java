package com.hooray.test.redis;

import com.hooray.cache.api.spi.common.CacheManager;
import com.hooray.cache.api.spi.common.CacheManagerFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * desc: 描述
 *
 * @auth: Orochi-Yzh
 * @dateTime: 2017/11/6 14:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonCacheTest {
    private final Logger logger = LoggerFactory.getLogger(CommonCacheTest.class);
    private final CacheManager redis = CacheManagerFactory.create();

    @Test
    public void testCache() {
        redis.hset("test-key","t1","msg1");
        logger.info("插入：{}",redis.hget("test-key","t1",String.class));

        redis.hset("test-key","t1","msg11");
        logger.info("修改：{}",redis.hget("test-key","t1",String.class));

//        redis.hdel("test-key","t1");
        logger.info("删除：{}",redis.hget("test-key","t1",String.class));
    }

    @Test
    public void testSetBitMap() {
        //true:插入
        redis.setbit("test-bitmap",1,true);
    }

    @Test
    public void testSetBitMap2() {
        //false:删除
        redis.setbit("test-bitmap",1,false);
    }


}
