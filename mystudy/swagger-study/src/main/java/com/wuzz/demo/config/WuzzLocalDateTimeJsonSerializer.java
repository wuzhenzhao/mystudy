package com.wuzz.demo.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class WuzzLocalDateTimeJsonSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        // 将当前时间转为时间戳
        long second = localDateTime.toEpochSecond(ZoneOffset.ofHours(8));
        jsonGenerator.writeString(String.valueOf(second));
    }
}
