package com.aladiin.global.dao.redis;

import com.aladiin.domain.event.domain.vo.EventRegisterVO;
import com.aladiin.domain.event.repository.EventRepository;
import com.aladiin.global.dao.redis.operation.RedisOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RedisRepository {

    private final EventRepository eventRepository;
    private final RedisTransaction redisTransaction;
    private final RedisTemplate redisTemplate;
    private final RedisOperation redisOperation;


    public void saveEvent(EventRegisterVO from) {

    }
}
