package aladiin.core.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SignUpRequest {

    private String memberName;

    public static SignUpRequest from(String memberName) {
        return new SignUpRequest(memberName);
    }
}
