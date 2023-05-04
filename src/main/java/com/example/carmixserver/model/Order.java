package com.example.carmixserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL)
    private Car car;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"automobiles"})
    private List<Job> jobs = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "order_mechanic",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "mechanic_id"))
    private List<Mechanic> mechanics = new ArrayList<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date workStartDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date workFinishDateTime;

    private Integer serviceId;
    private String box;

    @Temporal(TemporalType.TIMESTAMP)
    private Date start_Task;
    @Temporal(TemporalType.TIMESTAMP)
    private Date finish_Task;

    private String mainColorTaskStr;
    private String addColorTaskStr;

    private Boolean planed;


}
