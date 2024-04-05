package com.cardgame.stareindespair.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class LongToStringSerializer extends StdSerializer<Long> {
    public LongToStringSerializer() {
        super(Long.class);
    }

    @Override
    public void serialize(Long value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (value != null) {
            // 将Long类型的值转换成字符串，并写入JSON
            gen.writeString(value.toString());
        }
    }
}
