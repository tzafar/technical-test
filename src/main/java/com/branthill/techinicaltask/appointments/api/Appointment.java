package com.branthill.techinicaltask.appointments.api;

import com.branthill.techinicaltask.animals.api.Animal;
import com.branthill.techinicaltask.animals.api.Vet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Appointment {
    private LocalDateTime appointmentTime;
    private Animal animal;
    private Vet vet;
}
