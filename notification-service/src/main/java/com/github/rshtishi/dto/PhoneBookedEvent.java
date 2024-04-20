package com.github.rshtishi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneBookedEvent {

    private long phoneId;
    private String phoneName;
    private String bookedBy;
}
