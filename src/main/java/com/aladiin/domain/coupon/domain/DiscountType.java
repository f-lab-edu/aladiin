package com.aladiin.domain.coupon.domain;

import org.apache.catalina.util.StringUtil;
import org.springframework.util.StringUtils;

import java.util.Arrays;

public enum DiscountType {
    VALUE("value"), RATIO("ratio"),
    ;

    private final String type;

    DiscountType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static String[] getTypes() {
        return Arrays.stream(values()).map(DiscountType::getType).toArray(String[]::new);
    }

    public static DiscountType getDiscountType(String type) {
        return DiscountType.valueOf(type.toUpperCase());
    }

}
