package aladiin.couponapi.controller;

import aladiin.core.domain.entity.EventJoinMember;
import aladiin.core.response.CommonResponse;
import aladiin.couponapi.config.TestContainers;
import aladiin.couponapi.model.enums.EventJoinStatus;
import org.junit.jupiter.api.*;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RBucket;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EventControllerTest extends TestContainers {

    @LocalServerPort
    private String port;
    private final String host = "http://localhost:";
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @BeforeEach
    void init() {
        initEventJoinData();
    }

    @AfterEach
    void destroy() {
        destroyEventJoinData();
    }

    private void initEventJoinData() {
        String eventDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        Long memberId = 1L;
        Long eventId = 1L;

        RBucket<Object> bucket = redissonClient.getBucket(EventJoinMember.of(eventId, eventDate, memberId).getKey());
        bucket.set(1L);
    }

    private void destroyEventJoinData() {
        String eventDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        Long memberId = 1L;
        Long eventId = 1L;

        RBucket<Object> bucket = redissonClient.getBucket(EventJoinMember.of(eventId, eventDate, memberId).getKey());
        bucket.delete();
    }

    @Test
    @DisplayName("이벤트에 참여하지 않은 사용자가 이벤트 참여상태를 조회하면 NOT_JOINED 메시지를 응답한다")
    void test1() {
        // given
        String eventDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        Long memberId = 2L;
        Long eventId = 1L;
        String url = host + port + "/v1/events/" + eventId + "/status?eventdate=" + eventDate + "&memberid=" + memberId;

        // when
        Map<String, Object> data = (Map) restTemplate.getForEntity(url, CommonResponse.class).getBody().getData();
        String eventJoinStatus = String.valueOf(data.get("joinStatus"));

        // then
        assertThat(eventJoinStatus).isEqualTo(EventJoinStatus.NOT_JOINED.toString());
    }

    @Test
    @DisplayName("이벤트에 참여한 사용자가 이벤트 참여상태를 조회하면 JOINED 메시지를 응답한다")
    void test2() {
        // given
        String eventDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        Long memberId = 1L;
        Long eventId = 1L;
        String url = host + port + "/v1/events/" + eventId + "/status?eventdate=" + eventDate + "&memberid=" + memberId;

        // when
        Map<String, Object> data = (Map) restTemplate.getForEntity(url, CommonResponse.class).getBody().getData();
        String eventJoinStatus = String.valueOf(data.get("joinStatus"));

        // then
        assertThat(eventJoinStatus).isEqualTo(EventJoinStatus.JOINED.toString());
    }
}
