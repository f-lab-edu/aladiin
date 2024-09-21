package aladiin.couponconsumer.service;

import aladiin.core.domain.entity.EventJoinMember;
import aladiin.couponconsumer.domain.EventParticipants;
import aladiin.couponconsumer.error.DuplicateJoinException;
import org.assertj.core.api.Assertions;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EventServiceTest {

    private EventService eventService;

    @BeforeAll
    void init() {

        EventJoinMember eventJoinMember = getEventJoinMember();

        EventParticipants eventParticipants = new EventParticipants();
        eventParticipants.addMember(eventJoinMember);

        eventService = new EventService(eventParticipants);
    }

    @Test
    @DisplayName("이벤트 중복 참여시 DuplicateJoinException 이 발생한다")
    void test1() {
        EventJoinMember eventJoinMember = getEventJoinMember();

        Assertions.assertThatThrownBy(() -> eventService.checkDuplicateJoin(eventJoinMember))
                .isInstanceOf(DuplicateJoinException.class);
    }

    @NotNull
    private static EventJoinMember getEventJoinMember() {
        String eventDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        Long memberId = 1L;
        Long eventId = 1L;
        EventJoinMember eventJoinMember = EventJoinMember.of(eventId, eventDate, memberId);
        return eventJoinMember;
    }
}
