package com.example.carmixserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "automobile_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"car"})
    private Automobile automobile;
    private String vin;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Customer> customers = new ArrayList<>();

    @Temporal(TemporalType.DATE)
    private Date manufactureDate;

    private String licensePlate;

    private int millage;

    @Temporal(TemporalType.DATE)
    private Date technicalMaintence;

    private String sts;



    public Car() {};
}
