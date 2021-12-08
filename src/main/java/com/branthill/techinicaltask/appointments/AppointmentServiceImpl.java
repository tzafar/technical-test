package com.branthill.techinicaltask.appointments;

import com.branthill.techinicaltask.appointments.api.Appointment;
import com.branthill.techinicaltask.appointments.api.AppointmentRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public void createAppointment(AppointmentRequest appointmentRequest){
        try {
            LocalDateTime startTime = LocalDateTime.parse(appointmentRequest.getStartTime());
            if(startTime.getMinute() != 00 || startTime.getMinute() != 30){
                // throw new InvalidStartTimeException();
            }

        } catch (Exception e) {
            //throw InvalidStartTimeException();
        }
        appointmentRepository.save(appointmentRequest);
    }

    @Override
    public Appointment getAppointmentBy(Integer id) {
        return appointmentRepository.getById(id);
    }

    @Override
    public void deleteAppointment(Integer id) {
        appointmentRepository.deleteBy(id);
    }
}
