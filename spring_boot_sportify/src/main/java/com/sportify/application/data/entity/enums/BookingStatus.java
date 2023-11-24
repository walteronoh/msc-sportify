package com.sportify.application.data.entity.enums;

public enum BookingStatus {
    Available, Reserved;

    public char toToken() {
        switch (this) {
            case Available: return 'A';
            case Reserved: return 'R';
        
        }
        throw new RuntimeException("Token for " + this.toString() + " is not defined.");
    }
    public static BookingStatus fromToken(char c) {
        switch (c) {
            case 'A': return Available;
            case 'R': return Reserved;
        }
        throw new RuntimeException("Char " + c + " is not a valid token.");
    }
}
