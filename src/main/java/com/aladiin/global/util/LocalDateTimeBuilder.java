package com.aladiin.global.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

public class LocalDateTimeBuilder {

    public static LocalDateTime from(String YYYYMMDDHHmm) throws DateTimeParseException {
        LocalDateTime localDateTime = null;
        String format = "yyyyMMddHHmm";
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(format);
        try {
            localDateTime = LocalDateTime.parse(YYYYMMDDHHmm, dateTimeFormat);
        } catch (DateTimeParseException e) {
            throw e;
        }

        return localDateTime;
    }
}
