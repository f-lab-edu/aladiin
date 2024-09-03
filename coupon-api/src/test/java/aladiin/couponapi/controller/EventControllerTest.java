package aladiin.couponapi.controller;

import aladiin.core.response.CommonResponse;
import aladiin.core.request.CouponRegisterRequest;
import aladiin.core.request.EventRegisterRequest;
import aladiin.core.request.SignUpRequest;
import aladiin.couponapi.model.enums.EventJoinStatus;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RequiredArgsConstructor
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EventControllerTest {

    @LocalServerPort
    private String port;
    private String url = "http://localhost";
    private RestClient restClient;
    private String eventDate;
    private Long couponId;
    private Long memberId;

    @BeforeAll
    void init() {
        String baseUrl = url + ":" + port;
        restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .build();

        LocalDateTime today = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT);
        eventDate = today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String eventStartDateTime = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String eventEndDateTime = today.plusMonths(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        couponId = 1L;
        memberId = 1L;

        CommonResponse memberSignUpResponse = RestClient.builder().baseUrl(url + ":8080").build()
                .post()
                .uri("/v1/members/signup")
                .body(SignUpRequest.from("testmember"))
                .retrieve()
                .body(CommonResponse.class);

        CommonResponse couponRegisterResponse = RestClient.builder().baseUrl(url + ":8080").build()
                .post()
                .uri("/v1/coupons/register")
                .body(CouponRegisterRequest.of("testcoupon", "ratio", 50, eventEndDateTime))
                .retrieve()
                .body(CommonResponse.class);

        CommonResponse eventRegisterResponse = RestClient.builder().baseUrl(url + ":8080").build()
                .post()
                .uri("/v1/events/register")
                .body(EventRegisterRequest.of(couponId, 1, eventStartDateTime, eventEndDateTime))
                .retrieve()
                .body(CommonResponse.class);
    }

    @Test
    @DisplayName("이벤트에 참여하지 않은 사용자가 이벤트 참여상태를 조회하면 NOT_JOINED 메시지를 응답한다")
    void test1(){

        CommonResponse response = restClient.get()
                .uri("/v1/events/" + couponId + "/status?eventdate=" + eventDate + "&memberid=" + memberId)
                .retrieve()
                .body(CommonResponse.class);
        Map<String, Object> data = (Map) response.getData();
        String eventJoinStatus = String.valueOf(data.get("joinStatus"));

        assertThat(eventJoinStatus).isEqualTo(EventJoinStatus.NOT_JOINED.toString());
    }

}
