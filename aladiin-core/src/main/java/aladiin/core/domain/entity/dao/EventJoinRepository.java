package aladiin.core.domain.entity.dao;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EventJoinRepository {

    private final RedissonClient redissonClient;

    public boolean findMember(String key) {
        return redissonClient.getBucket(key).isExists();
    }
}
