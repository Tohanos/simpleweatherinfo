package com.example.simpleweatherinfo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cities")

public class City {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "id", nullable = false)
    private String name;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<WeatherData> weatherDataList;

}
