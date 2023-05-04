package com.example.carmixserver.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"brend","model","series"}))
public class Automobile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String brend;
    @Column(nullable = false)
    private String model;
    private String series;
    @OneToMany(cascade = CascadeType.ALL)
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    private List<Car> car = new ArrayList<>();
    @ManyToMany(mappedBy = "automobiles", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    private List<Job> jobs = new ArrayList<>();
}
