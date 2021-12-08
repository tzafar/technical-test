package com.branthill.techinicaltask.appointments;

import com.branthill.techinicaltask.appointments.api.Appointment;
import com.branthill.techinicaltask.appointments.api.AppointmentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppointmentController {
    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/appointments")
    public ResponseEntity<Appointment> createAppointment(@RequestBody AppointmentRequest appointmentRequest) {
        appointmentService.createAppointment(appointmentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/appointments/{id}")
    public ResponseEntity<Appointment> getAppointment(@PathVariable("id") Integer id){
        return ResponseEntity.ok().body(appointmentService.getAppointmentBy(id));
    }

    @DeleteMapping("/appointments/{id}")
    public ResponseEntity deleteAppointment(@PathVariable("id") Integer id){
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok().build();
    }
}
