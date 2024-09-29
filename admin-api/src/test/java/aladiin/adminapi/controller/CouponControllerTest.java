package aladiin.adminapi.controller;

import aladiin.adminapi.config.TestContainers;
import aladiin.core.domain.enums.DiscountType;
import aladiin.core.request.CouponRegisterRequest;
import aladiin.core.request.EventRegisterRequest;
import aladiin.core.request.SignUpRequest;
import aladiin.core.response.CommonResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CouponControllerTest extends TestContainers {

    @LocalServerPort
    private String port;
    private final String host = "http://localhost:";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("회원가입시 200 OK를 응답한다")
    void test1() {
        // given
        SignUpRequest signUpRequest = SignUpRequest.from("test");
        String url = host + port + "/v1/members/signup";

        // when
        HttpStatusCode responseCode = restTemplate.postForEntity(url, signUpRequest, CommonResponse.class).getStatusCode();

        // then
        Assertions.assertThat(responseCode).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("쿠폰 정상 등록시 200 OK를 응답한다")
    void test2() {
        // given
        CouponRegisterRequest couponRegisterRequest = CouponRegisterRequest.of(
                "testCoupon"
                , DiscountType.RATIO.getType()
                , 50
                , LocalDateTime.now().plusYears(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        String url = host + port + "/v1/coupons/register";

        // when
        HttpStatusCode responseCode = restTemplate.postForEntity(url, couponRegisterRequest, CommonResponse.class).getStatusCode();

        // then
        assertThat(responseCode).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("이벤트 정상 등록시 200 OK를 응답한다")
    void test3() {
        // given
        EventRegisterRequest eventRegisterRequest = EventRegisterRequest.of(
                1L
                , 10
                , LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                , LocalDateTime.now().plusYears(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
        );
        String url = host + port + "/v1/events/register";

        // when
        HttpStatusCode responseCode = restTemplate.postForEntity(url, eventRegisterRequest, CommonResponse.class).getStatusCode();

        // then
        assertThat(responseCode).isEqualTo(HttpStatus.OK);
    }
}
