package com.weatherinfo.simpleweatherinfo.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "cities")

public class City {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "city")
    private List<WeatherData> weatherDataList;

}
