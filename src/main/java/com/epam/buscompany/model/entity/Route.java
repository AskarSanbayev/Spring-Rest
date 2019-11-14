package com.epam.buscompany.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"busList","stages","towns"})
@Entity
@ToString(exclude = {"busList","stages","towns"})
@Table(name = "route", schema = "company")
public class Route implements Serializable {

    @Id
    @SequenceGenerator(name="seq",sequenceName="route_id",allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    private int id;

    @Min(value = 1, message = "number must be more than 0")
    @Column(name = "route_number", unique = true)
    private int number;

    @Min(value = 1, message = "number must be more than 0")
    @Column(name = "passenger_average")
    private int passengerAverage;

    public Route(@Min(value = 1, message = "number must be more than 0") int number,
                 @Min(value = 1, message = "number must be more than 0") int passengerAverage) {
        this.number = number;
        this.passengerAverage = passengerAverage;
    }

    @OneToMany(mappedBy = "route", fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"deck","size","route","garage","driver"})
    @JsonProperty("buslist")
    private Set<Bus> busList = new HashSet<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "route_stage", schema = "company",
            joinColumns = {@JoinColumn(name = "route_number", referencedColumnName = "route_number")},
            inverseJoinColumns = {@JoinColumn(name = "stage_number", referencedColumnName = "stage_number")}
    )
    @JsonIgnoreProperties({"id","routes"})
    private Set<Stage> stages;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "route_town", schema = "company",
            joinColumns = {@JoinColumn(name = "route_number", referencedColumnName = "route_number")},
            inverseJoinColumns = {@JoinColumn(name = "town_name", referencedColumnName = "name")}
    )
    @JsonIgnoreProperties({"id","routes"})
    private Set<Town> towns;


    public void setBuses(Set<Bus> busList) {
        if (busList != null) {
            busList.forEach(el -> {
                el.setRoute(this);
            });
        }
    }
}
