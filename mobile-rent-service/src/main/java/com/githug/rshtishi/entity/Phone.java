package com.githug.rshtishi.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Phone {
    private Long id;
    private String name;
    private boolean availability;
    private String bookedBy;
    private LocalDateTime bookedAt;
}