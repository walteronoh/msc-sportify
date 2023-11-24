package com.sportify.application.data.entity.converters;

import java.util.ArrayList;
import java.util.Arrays;

import com.sportify.application.data.entity.enums.BookingStatus;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class BookingStatusArrayConverter implements AttributeConverter<BookingStatus[][], String>{

    @Override
    public String convertToDatabaseColumn(BookingStatus[][] seats) {
        StringBuilder result = new StringBuilder();
        for(BookingStatus[] row : seats){
            for(BookingStatus seat: row) {
                result.append(seat.toToken());
                result.append(",");
            }
            result.append("\n");
        }
        return result.toString();
    }

    @Override
    public BookingStatus[][] convertToEntityAttribute(String dbString) {
        ArrayList<BookingStatus[]> result = new ArrayList<>();
        String rows[] = dbString.split("\n");
        for(String line : rows) {
            String tokens[] = line.split(",");
            BookingStatus[] seats = Arrays.stream(tokens)
                            .map(s -> BookingStatus.fromToken(s.charAt(1)))
                            .toArray(BookingStatus[]::new);
            result.add(seats);
        }
        return result.toArray(BookingStatus[][]::new);
    }
    
}
