package com.weatherinfo.simpleweatherinfo.domain;

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

    private static final String SEQ_NAME = "stamp_seq";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "time_stamp")
    private Timestamp timestamp;

    @Column(name = "temperature")
    private double temperature;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "weather_data",
            joinColumns = @JoinColumn(name = "stamp_id"),
            inverseJoinColumns = @JoinColumn(name = "city_id"))
    private City city;


}
