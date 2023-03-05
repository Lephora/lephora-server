package thoughtworks.lephora.server.lephoraserver.core.cache;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import thoughtworks.lephora.server.lephoraserver.core.exception.RedisLockException;

import java.time.OffsetTime;
import java.util.Objects;

@Component
@Aspect
public class RedisConfig {

    private final RedisLock redisLock;

    private final Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    public RedisConfig(RedisLock redisLock) {
        this.redisLock = redisLock;
    }

    @Pointcut("@annotation(thoughtworks.lephora.server.lephoraserver.core.cache.RedisCache)")
    public void cutPoint() {
    }

    @Around("cutPoint()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        final String token = getToken();
        var expireTime = OffsetTime.now().plusMinutes(30L);
        if (redisLock.lock(token, expireTime)) {
            logger.info("get redis lock succeed");
            return joinPoint.proceed();
        }
        logger.error("get redis lock failed");
        throw new RedisLockException("failed to get redis lock");
    }

    @After("cutPoint()")
    public void afterReturning() {
        logger.info("release redis lock");
        redisLock.release(getToken());
    }

    private static String getToken() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        return request.getHeader("token");
    }
}
