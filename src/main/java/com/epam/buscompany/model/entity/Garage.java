package com.epam.buscompany.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@ToString(exclude = "busList")
@Table(name = "garage", schema = "company")
public class Garage implements Serializable {

    @Id
    @SequenceGenerator(name="seq",sequenceName="garage_id",allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    private int id;

    @Min(value = 1, message = "number must be more than 0")
    @Column(name = "garage_number", unique = true)
    private int number;

    @NotBlank(message = "address is required")
    @Column(name = "address")
    private String address;

    @NotBlank(message = "name is required")
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "garage", fetch = FetchType.EAGER,cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonIgnoreProperties({"deck","size","route","garage","driver"})
    @JsonProperty("buslist")
    private Set<Bus> busList = new HashSet<>();

    public Garage(@Min(value = 1, message = "number must be more than 0") int number,
                  @NotBlank(message = "address is required") String address,
                  @NotBlank(message = "name is required") String name) {
        this.number = number;
        this.address = address;
        this.name = name;
    }

    public void setBuses(Set<Bus> busList) {
        if (busList != null) {
            busList.forEach(el -> {
                el.setGarage(this);
            });
        }
    }
}
