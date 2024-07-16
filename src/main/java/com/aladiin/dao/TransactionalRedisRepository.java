package com.aladiin.dao;

import com.aladiin.dao.redis.RedisTransaction;
import com.aladiin.dao.redis.operation.EventJoinRedisOperation;
import com.aladiin.model.vo.EventJoinVO;
import com.aladiin.model.dto.EventJoinResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TransactionalRedisRepository {

    private final RedisTransaction redisTransaction;
    private final EventJoinRedisOperation eventJoinRedisOperation;

    public EventJoinResultDTO countAndAdd(EventJoinVO eventJoinVO) throws DataAccessException {
        List<Long> eventJoinResultList = (List<Long>) redisTransaction.execute(eventJoinRedisOperation, eventJoinVO);
        if (eventJoinResultList.size() != 2){
            // throw Something
        }
        return EventJoinResultDTO.of(eventJoinResultList.get(0), eventJoinResultList.get(1));
    }
}
