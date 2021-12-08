package com.branthill.techinicaltask.appointments.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppointmentRequest {
    private String startTime;
    private Integer animalId;
}
