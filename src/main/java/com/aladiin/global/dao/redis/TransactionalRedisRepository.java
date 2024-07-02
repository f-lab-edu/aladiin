package com.aladiin.global.dao.redis;

import com.aladiin.domain.event.domain.vo.EventJoinVO;
import com.aladiin.domain.event.dto.EventJoinResultDTO;
import com.aladiin.global.dao.redis.operation.RedisOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TransactionalRedisRepository {

    private final RedisTemplate redisTemplate;
    private final RedisOperation redisOperation;
    private final RedisTransaction redisTransaction;


    public EventJoinResultDTO countAndAdd(EventJoinVO eventJoinVO) throws DataAccessException {
        List<Long> eventJoinResultList = (List<Long>) redisTransaction.execute(redisTemplate, redisOperation, eventJoinVO);
        if (eventJoinResultList.size() != 2){
            // throw Something
        }
        return EventJoinResultDTO.of(eventJoinResultList.get(0), eventJoinResultList.get(1));
    }
}
