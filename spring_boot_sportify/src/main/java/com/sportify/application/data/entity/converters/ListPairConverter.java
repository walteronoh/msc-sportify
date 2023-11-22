package com.sportify.application.data.entity.converters;


import java.util.Arrays;
import java.util.List;

import com.sportify.application.data.utilities.Pair;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


@Converter(autoApply = true)
public class ListPairConverter implements AttributeConverter<List<Pair<Integer, Integer>>, String> {
    @Override
    public String convertToDatabaseColumn(List<Pair<Integer, Integer>> pairs) {
        StringBuilder res = new StringBuilder();
        for(Pair<Integer, Integer> p : pairs) {
            res.append(p.toString());
            res.append(" ");
        }
        return res.toString();
    }

    @Override
    public List<Pair<Integer, Integer>> convertToEntityAttribute(String dbData) {
        String[] tokens = dbData.split(" ");
        return Arrays.stream(tokens)
                .map(ListPairConverter::parse)
                .toList();
    }

    public static Pair<Integer, Integer> parse(String token) {
        StringBuilder firstString = new StringBuilder();
        StringBuilder secondString = new StringBuilder();

        if(token.charAt(0) == '(') {
            token = token.substring(1);
            while(Character.isDigit(token.charAt(0))) {
                firstString.append(token.charAt(0));
                token = token.substring(1);
            }
            if (token.charAt(0) == ',') {
                token = token.substring(1);
                while(Character.isDigit(token.charAt(0))) {
                    secondString.append(token.charAt(0));
                    token = token.substring(1);
                }
            }
        }
        Integer first = Integer.parseInt(firstString.toString());
        Integer second = Integer.parseInt(secondString.toString());

        return new Pair<>(first,second);
    }
}
