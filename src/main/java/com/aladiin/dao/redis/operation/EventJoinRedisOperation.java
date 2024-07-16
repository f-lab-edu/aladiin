package com.aladiin.dao.redis.operation;

import com.aladiin.model.vo.EventJoinVO;
import com.aladiin.dao.redis.operation.RedisOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Slf4j
@Component
public class EventJoinRedisOperation implements RedisOperation<EventJoinVO> {

    @Override
    public Long count(RedisOperations<String, Object> operations, EventJoinVO vo) {
        String key = vo.getKey();
        Long size = operations.opsForSet().size(key);
        log.debug("[EventRedisOperation] [count] key ::: {}, size ::: {}", key, size);
        return size;
    }

    @Override
    public Long add(RedisOperations<String, Object> operations, EventJoinVO vo) {
        String key = vo.getKey();
        String value = this.generateValue(vo);
        Long result = operations.opsForSet().add(key, value);
        log.debug(
                "[EventRedisOperation] [add] key ::: {}, value ::: {}, result ::: {}", key, value, result);
        return result;
    }

    @Override
    public Long remove(RedisOperations<String, Object> operations, EventJoinVO vo) {
        String key = vo.getKey();
        String value = this.generateValue(vo);
        Long result = operations.opsForSet().remove(key, value);
        log.debug(
                "[EventRedisOperation] [remove] key ::: {}, value ::: {}, result ::: {}",
                key,
                value,
                result);
        return result;
    }

    @Override
    public Boolean delete(RedisOperations<String, Object> operations, EventJoinVO vo) {
        String key = vo.getKey();
        Boolean result = operations.delete(key);
        log.debug("[EventRedisOperation] [delete] key ::: {}, result ::: {}", key, result);
        return result;
    }

    @Override
    public Boolean expire(
            RedisOperations<String, Object> operations, EventJoinVO vo, Duration duration) {
        String key = vo.getKey();
        Boolean result = operations.expire(key, duration);
        log.debug(
                "[EventRedisOperation] [expire] key ::: {}, expire ::: {}, result ::: {}",
                key,
                duration,
                result);
        return result;
    }

    @Override
    public String generateValue(EventJoinVO vo) {
        return String.valueOf(vo.getMemberId());
    }

    @Override
    public void execute(RedisOperations<String, Object> operations, EventJoinVO vo) {
        this.count(operations, vo);
        this.add(operations, vo);
    }
}