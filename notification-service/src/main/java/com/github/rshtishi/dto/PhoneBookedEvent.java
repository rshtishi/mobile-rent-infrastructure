package com.github.rshtishi.dto;

public record PhoneBookedEvent(long phoneId, String phoneName, String bookedBy) {
}
