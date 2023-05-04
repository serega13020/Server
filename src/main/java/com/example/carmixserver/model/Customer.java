package com.example.carmixserver.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String patronymic;
    @NotNull
    private String phoneNumber;
    @NotNull
    private int roleId;
    private String email;
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @NotNull
    private int serviceId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "customer_car",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id"))
    List<Car> cars = new ArrayList<>();


    public Customer() {};
}
