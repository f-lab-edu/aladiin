package com.aladiin.global.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor(staticName = "of")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<D> {

    private final String resultCode;
    private final String message;
    private final D data;

    public static <D> CommonResponse<D> ofSuccess(){
        return new CommonResponse<D>("200", null, null);
    }

    public static <D> CommonResponse<D> ofSuccess(String message, D data){
        return new CommonResponse<D>("200", message, data);
    }

    public static <D> CommonResponse<D> ofFail(String errorCode, String message, D data){
        return new CommonResponse<D>(errorCode, message, data);
    }
}
