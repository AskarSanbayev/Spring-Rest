package com.epam.buscompany.model.entity;


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
@ToString(exclude = {"garage", "route"})
@Entity
@Table(name = "bus", schema = "company")
public class Bus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Min(value = 1, message = "number must be more than 0")
    @Column(name = "register_number", unique = true)
    private int registerNumber;

    @Min(value = 15)
    @Column(name = "size")
    private int size;

    @Min(value = 1, message = "number must be 1 or 2")
    @Max(value = 2)
    @Column(name = "deck")
    private int deck;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "garage_number", referencedColumnName = "garage_number")
    private Garage garage;

    @OneToOne(mappedBy = "bus")
    private Driver driver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_number", referencedColumnName = "route_number")
    private Route route;

    public Bus(@Min(value = 1, message = "number must be more than 0") int registerNumber,
               @Min(value = 15) int size,
               @Min(value = 1, message = "number must be 1 or 2") @Max(value = 2) int deck) {
        this.registerNumber = registerNumber;
        this.size = size;
        this.deck = deck;

    }
}
