package com.example.carmixserver.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Filter {
    private String fullNameMechanic;
    private String fullNameClient;
    private Integer numberOfBox;
    private Calendar beginDate;
    private Calendar endDate;
    // получить строку с датой beginDate.getTime().toInstant().toString()
    private String licensePlate;
}
