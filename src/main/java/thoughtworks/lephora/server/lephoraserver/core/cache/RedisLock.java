package thoughtworks.lephora.server.lephoraserver.core.cache;

import io.micrometer.common.util.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.OffsetTime;

import static java.lang.Boolean.TRUE;

@Component
public class RedisLock {

    private final StringRedisTemplate redisTemplate;

    public RedisLock(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean lock(String key, OffsetTime timeStamp) {
        if (TRUE.equals(redisTemplate.opsForValue().setIfAbsent(key, timeStamp.toString()))) {
            return true;
        }
        String time = redisTemplate.opsForValue().get(key);
        if (!StringUtils.isEmpty(time) && OffsetTime.parse(time).isBefore(OffsetTime.now())) {
            String beforeTime = redisTemplate.opsForValue().getAndSet(key, timeStamp.toString());
            return !StringUtils.isEmpty(beforeTime) && time.equals(beforeTime);
        }
        return false;
    }

    public void release(String key) {
        if (!StringUtils.isEmpty(redisTemplate.opsForValue().get(key))) {
            redisTemplate.opsForValue().getOperations().delete(key);
        }
    }
}
