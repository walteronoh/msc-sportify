package com.sportify.application.data.entity.converters;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RowListConverter implements AttributeConverter<List<Integer>, String> {
    @Override
    public String convertToDatabaseColumn(List<Integer> rows) {
        StringBuilder res = new StringBuilder();
        for(Integer i: rows) {
            res.append(i);
            res.append(" ");
        }
        return res.toString();
    }

    @Override
    public List<Integer> convertToEntityAttribute(String dbData) {
        String[] tokens = dbData.split(" ");
        return Arrays.stream(tokens)
                .map(Integer::parseInt)
                .toList();
    }
}
