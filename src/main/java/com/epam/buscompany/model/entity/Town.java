package com.epam.buscompany.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "routes")
@Entity
@Table(name = "town", schema = "company")
public class Town implements Serializable {

    @Id
    @SequenceGenerator(name="seq",sequenceName="town_id",allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    private int id;

    @NotBlank(message = "town name address is required")
    @Column(name = "name", unique = true)
    private String name;

    @ManyToMany(mappedBy = "towns", fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonIgnoreProperties({"busList","stages","towns","id","passengerAverage"})
    private Set<Route> routes;

    public Town(@NotBlank(message = "town name is required") String name) {
        this.name = name;
    }
}

