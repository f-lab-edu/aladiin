package aladiin.core.domain.entity.dao;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EventJoinRepository {

    private final RedissonClient redissonClient;

    public boolean findMember(String key, Long value) {
        return redissonClient.getSet(key).contains(value);
    }
}
