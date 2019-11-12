package com.epam.buscompany.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "town", schema = "company")
public class Town implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank(message = "address is required")
    @Column(name = "name", unique = true)
    private String name;

    @ManyToMany(mappedBy = "towns", fetch = FetchType.LAZY)
    private Set<Route> routes;

    public Town(@NotBlank(message = "address is required") String name) {
        this.name = name;
    }
}

