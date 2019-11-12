package com.epam.buscompany.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "stage", schema = "company")
public class Stage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Min(value = 1, message = "number must be more than 0")
    @Column(name = "stage_number", unique = true)
    private int number;

    @ManyToMany(mappedBy = "stages", fetch = FetchType.LAZY)
    private Set<Route> routes;

    public Stage(@Min(value = 1, message = "number must be more than 0") int number) {
        this.number = number;
    }
}