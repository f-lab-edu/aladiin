package aladiin.couponapi.controller;

import aladiin.core.response.CommonResponse;
import aladiin.couponapi.model.enums.EventJoinStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RequiredArgsConstructor
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EventControllerTest extends MySQLTestContainer {

    @LocalServerPort
    private String port;
    private RestClient restClient;
    private String eventDate;
    private Long couponId;
    private Long memberId;

    @BeforeAll
    void init() {
        restClient = RestClient.builder()
                .baseUrl("http://localhost:" + port)
                .build();

        eventDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        memberId = 1L;
        couponId = 1L;
    }

    @Test
    @DisplayName("이벤트에 참여하지 않은 사용자가 이벤트 참여상태를 조회하면 NOT_JOINED 메시지를 응답한다")
    void test1() {

        CommonResponse response = restClient.get()
                .uri("/v1/events/" + couponId + "/status?eventdate=" + eventDate + "&memberid=" + memberId)
                .retrieve()
                .body(CommonResponse.class);
        Map<String, Object> data = (Map) response.getData();
        String eventJoinStatus = String.valueOf(data.get("joinStatus"));

        assertThat(eventJoinStatus).isEqualTo(EventJoinStatus.NOT_JOINED.toString());
    }

    @Test
    @DisplayName("TestContainers 테스트")
    void test2() {
        log.info(mySQLContainer.getDatabaseName());
    }
}
