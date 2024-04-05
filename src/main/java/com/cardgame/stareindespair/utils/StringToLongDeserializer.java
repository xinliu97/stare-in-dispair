package com.cardgame.stareindespair.utils;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class StringToLongDeserializer extends JsonDeserializer<Long> {
    @Override
    public Long deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String phoneNumberStr = p.getText();
        if (phoneNumberStr != null && !phoneNumberStr.isEmpty()) {
            try {
                return Long.parseLong(phoneNumberStr);
            } catch (NumberFormatException e) {
                throw new IOException("Unable to deserialize phone number", e);
            }
        }
        return null;
    }
}
