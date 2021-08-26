package com.example.simpleweatherinfo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "weather_stamp")
public class WeatherData {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "time_stamp")
    private Timestamp timestamp;

    @Column(name = "temperature")
    private double temperature;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

}
