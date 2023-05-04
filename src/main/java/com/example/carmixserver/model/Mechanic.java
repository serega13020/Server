package com.example.carmixserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Mechanic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    private String patronymic;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String level;
    @NotNull
    private String passport;
    @NotNull
    private String snils;
    private String address;
    private String email;
    @NotNull
    private String hiredate;
    private String firedate;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();
}
