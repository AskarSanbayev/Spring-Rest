package com.epam.buscompany.model.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@ToString(exclude = "bus")
@EqualsAndHashCode(exclude = "bus")
@Table(name = "driver", schema = "company")
public class Driver implements Serializable {

    @Id
    @SequenceGenerator(name="seq",sequenceName="driver_id",allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    private int id;

    @Min(value = 1, message = "number must be more than 0")
    @Column(name = "driver_number", unique = true)
    private int number;

    @NotBlank(message = "name is required")
    @Column(name = "name")
    private String name;

    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthday = new Date();


    public Driver(@Min(value = 1, message = "number must be more than 0") int number,
                  @NotBlank(message = "name is required") String name,
                  @NotBlank Date birthday) {
        this.number = number;
        this.name = name;
        this.birthday = (Date) birthday.clone();
    }

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "bus_number", referencedColumnName = "register_number")
    @JsonIgnoreProperties({"deck", "size", "route", "garage", "driver"})
    private Bus bus;

}
