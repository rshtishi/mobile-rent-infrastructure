package com.githug.rshtishi.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Phone {
    @Id
    private Long id;
    private String name;
    private boolean availability;
    private String bookedBy;
    private LocalDateTime bookedAt;
}