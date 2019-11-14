package com.epam.buscompany.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"route", "garage"})
@ToString(exclude = {"driver"})
@Entity
@Table(name = "bus", schema = "company")
public class Bus implements Serializable {

    @Id
    @Min(value = 1, message = "number must be more than 0")
    @Column(name = "register_number", unique = true)
    @JsonProperty("registernumber")
    private int registerNumber;

    @Min(value = 15)
    @Column(name = "size")
    private int size;

    @Min(value = 1, message = "number must be 1 or 2")
    @Max(value = 2)
    @Column(name = "deck")
    private int deck;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "garage_number", referencedColumnName = "garage_number")
    @JsonIgnoreProperties({"id", "address", "name"})

    private Garage garage;

    @OneToOne(mappedBy = "bus", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Driver driver;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "route_number", referencedColumnName = "route_number")
    @JsonIgnoreProperties({"id", "passengerAverage"})
    private Route route;

    public Bus(@Min(value = 1, message = "number must be more than 0") int registerNumber,
               @Min(value = 15) int size,
               @Min(value = 1, message = "number must be 1 or 2") @Max(value = 2) int deck) {
        this.registerNumber = registerNumber;
        this.size = size;
        this.deck = deck;
    }
}
