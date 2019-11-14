package com.epam.buscompany.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "routes")
@Entity
@Table(name = "stage", schema = "company")
public class Stage implements Serializable {

    @Id
    @SequenceGenerator(name="seq",sequenceName="stage_id",allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    private int id;

    @Min(value = 1, message = "number must be more than 0")
    @Column(name = "stage_number", unique = true)
    private int number;

    @ManyToMany(mappedBy = "stages", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnoreProperties({"busList","stages","towns","id","passengerAverage"})
    private Set<Route> routes;

    public Stage(@Min(value = 1, message = "number must be more than 0") int number) {
        this.number = number;
    }
}