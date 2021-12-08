package com.branthill.techinicaltask.common;

import com.branthill.techinicaltask.common.errors.AnimalNotFoundException;
import com.branthill.techinicaltask.common.errors.AppointmentNotFound;
import com.branthill.techinicaltask.common.errors.AppointmentTimeSlotTakenException;
import com.branthill.techinicaltask.common.errors.Error;
import com.branthill.techinicaltask.common.errors.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class ErrorResponder {
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<Error> handleGeneralError(GeneralException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error("Something went wrong plesae try again later"));
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Error> handSQLException(SQLException e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Error("Something went wrong related to database: " + e.getMessage()));
    }

    @ExceptionHandler({AnimalNotFoundException.class, AppointmentNotFound.class})
    public ResponseEntity handleAnimalNotFoundException(Exception e){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(AppointmentTimeSlotTakenException.class)
    public ResponseEntity<Error> handleAppointmentTimeSlotTakenException(AppointmentTimeSlotTakenException e){
        return ResponseEntity.badRequest().body(new Error("The appointment time slot is taken"));
    }
}
