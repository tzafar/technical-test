package com.branthill.techinicaltask.appointments;

import com.branthill.techinicaltask.animals.api.Animal;
import com.branthill.techinicaltask.animals.api.AnimalType;
import com.branthill.techinicaltask.animals.api.Vet;
import com.branthill.techinicaltask.appointments.api.Appointment;
import com.branthill.techinicaltask.appointments.api.AppointmentRequest;
import com.branthill.techinicaltask.common.H2ConnectionProvider;
import com.branthill.techinicaltask.common.errors.AppointmentNotFound;
import com.branthill.techinicaltask.common.errors.AppointmentTimeSlotTakenException;
import com.branthill.techinicaltask.common.errors.GeneralException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

@Slf4j
@Repository
public class AppointmentRepository {
    public void save(AppointmentRequest appointmentRequest) {
        Statement stmt;
        try {
            stmt = H2ConnectionProvider.getDatabaseConnection().createStatement();
            String getSql = "SELECT * FROM APPOINTMENTS WHERE start_time = '" + appointmentRequest.getStartTime() + "'";
            ResultSet resultSet = stmt.executeQuery(getSql);
            if(resultSet.next()){
                if(resultSet.getInt("animal_id") == appointmentRequest.getAnimalId()){
                    return;
                }

                throw new AppointmentTimeSlotTakenException();
            }

            String sql = "INSERT INTO APPOINTMENTS (start_time, animal_id) values ('" + appointmentRequest.getStartTime() + "'," + appointmentRequest.getAnimalId() + ")";
            stmt.execute(sql);
        } catch (SQLException e) {
            log.error("Error {}", e.getMessage());
            throw new GeneralException();
        }
    }

    public Appointment getById(Integer id) {
        Statement stmt;
        try {
            stmt = H2ConnectionProvider.getDatabaseConnection().createStatement();
            String getSql = "SELECT * FROM APPOINTMENTS left join ANIMALS ON APPOINTMENTS.ANIMAL_ID = ANIMALS.ID LEFT JOIN VETS ON ANIMALS.VET_ID = VETS.ID LEFT JOIN ANIMAL_TYPES ON ANIMALS.ANIMAL_TYPE_ID = ANIMAL_TYPES.ID WHERE APPOINTMENTS.ID = " + id;
            ResultSet resultSet = stmt.executeQuery(getSql);
            if(!resultSet.next()){
                throw new AppointmentNotFound();
            }

            return new Appointment(LocalDateTime.parse(resultSet.getString("start_time")),
                    new Animal(resultSet.getString("animal_name"),
                            new Vet(resultSet.getString("vet_name")),
                            new AnimalType(resultSet.getString("name"))), new Vet(resultSet.getString("vet_name")));
        } catch (SQLException e) {
            log.error("Error {}", e.getMessage());
            throw new GeneralException();
        }
    }

    public void deleteBy(Integer id) {
        try {
            Statement statement = H2ConnectionProvider.getDatabaseConnection().createStatement();
            String sql = "DELETE FROM APPOINTMENTS WHERE ID = " + id;
            statement.execute(sql);
        } catch (SQLException e) {
            throw new GeneralException();
        }
    }
}
