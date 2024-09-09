package aladiin.couponapi.model.dto;

import aladiin.couponapi.model.enums.EventJoinStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class JoinStatusResponse {

    private EventJoinStatus joinStatus;
}
