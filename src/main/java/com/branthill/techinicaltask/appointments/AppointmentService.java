package com.branthill.techinicaltask.appointments;

import com.branthill.techinicaltask.appointments.api.Appointment;
import com.branthill.techinicaltask.appointments.api.AppointmentRequest;

public interface AppointmentService {
    void createAppointment(AppointmentRequest appointmentRequest);

    Appointment getAppointmentBy(Integer id);

    void deleteAppointment(Integer id);
}
