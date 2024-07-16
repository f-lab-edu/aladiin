package com.aladiin.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class EventJoinResultDTO {

    private Long currentParticipantNumber;
    private Long isDuplicateJoin;

    public static EventJoinResultDTO of(Long currentParticipantNumber, Long isDuplicateJoin) {
        return new EventJoinResultDTO(currentParticipantNumber, isDuplicateJoin);
    }
}
